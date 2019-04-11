package com.hadi.trainticketing.datasource;

import com.google.gson.Gson;
import com.hadi.trainticketing.passenger.pojo.enquire.EnquireResponse;
import com.hadi.trainticketing.passenger.pojo.login.LoginResponse;
import com.hadi.trainticketing.passenger.pojo.login.UserSignIn;
import com.hadi.trainticketing.passenger.pojo.signup.SignUpResponse;
import com.hadi.trainticketing.passenger.pojo.signup.UserSignUp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServices {
    String BASE_UR = "https://trainres.herokuapp.com";

    String USER_SIGN_IN = "user/signin";
    String USER_SIGN_UP = "user/signup";
    //https://trainres.herokuapp.com/?from=Cairo&to=Alexandria&date=pm&classChoosen=2
    String SEARCH_TRIP = "trip/search";
    String METHOD_FROM = "from";
    String METHOD_TO = "to";
    String METHOD_DATE = "date";
    String METHOD_CLASS = "classChoosen";

    Retrofit serverConnection = new Retrofit
            .Builder()
            .baseUrl(BASE_UR)
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();

    @POST(USER_SIGN_IN)
    Call<LoginResponse> getLoginResponse(@Body UserSignIn user);

    @POST(USER_SIGN_UP)
    Call<SignUpResponse> getSignUpResponse(@Body UserSignUp user);

    @GET(SEARCH_TRIP)
    Call<EnquireResponse> getTripEnquire(
            @Query(METHOD_FROM) String from,
            @Query(METHOD_TO) String to,
            @Query(METHOD_DATE) String date,
            @Query(METHOD_CLASS) String classType
    );
}
