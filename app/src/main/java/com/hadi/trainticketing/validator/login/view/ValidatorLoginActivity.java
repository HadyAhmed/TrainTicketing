package com.hadi.trainticketing.validator.login.view;

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
import com.hadi.trainticketing.databinding.FragmentLoginPassengerBinding;
import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.passenger.login.pojo.login.SignInFields;
import com.hadi.trainticketing.passenger.login.view.ForgetPasswordFragment;
import com.hadi.trainticketing.passenger.login.view.PassengerSignInActivity;
import com.hadi.trainticketing.validator.home.ValidatorMainActivity;
import com.hadi.trainticketing.validator.login.pojo.EmployeeLoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidatorLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentLoginPassengerBinding activityBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.fragment_login_passenger);

        activityBinding.signUpBtn.setVisibility(View.GONE);
        activityBinding.forgetPassBtn.setOnClickListener(this);
        activityBinding.signInBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == activityBinding.forgetPassBtn.getId()) {
            startActivity(new Intent(ValidatorLoginActivity.this, ForgetPasswordFragment.class));
        } else if (viewId == activityBinding.signInBtn.getId()) {
            checkForLoginCredentials();
        }
    }

    private void checkForLoginCredentials() {
        // progress dialog as message for the user

        if (!activityBinding.userNameEt.getText().toString().equals("") || !activityBinding.passwordEt.getText().toString().equals("")) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing In...");
            progressDialog.show();
            progressDialog.setCancelable(false);

            SignInFields signInFields = new SignInFields(activityBinding.userNameEt.getText().toString(), activityBinding.passwordEt.getText().toString());

            WebServices.serverConnection.create(WebServices.class)
                    .loginAsEmpolyee(signInFields)
                    .enqueue(new Callback<EmployeeLoginResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<EmployeeLoginResponse> call, @NonNull Response<EmployeeLoginResponse> response) {
                            progressDialog.dismiss();
                            if (response.body() != null && response.body().getSuccess()) {
                                saveUserInfo(response.body());
                                startActivity(new Intent(ValidatorLoginActivity.this, ValidatorMainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ValidatorLoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<EmployeeLoginResponse> call, @NonNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(ValidatorLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "insert all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserInfo(EmployeeLoginResponse response) {
        // saving data to shared pref if the user selected remember me
        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        userPrefs.edit()
                .putBoolean(PassengerSignInActivity.IS_SIGNED_IN, true)
                .putBoolean(PassengerSignInActivity.IS_SIGNED_AS_PASSENGER, false)
                .putString(PassengerSignInActivity.USER_TOKEN, response.getToken())
                .putString(PassengerSignInActivity.USER_ID, response.getEmp().getId())
                .apply();
    }

}
