package com.hadi.trainticketing.passenger.login.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentPassengerRegisterBinding;
import com.hadi.trainticketing.passenger.home.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.login.pojo.signup.SignUpFields;

public class PassengerRegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentPassengerRegisterBinding signUpBinding;
    private String gender;
    private PassengerViewModel passengerViewModel;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signUpBinding = FragmentPassengerRegisterBinding.inflate(inflater, container, false);

        signUpBinding.genderSpinner.setOnItemSelectedListener(this);

        removeErrors(signUpBinding.fullNameTextInputLayout, signUpBinding.nameEt);
        removeErrors(signUpBinding.nationalIdTextInputLayout, signUpBinding.nationalIdEt);
        removeErrors(signUpBinding.emailTextInputLayout, signUpBinding.emailEt);
        removeErrors(signUpBinding.passwordTextInputLayout, signUpBinding.passEt);

        signUpBinding.submitRegistrationBtn.setOnClickListener(v -> {
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
        });
        return signUpBinding.getRoot();
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

    private void checkForExistingMail(final SignUpFields user) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Creating Account...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        passengerViewModel.registerNewAccount(user)
                .observe(this, signUpResponse -> {
                    progressDialog.dismiss();
                    if (signUpResponse != null) {
                        Toast.makeText(context, "Successfully Created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Account Already Exists", Toast.LENGTH_SHORT).show();
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
