package com.hadi.trainticketing.validator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.LoginActivityBinding;
import com.hadi.trainticketing.passenger.ForgetPasswordActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ValidatorLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginActivityBinding activityBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity);

        activityBinding.signUpBtn.setVisibility(View.GONE);
        activityBinding.forgetPassBtn.setOnClickListener(this);
        activityBinding.signInBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == activityBinding.forgetPassBtn.getId()) {
            startActivity(new Intent(ValidatorLoginActivity.this, ForgetPasswordActivity.class));
        } else if (viewId == activityBinding.signInBtn.getId()) {

        }
    }

}
