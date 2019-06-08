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
import com.hadi.trainticketing.passenger.login.view.PassengerSignInActivity;

public class TicketFragment extends Fragment implements TicketHistoryAdapter.OnTicketClickListener {
    private static final String TAG = "TicketActivityTag";
    private FragmentTicketBinding ticketBinding;
    // place holder for adapter holding past tickets
    private TicketHistoryAdapter ticketHistoryAdapter = new TicketHistoryAdapter(this);
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

        ticketBinding.previousRv.setAdapter(ticketHistoryAdapter);

        fetchAllTickets();

        return ticketBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        passengerViewModel.requestTickets(PreferenceManager.getDefaultSharedPreferences(context).getString(PassengerSignInActivity.USER_ID, ""));
    }

    private void fetchAllTickets() {
        passengerViewModel.getTicketsHistory().observe(this, ticketModels -> {
            ticketBinding.ticketProgress.setVisibility(View.INVISIBLE);
            if (ticketModels != null && !ticketModels.isEmpty()) {
                ticketHistoryAdapter.setTicketHistoryModels(ticketModels);
            } else {
                Toast.makeText(context, "no tickets were reserved for now", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void sendReservationId(View v, boolean validTicket, String reservationId) {
        if (!validTicket) {
            Navigation.findNavController(v).navigate(TicketFragmentDirections.actionTicketFragmentToTicketQrCodeFragment(reservationId));
        } else {
            Toast.makeText(context, "this ticket is already validated", Toast.LENGTH_SHORT).show();
        }
    }
}
