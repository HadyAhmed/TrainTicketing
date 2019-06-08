package com.hadi.trainticketing.passenger.login.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentLoginPassengerBinding;
import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.passenger.home.view.activities.PassengerMainActivity;
import com.hadi.trainticketing.passenger.login.pojo.login.PassengerLoginResponse;
import com.hadi.trainticketing.passenger.login.pojo.login.SignInFields;
import com.hadi.trainticketing.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerLoginFragment extends Fragment implements View.OnClickListener {
    private FragmentLoginPassengerBinding loginPassengerBinding;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginPassengerBinding = FragmentLoginPassengerBinding.inflate(inflater, container, false);

        loginPassengerBinding.forgetPassBtn.setOnClickListener(this);
        loginPassengerBinding.signInBtn.setOnClickListener(this);
        loginPassengerBinding.signUpBtn.setOnClickListener(this);

        return loginPassengerBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == loginPassengerBinding.forgetPassBtn.getId()) {
            Navigation.findNavController(v).navigate(R.id.action_passengerLoginFragment_to_forgetPasswordFragment);
        } else if (viewId == loginPassengerBinding.signInBtn.getId()) {
            if (Utils.isNetworkAvailable(context)) {
                checkForCredentials(new SignInFields(loginPassengerBinding.userNameEt.getText().toString(), loginPassengerBinding.passwordEt.getText().toString()));
            } else {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        } else if (viewId == loginPassengerBinding.signUpBtn.getId()) {
            Navigation.findNavController(v).navigate(R.id.action_passengerLoginFragment_to_passengerRegisterFragment);
        }
    }

    private void checkForCredentials(SignInFields user) {
        if (!loginPassengerBinding.userNameEt.getText().toString().equals("") || !loginPassengerBinding.passwordEt.getText().toString().equals("")) {  // progress dialog as message for the user
            final ProgressDialog progressDialog = new ProgressDialog(context);
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
                                startActivity(new Intent(context, PassengerMainActivity.class));
                                if (getActivity() != null) {
                                    getActivity().finish();
                                }
                            } else {
                                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<PassengerLoginResponse> call, @NonNull Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        } else {
            Toast.makeText(context, "insert all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserInfo(PassengerLoginResponse response) {
        // saving data to shared pref if the user selected remember me
        SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        userPrefs.edit()
                .putBoolean(PassengerSignInActivity.IS_SIGNED_IN, true)
                .putBoolean(PassengerSignInActivity.IS_SIGNED_AS_PASSENGER, true)
                .putString(PassengerSignInActivity.USER_TOKEN, response.getToken())
                .putString(PassengerSignInActivity.USER_ID, response.getUser().getId())
                .apply();
    }
}
