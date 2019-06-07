package com.hadi.trainticketing.passenger.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.LoginActivityBinding;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInFields;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInResponse;
import com.hadi.trainticketing.utils.Utils;

/**
 * @author Hady Ahmed
 * @version 1.0
 */
public class PassengerSignInActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginActivityBinding activityBinding;
    // preferences key for the user data
    public static final String IS_SIGNED_IN = "isSignedIn";
    public static final String USER_TOKEN = "user_id_pref_key";
    public static final String USER_ID = "uid";

    private PassengerViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity);

        viewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

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
        // progress dialog as message for the user
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        viewModel.loginWithCredentials(user)
                .observe(this, new Observer<SignInResponse>() {
                    @Override
                    public void onChanged(SignInResponse signInResponse) {
                        progressDialog.dismiss();
                        if (signInResponse != null && signInResponse.isSuccess()) {
                            saveUserInfo(signInResponse);
                            Toast.makeText(PassengerSignInActivity.this, "Welcome " + signInResponse.getUser().getName(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PassengerSignInActivity.this, PassengerMainActivity.class));
                            finish();
                        } else if (signInResponse != null && !signInResponse.isSuccess()) {
                            Toast.makeText(PassengerSignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PassengerSignInActivity.this, "something went wring with our server side", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserInfo(SignInResponse response) {
        // saving data to shared pref if the user selected remember me
        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(PassengerSignInActivity.this);
        userPrefs.edit()
                .putBoolean(PassengerSignInActivity.IS_SIGNED_IN, true)
                .putString(PassengerSignInActivity.USER_TOKEN, response.getToken())
                .putString(PassengerSignInActivity.USER_ID, response.getUser().getId())
                .apply();
    }
}

