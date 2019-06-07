package com.hadi.trainticketing.passenger.view.features;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityBalanceBinding;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.model.pojo.profile.UserResponse;
import com.hadi.trainticketing.passenger.view.activities.PassengerSignInActivity;

public class BalanceActivity extends AppCompatActivity implements AlertDialog.OnClickListener {
    PassengerViewModel passengerViewModel;
    private ActivityBalanceBinding balanceBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        balanceBinding = DataBindingUtil.setContentView(this, R.layout.activity_balance);
        balanceBinding.balanceToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(BalanceActivity.this);
            }
        });

        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

        passengerViewModel.requestUserInfo(PreferenceManager.getDefaultSharedPreferences(this).getString(PassengerSignInActivity.USER_TOKEN, ""));

        passengerViewModel.getUserInfo().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                balanceBinding.balanceProgress.setVisibility(View.INVISIBLE);
                if (userResponse != null) {
                    balanceBinding.userBalanceTv.setText(String.format(getString(R.string.creditEg), userResponse.getResult().getData().getBalance()));
                }
            }
        });


        balanceBinding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpPaymentMethodDialog(BalanceActivity.this);
            }
        });
    }

    private void showUpPaymentMethodDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Choose payment method")
                .setView(R.layout.dialog_payment_method)
                .setPositiveButton("Add Credit Card", this)
                .setNeutralButton("Cancel", null)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        startActivity(new Intent(BalanceActivity.this, AddCreditCardActivity.class));
    }
}
