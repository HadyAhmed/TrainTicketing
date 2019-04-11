package com.hadi.trainticketing.passenger.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.LoginActivityBinding;
import com.hadi.trainticketing.datasource.WebServices;
import com.hadi.trainticketing.passenger.pojo.login.LoginResponse;
import com.hadi.trainticketing.passenger.pojo.login.UserSignIn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerSignInActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginActivityBinding activityBinding;
    // preferences key for the user data
    public static final String IS_SIGNED_IN = "isSignedIn";
    public static final String USER_EMAIL_PREF_KEY = "user_email_pref_key";
    public static final String USER_NAME_PREF_KEY = "user_name_pref_key";
    public static final String USER_ID_PREF_KEY = "user_id_pref_key";
    private static final String TAG = "PassengerSignInTag";

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
            checkForCredentials(new UserSignIn(activityBinding.userNameEt.getText().toString(), activityBinding.passwordEt.getText().toString()));
        } else if (viewId == activityBinding.signUpBtn.getId()) {
            startActivity(new Intent(PassengerSignInActivity.this, PassengerSignUpActivity.class));
        }
    }

    private void checkForCredentials(UserSignIn user) {
        // progress dialog as message for the user
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        WebServices.serverConnection.create(WebServices.class).getLoginResponse(user).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.body() != null) {
                    progressDialog.dismiss();
                    Log.d(TAG, "onResponse: success");
                    if (response.body().isSuccess()) {
                        saveUserInfo(response);
                        Toast.makeText(PassengerSignInActivity.this, "Welcome " + response.body().getUser().getName(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PassengerSignInActivity.this, PassengerMainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(PassengerSignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.hide();
                    Toast.makeText(PassengerSignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PassengerSignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void saveUserInfo(@NonNull Response<LoginResponse> response) {
        if (response.body() != null) {
            // saving data to shared pref if the user selected remember me
            SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(PassengerSignInActivity.this);
            userPrefs.edit()
                    .putString(PassengerSignInActivity.USER_EMAIL_PREF_KEY, response.body().getUser().getEmail())
                    .putBoolean(PassengerSignInActivity.IS_SIGNED_IN, true)
                    .putString(PassengerSignInActivity.USER_NAME_PREF_KEY, response.body().getUser().getName())
                    .putString(PassengerSignInActivity.USER_ID_PREF_KEY, response.body().getUser().getId())
                    .apply();
        }
    }
}
