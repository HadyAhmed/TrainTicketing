package com.hadi.trainticketing.passenger.home.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.boarding.WelcomeActivity;
import com.hadi.trainticketing.databinding.ActivityPassengerMainBinding;
import com.hadi.trainticketing.passenger.home.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.login.view.PassengerSignInActivity;

public class PassengerMainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPassengerMainBinding passengerMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_main);

        PassengerViewModel passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

        passengerViewModel.requestUserInfo(PreferenceManager.getDefaultSharedPreferences(this).getString(PassengerSignInActivity.USER_TOKEN, ""));

        passengerViewModel.getUserInfo().observe(this, userResponse -> {
            if (userResponse != null) {
                passengerMainBinding.mainToolbar.setSubtitle(userResponse.getResult().getName());
            }
        });

        passengerMainBinding.mainToolbar.inflateMenu(R.menu.main_menu);
        passengerMainBinding.mainToolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_account_settings) {
            startActivity(new Intent(PassengerMainActivity.this, PassengerProfilePage.class));
            return true;
        } else if (item.getItemId() == R.id.action_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(PassengerMainActivity.this);
            builder.setTitle("Logout!")
                    .setMessage("Are you sure to logout ?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PassengerMainActivity.this);
                        preferences.edit().clear().apply();
                        startActivity(new Intent(PassengerMainActivity.this, WelcomeActivity.class));
                        finish();
                    }).setNegativeButton("Cancel", null)
                    .show();
            return true;
        } else
            return false;
    }
}
