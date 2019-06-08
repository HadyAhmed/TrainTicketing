package com.hadi.trainticketing.passenger.view.features;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityTicketBinding;
import com.hadi.trainticketing.passenger.adapter.TicketHistoryAdapter;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.view.activities.PassengerSignInActivity;

public class TicketActivity extends AppCompatActivity implements TicketHistoryAdapter.OnTicketClickListener {
    private static final String TAG = "TicketActivityTag";
    private ActivityTicketBinding ticketBinding;
    // place holder for adapter holding past tickets
    private TicketHistoryAdapter ticketHistoryAdapter = new TicketHistoryAdapter(this);
    private PassengerViewModel passengerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketBinding = DataBindingUtil.setContentView(this, R.layout.activity_ticket);
        ticketBinding.ticketToolbar.setNavigationOnClickListener(v -> NavUtils.navigateUpFromSameTask(TicketActivity.this));

        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

        passengerViewModel.requestTickets(PreferenceManager.getDefaultSharedPreferences(this).getString(PassengerSignInActivity.USER_ID, ""));

        ticketBinding.previousRv.setAdapter(ticketHistoryAdapter);
        ticketBinding.previousRv.setLayoutManager(new LinearLayoutManager(this));

        fetchAllTickets();
    }

    private void fetchAllTickets() {
        passengerViewModel.getTicketsHistory().observe(this, ticketModels -> {
            ticketBinding.ticketProgress.setVisibility(View.INVISIBLE);
            if (ticketModels != null && !ticketModels.isEmpty()) {
                ticketHistoryAdapter.setTicketHistoryModels(ticketModels);
            } else {
                Toast.makeText(TicketActivity.this, "no tickets were reserved for now", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        passengerViewModel.requestTickets(PreferenceManager.getDefaultSharedPreferences(this).getString(PassengerSignInActivity.USER_ID, ""));
    }

    @Override
    public void sendReservationId(String reservationId) {
        startActivity(new Intent(TicketActivity.this, TicketQrCodeActivity.class).putExtra(TicketQrCodeActivity.RESERVATION_ID_EXTRA, reservationId));
    }
}
