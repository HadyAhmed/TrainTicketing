package com.hadi.trainticketing.passenger.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ForgetPasswordLayoutBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private ForgetPasswordLayoutBinding forgetPasswordLayoutBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPasswordLayoutBinding = DataBindingUtil.setContentView(this, R.layout.forget_password_layout);

        forgetPasswordLayoutBinding.resetPassBtn.setOnClickListener(this);
        forgetPasswordLayoutBinding.resetPassToolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == forgetPasswordLayoutBinding.resetPassBtn.getId()) {
            Toast.makeText(this, "you will be sent email with reset link if it was valid", Toast.LENGTH_SHORT).show();
        }
        onBackPressed();
    }
}
