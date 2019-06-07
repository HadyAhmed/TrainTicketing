package com.hadi.trainticketing.passenger.view.activities;

import android.content.DialogInterface;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityPassengerMainBinding;
import com.hadi.trainticketing.datasource.database.StaticDataSource;
import com.hadi.trainticketing.passenger.adapter.WelcomeAdapter;
import com.hadi.trainticketing.passenger.view.features.BalanceActivity;
import com.hadi.trainticketing.passenger.view.features.EnquireActivity;
import com.hadi.trainticketing.passenger.view.features.HistoryActivity;
import com.hadi.trainticketing.passenger.view.features.ReserveActivity;
import com.hadi.trainticketing.passenger.view.features.TicketActivity;

public class PassengerMainActivity extends AppCompatActivity
        implements WelcomeAdapter.OnWelcomeItemClickListener, Toolbar.OnMenuItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPassengerMainBinding passengerMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_main);


        passengerMainBinding.mainToolbar.inflateMenu(R.menu.main_menu);
        passengerMainBinding.mainToolbar.setOnMenuItemClickListener(this);

        passengerMainBinding.passengerFeaturesRv.setLayoutManager(new LinearLayoutManager(this));
        passengerMainBinding.passengerFeaturesRv.setAdapter(new WelcomeAdapter(StaticDataSource.getPassengerCardsList(), this));
    }


    @Override
    public void onWelcomeItemClick(int itemPosition) {
        if (itemPosition == StaticDataSource.ENQUIRE_ITEM) {
            startActivity(new Intent(this, EnquireActivity.class));

        } else if (itemPosition == StaticDataSource.RESERVE_ITEM) {
            startActivity(new Intent(this, ReserveActivity.class));

        } else if (itemPosition == StaticDataSource.BALANCE_ITEM) {
            startActivity(new Intent(this, BalanceActivity.class));

        } else if (itemPosition == StaticDataSource.TICKET_ITEM) {
            startActivity(new Intent(this, TicketActivity.class));

        } else if (itemPosition == StaticDataSource.HISTORY_ITEM) {
            startActivity(new Intent(this, HistoryActivity.class));

        }
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
                        startActivity(new Intent(PassengerMainActivity.this, PassengerSignInActivity.class));
                        finish();
                    }).setNegativeButton("Cancel", null)
                    .show();
            return true;
        } else
            return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PassengerMainActivity.this);
        builder.setTitle("Exit!")
                .setMessage("Are you sure to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Cancel", null)
                .show();
    }
}
