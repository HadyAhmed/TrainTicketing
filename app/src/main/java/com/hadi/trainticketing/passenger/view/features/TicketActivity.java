package com.hadi.trainticketing.passenger.view.features;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityTicketBinding;
import com.hadi.trainticketing.passenger.adapter.EnquireAdapter;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.model.pojo.enquire.TicketModel;
import com.hadi.trainticketing.passenger.view.activities.PassengerSignInActivity;

import java.util.List;

public class TicketActivity extends AppCompatActivity {
    private static final String TAG = "TicketActivityTag";
    private ActivityTicketBinding ticketBinding;
    // place holder for adapter holding past tickets
    private EnquireAdapter enquireAdapter = new EnquireAdapter(null);
    private PassengerViewModel passengerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketBinding = DataBindingUtil.setContentView(this, R.layout.activity_ticket);
        ticketBinding.ticketToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(TicketActivity.this);
            }
        });

        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

        passengerViewModel.requestTickets(PreferenceManager.getDefaultSharedPreferences(this).getString(PassengerSignInActivity.USER_ID, ""));

        ticketBinding.previousRv.setAdapter(enquireAdapter);
        ticketBinding.previousRv.setLayoutManager(new LinearLayoutManager(this));

        fetchAllTickets();
    }

    private void fetchAllTickets() {
        passengerViewModel.getTicketsHistory().observe(this, new Observer<List<TicketModel>>() {
            @Override
            public void onChanged(List<TicketModel> ticketModels) {
                if (ticketModels != null) {
                    for (TicketModel ticket : ticketModels) {
                        Log.d(TAG, "onChanged: " + ticket.getTicket().getFrom());
                    }
                }
            }
        });
    }

}
