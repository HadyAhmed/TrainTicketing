package com.hadi.trainticketing.passenger.view.navigations;

import android.os.Bundle;
import android.view.View;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityBalanceBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBalanceBinding balanceBinding = DataBindingUtil.setContentView(this, R.layout.activity_balance);
        balanceBinding.balanceToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(BalanceActivity.this);
            }
        });
    }
}
