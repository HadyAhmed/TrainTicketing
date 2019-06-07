package com.hadi.trainticketing.datasource.webservice;

import com.google.gson.Gson;
import com.hadi.trainticketing.passenger.model.pojo.enquire.EnquireResponse;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInFields;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInResponse;
import com.hadi.trainticketing.passenger.model.pojo.profile.UserResponse;
import com.hadi.trainticketing.passenger.model.pojo.reservation.request.ReservationRequest;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.reservation.ReservationResult;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.reserve_seat.ReservationResponse;
import com.hadi.trainticketing.passenger.model.pojo.signup.SignUpFields;
import com.hadi.trainticketing.passenger.model.pojo.signup.SignUpResponse;
import com.hadi.trainticketing.passenger.model.pojo.stations.StationsResponse;
import com.hadi.trainticketing.passenger.model.pojo.ticket.TicketHistoryResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * interface to handle the {@link Retrofit} methods
 * and call backs
 */
public interface WebServices {
    String BASE_UR = "https://trainres.herokuapp.com";

    String METHOD_FROM = "from";
    String METHOD_TO = "to";
    String METHOD_DATE = "date";
    String METHOD_CLASS = "classChoosen";

    Retrofit serverConnection = new Retrofit
            .Builder()
            .baseUrl(BASE_UR)
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();

    @POST("user/signin")
    Call<SignInResponse> getLoginResponse(@Body SignInFields user);

    @POST("user/signup")
    Call<SignUpResponse> getSignUpResponse(@Body SignUpFields user);

    @GET("user/search/" + "{email}")
    Call<UserResponse> getUserData(@Path("email") String userEmail);

    @GET("station")
    Call<StationsResponse> getStations();

    @GET("justTrip/search")
    Call<EnquireResponse> getTripEnquire(
            @Query(METHOD_FROM) String from,
            @Query(METHOD_TO) String to,
            @Query(METHOD_DATE) String date,
            @Query(METHOD_CLASS) String classType
    );

    @GET("reservation/search")
    Call<ReservationResponse> getSeats(@Query("classType") int classType, @Query("train") String trainId, @Query("justTrip") String[] ids);


    @GET("user/profile")
    Call<UserResponse> getProfileInfo(@Header("Authorization") String userToken);

    @POST("reservation")
    Call<ReservationResult> getReservationResult(@Body ReservationRequest reservation);

    @GET("reservation/byUser/{uid}")
    Call<TicketHistoryResponse> getTicketHsitory(@Path("uid") String uid);
}
