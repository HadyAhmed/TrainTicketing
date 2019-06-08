package com.hadi.trainticketing.passenger.home.view;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.hadi.trainticketing.databinding.FragmentTicketBinding;
import com.hadi.trainticketing.passenger.home.adapter.TicketHistoryAdapter;
import com.hadi.trainticketing.passenger.home.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.home.model.pojo.ticket.TicketHistoryModel;
import com.hadi.trainticketing.passenger.login.view.PassengerSignInActivity;

import java.util.ArrayList;
import java.util.List;

public class TicketFragment extends Fragment implements TicketHistoryAdapter.OnTicketClickListener {
    private static final String TAG = "TicketActivityTag";
    private FragmentTicketBinding ticketBinding;
    // place holder for adapter holding past tickets
    private TicketHistoryAdapter validTicketAdapter = new TicketHistoryAdapter(this);
    private TicketHistoryAdapter invalidTicketAdapter = new TicketHistoryAdapter(this);
    private PassengerViewModel passengerViewModel;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);
        passengerViewModel.requestTickets(PreferenceManager.getDefaultSharedPreferences(context).getString(PassengerSignInActivity.USER_ID, ""));
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ticketBinding = FragmentTicketBinding.inflate(inflater, container, false);

        Log.d(TAG, "onCreateView: ");
        ticketBinding.validTicketRv.setAdapter(validTicketAdapter);
        ticketBinding.validTicketRv.setNestedScrollingEnabled(false);
        ticketBinding.previousTicketRv.setAdapter(invalidTicketAdapter);
        ticketBinding.previousTicketRv.setNestedScrollingEnabled(false);

        fetchAllTickets();

        return ticketBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        passengerViewModel.requestTickets(PreferenceManager.getDefaultSharedPreferences(context).getString(PassengerSignInActivity.USER_ID, ""));
    }

    private void fetchAllTickets() {
        passengerViewModel.getTicketsHistory().observe(this, ticketModels -> {
            ticketBinding.ticketProgress.setVisibility(View.INVISIBLE);
            if (ticketModels != null && !ticketModels.isEmpty()) {
                ticketBinding.contentLayout.setVisibility(View.VISIBLE);
                List<TicketHistoryModel> validTickets = new ArrayList<>();
                List<TicketHistoryModel> invalidTickets = new ArrayList<>();
                for (int i = 0; i < ticketModels.size(); i++) {
                    if (!ticketModels.get(i).isValidated()) {
                        validTickets.add(ticketModels.get(i));
                    } else {
                        invalidTickets.add(ticketModels.get(i));
                    }
                }
                if (invalidTickets.isEmpty()) {
                    ticketBinding.invalidTicketsTv.setText("no previous tickets available");
                }
                if (validTickets.isEmpty()) {
                    ticketBinding.validTicketTv.setText("no recent tickets available");
                }
                validTicketAdapter.setTicketHistoryModels(validTickets);
                invalidTicketAdapter.setTicketHistoryModels(invalidTickets);

            } else {
                Toast.makeText(context, "no tickets were reserved for now", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void sendReservationId(View v, TicketHistoryModel historyModel, String reservationId) {
        if (!historyModel.isValidated()) {
            Navigation.findNavController(v).navigate(TicketFragmentDirections.actionTicketFragmentToTicketQrCodeFragment(reservationId, historyModel));
        } else {
            Toast.makeText(context, "this ticket is already validated", Toast.LENGTH_SHORT).show();
        }
    }
}
