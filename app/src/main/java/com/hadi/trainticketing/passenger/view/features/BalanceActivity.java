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

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityBalanceBinding;
import com.hadi.trainticketing.passenger.view.activities.PassengerMainActivity;

public class BalanceActivity extends AppCompatActivity implements AlertDialog.OnClickListener {

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

        balanceBinding.userBalanceTv.setText(
                String.format(getString(R.string.creditEg),
                        PreferenceManager.getDefaultSharedPreferences(this).getInt(PassengerMainActivity.USER_BALANCE_PREF, 0))
        );

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
