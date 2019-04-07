package com.hadi.trainticketing.passenger;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivitySignUpBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding signUpBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        signUpBinding.signUpToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        removeErrors(signUpBinding.fullNameTextInputLayout, signUpBinding.nameEt);
        removeErrors(signUpBinding.nationalIdTextInputLayout, signUpBinding.nationalIdEt);
        removeErrors(signUpBinding.emailTextInputLayout, signUpBinding.emailEt);
        removeErrors(signUpBinding.passwordTextInputLayout, signUpBinding.passEt);

        signUpBinding.submitRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForEntries()) {
                    checkForExistingMail();
                }
            }
        });
    }

    private void removeErrors(final TextInputLayout textInputLayout, TextInputEditText inputEditText) {
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

    private void checkForExistingMail() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

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
}
