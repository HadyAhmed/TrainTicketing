package com.hadi.trainticketing.passenger.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityPassengerMainBinding;
import com.hadi.trainticketing.datasource.database.StaticDataSource;
import com.hadi.trainticketing.passenger.adapter.WelcomeAdapter;
import com.hadi.trainticketing.passenger.view.navigations.BalanceActivity;
import com.hadi.trainticketing.passenger.view.navigations.EnquireActivity;
import com.hadi.trainticketing.passenger.view.navigations.HistoryActivity;
import com.hadi.trainticketing.passenger.view.navigations.ReserveActivity;
import com.hadi.trainticketing.passenger.view.navigations.TicketActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

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
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_logout) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            preferences.edit().clear().apply();
            startActivity(new Intent(PassengerMainActivity.this, PassengerSignInActivity.class));
            finish();
            return true;
        } else
            return false;
    }
}
