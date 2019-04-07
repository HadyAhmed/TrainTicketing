package com.hadi.trainticketing.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.LoginActivityBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class PassengerLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginActivityBinding activityBinding;

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
            startActivity(new Intent(PassengerLoginActivity.this, ForgetPasswordActivity.class));
        } else if (viewId == activityBinding.signInBtn.getId()) {

        } else if (viewId == activityBinding.signUpBtn.getId()) {
            startActivity(new Intent(PassengerLoginActivity.this, SignUpActivity.class));
        }
    }
}
