package com.hadi.trainticketing.passenger.login.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hadi.trainticketing.R;

/**
 * @author Hady Ahmed
 * @version 1.0
 */
public class PassengerSignInActivity extends AppCompatActivity {
    // preferences key for the user data
    public static final String IS_SIGNED_AS_PASSENGER = "signInAsPassengerPrefs";
    public static final String IS_SIGNED_IN = "isSignedIn";
    public static final String USER_TOKEN = "user_id_pref_key";
    public static final String USER_ID = "uid";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_login);
    }

}

