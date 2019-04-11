package com.hadi.trainticketing.datasource.webservice;

import com.google.gson.Gson;
import com.hadi.trainticketing.passenger.pojo.enquire.EnquireResponse;
import com.hadi.trainticketing.passenger.pojo.login.LoginResponse;
import com.hadi.trainticketing.passenger.pojo.login.UserSignIn;
import com.hadi.trainticketing.passenger.pojo.profile.UserResponse;
import com.hadi.trainticketing.passenger.pojo.signup.SignUpResponse;
import com.hadi.trainticketing.passenger.pojo.signup.UserSignUp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * interface to handle the {@link Retrofit} methods
 * and call backs
 */
public interface WebServices {
    String BASE_UR = "https://trainres.herokuapp.com";

    //https://trainres.herokuapp.com/?from=Cairo&to=Alexandria&date=pm&classChoosen=2
    String SEARCH_TRIP = "trip/search";
    String METHOD_FROM = "from";
    String METHOD_TO = "to";
    String METHOD_DATE = "date";
    String METHOD_CLASS = "classChoosen";
    //https://trainres.herokuapp.com/user/h.a7med2017@gmail.com


    Retrofit serverConnection = new Retrofit
            .Builder()
            .baseUrl(BASE_UR)
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();

    @POST("user/signin")
    Call<LoginResponse> getLoginResponse(@Body UserSignIn user);

    @POST("user/signup")
    Call<SignUpResponse> getSignUpResponse(@Body UserSignUp user);

    @GET("user/" + "{email}")
    Call<UserResponse> getUserData(@Path("email") String userEmail);

    @GET(SEARCH_TRIP)
    Call<EnquireResponse> getTripEnquire(
            @Query(METHOD_FROM) String from,
            @Query(METHOD_TO) String to,
            @Query(METHOD_DATE) String date,
            @Query(METHOD_CLASS) String classType
    );
}
