package com.hadi.trainticketing.passenger.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.LoginActivityBinding;
import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.passenger.model.pojo.login.PassengerLoginResponse;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInFields;
import com.hadi.trainticketing.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Hady Ahmed
 * @version 1.0
 */
public class PassengerSignInActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginActivityBinding activityBinding;
    // preferences key for the user data
    public static final String IS_SIGNED_AS_PASSENGER = "signInAsPassengerPrefs";
    public static final String IS_SIGNED_IN = "isSignedIn";
    public static final String USER_TOKEN = "user_id_pref_key";
    public static final String USER_ID = "uid";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity);

        activityBinding.forgetPassBtn.setOnClickListener(this);
        activityBinding.signInBtn.setOnClickListener(this);
        activityBinding.signUpBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == activityBinding.forgetPassBtn.getId()) {
            startActivity(new Intent(PassengerSignInActivity.this, ForgetPasswordActivity.class));
        } else if (viewId == activityBinding.signInBtn.getId()) {
            if (Utils.isNetworkAvailable(PassengerSignInActivity.this)) {
                checkForCredentials(new SignInFields(activityBinding.userNameEt.getText().toString(), activityBinding.passwordEt.getText().toString()));
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        } else if (viewId == activityBinding.signUpBtn.getId()) {
            startActivity(new Intent(PassengerSignInActivity.this, PassengerSignUpActivity.class));
        }
    }

    private void checkForCredentials(SignInFields user) {
        if (!activityBinding.userNameEt.getText().toString().equals("") || !activityBinding.passwordEt.getText().toString().equals("")) {  // progress dialog as message for the user
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing In...");
            progressDialog.show();
            progressDialog.setCancelable(false);

            WebServices.serverConnection.create(WebServices.class)
                    .loginAsPassenger(user)
                    .enqueue(new Callback<PassengerLoginResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<PassengerLoginResponse> call, @NonNull Response<PassengerLoginResponse> response) {
                            progressDialog.dismiss();
                            if (response.body() != null && response.body().isSuccess()) {
                                saveUserInfo(response.body());
                                startActivity(new Intent(PassengerSignInActivity.this, PassengerMainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(PassengerSignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<PassengerLoginResponse> call, @NonNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(PassengerSignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(this, "insert all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserInfo(PassengerLoginResponse response) {
        // saving data to shared pref if the user selected remember me
        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(PassengerSignInActivity.this);
        userPrefs.edit()
                .putBoolean(PassengerSignInActivity.IS_SIGNED_IN, true)
                .putBoolean(PassengerSignInActivity.IS_SIGNED_AS_PASSENGER, true)
                .putString(PassengerSignInActivity.USER_TOKEN, response.getToken())
                .putString(PassengerSignInActivity.USER_ID, response.getUser().getId())
                .apply();
    }
}

