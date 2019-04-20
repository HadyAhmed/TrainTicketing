package com.hadi.trainticketing.passenger.view.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.textfield.TextInputLayout;
import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivitySignUpBinding;
import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.passenger.pojo.signup.SignUpFields;
import com.hadi.trainticketing.passenger.pojo.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerSignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivitySignUpBinding signUpBinding;
    private static final String TAG = "PassengerSignUpTag";
    private String gender;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        signUpBinding.signUpToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        signUpBinding.genderSpinner.setOnItemSelectedListener(this);

        removeErrors(signUpBinding.fullNameTextInputLayout, signUpBinding.nameEt);
        removeErrors(signUpBinding.nationalIdTextInputLayout, signUpBinding.nationalIdEt);
        removeErrors(signUpBinding.emailTextInputLayout, signUpBinding.emailEt);
        removeErrors(signUpBinding.passwordTextInputLayout, signUpBinding.passEt);

        signUpBinding.submitRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForEntries()) {
                    SignUpFields user = new SignUpFields(
                            signUpBinding.nationalIdEt.getText().toString(),
                            signUpBinding.emailEt.getText().toString(),
                            signUpBinding.passEt.getText().toString(),
                            gender,
                            signUpBinding.nameEt.getText().toString()
                    );
                    checkForExistingMail(user);
                }
            }
        });
    }

    private void removeErrors(final TextInputLayout textInputLayout, EditText inputEditText) {
        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textInputLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                textInputLayout.setError(null);
            }
        });
    }

    private void checkForExistingMail(SignUpFields user) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        Log.d(TAG, "checkForExistingMail: " + user.toString());

        WebServices.serverConnection.create(WebServices.class)
                .getSignUpResponse(user)
                .enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                        progressDialog.dismiss();
                        if (response.body() != null) {
                            Log.d(TAG, "onResponse: not null");
                            SharedPreferences userPref = PreferenceManager.getDefaultSharedPreferences(PassengerSignUpActivity.this);
                            userPref.edit()
                                    .putString(PassengerSignInActivity.USER_EMAIL_PREF_KEY, response.body().getUser().getEmail())
                                    .putBoolean(PassengerSignInActivity.IS_SIGNED_IN, true)
                                    .putString(PassengerSignInActivity.USER_NAME_PREF_KEY, response.body().getUser().getName())
                                    .putString(PassengerSignInActivity.USER_ID_PREF_KEY, response.body().getUser().getId())
                                    .apply();
                            Toast.makeText(PassengerSignUpActivity.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PassengerSignUpActivity.this, PassengerMainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(PassengerSignUpActivity.this, "Mail Already Exists", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onResponse: Exists");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
                        progressDialog.dismiss();
                        Log.d(TAG, "onFailure: ");
                        Toast.makeText(PassengerSignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private boolean checkForEntries() {
        if (!isValidName(signUpBinding.nameEt.getEditableText())) {
            signUpBinding.fullNameTextInputLayout.setError("please insert convenience name");
            return false;
        } else if (!isValidEmail(signUpBinding.emailEt.getEditableText())) {
            signUpBinding.emailTextInputLayout.setError("insert valid mail");
            return false;
        } else if (!isValidPass(signUpBinding.passEt.getEditableText())) {
            signUpBinding.passwordTextInputLayout.setError("please insert stronger password");
            return false;
        } else if (!isValidNationalId(signUpBinding.nationalIdEt.getEditableText())) {
            signUpBinding.nationalIdTextInputLayout.setError("national id is 14 numbers");
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidPass(Editable editableText) {
        return editableText != null && editableText.length() >= 4;
    }

    private boolean isValidNationalId(Editable editableText) {
        return editableText != null && editableText.length() == 14;
    }

    private boolean isValidEmail(Editable editableText) {
        String email = editableText.toString();
        return email.endsWith(".com") && email.contains("@");
    }

    private boolean isValidName(Editable fullNameTextInputLayout) {
        return fullNameTextInputLayout != null && fullNameTextInputLayout.length() >= 4;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            gender = getString(R.string.male);

        } else if (position == 1) {
            gender = getString(R.string.female);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //
    }
}
