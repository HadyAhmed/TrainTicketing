package com.hadi.trainticketing.passenger.view.navigation;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.databinding.FragmentSeatReservationBinding;
import com.hadi.trainticketing.passenger.adapter.SeatsAdapter;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.model.pojo.reservation.request.ReservationRequest;
import com.hadi.trainticketing.passenger.view.activities.PassengerSignInActivity;
import com.hadi.trainticketing.passenger.view.features.TicketActivity;

import java.util.Random;


public class SeatReservationFragment extends Fragment implements SeatsAdapter.OnSeatClickListener {
    private static final String TAG = "SeatReservationFragment";
    private Context context;
    private FragmentSeatReservationBinding seatReservationBinding;
    private SeatsAdapter seatsAdapter = new SeatsAdapter(this);
    private PassengerViewModel passengerViewModel;
    private String uid;
    private String trainId;
    private String[] reservationIds;
    private String ticketId;
    private int classType;

    public SeatReservationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);
        passengerViewModel.requestUserInfo(PreferenceManager.getDefaultSharedPreferences(context).getString(PassengerSignInActivity.USER_TOKEN, ""));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        seatReservationBinding = FragmentSeatReservationBinding.inflate(inflater, container, false);

        seatReservationBinding.seatsRv.setAdapter(seatsAdapter);

        if (getArguments() != null) {
            trainId = SeatReservationFragmentArgs.fromBundle(getArguments()).getSeatRequest().getTrainId();
            reservationIds = SeatReservationFragmentArgs.fromBundle(getArguments()).getSeatRequest().getReservationId();
            classType = SeatReservationFragmentArgs.fromBundle(getArguments()).getSeatRequest().getClassType();
            ticketId = SeatReservationFragmentArgs.fromBundle(getArguments()).getSeatRequest().getTicketId();
            uid = PreferenceManager.getDefaultSharedPreferences(context).getString(PassengerSignInActivity.USER_ID, "");

            observeTickets();
        }
        return seatReservationBinding.getRoot();
    }


    private void observeTickets() {
        passengerViewModel.getReservationSeats(classType, trainId, reservationIds)
                .observe(getViewLifecycleOwner(), reservationResponse -> {
                    seatReservationBinding.loadingSeats.setVisibility(View.INVISIBLE);
                    if (reservationResponse != null && !reservationResponse.getAvailableSeats().isEmpty()) {
                        seatsAdapter.setSeatList(reservationResponse.getAvailableSeats());
                    } else {
                        Toast.makeText(context, "all seats are completed", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onSeatClick(View view, final String seatId, int seatNumber) {
        AlertDialog.Builder confirmTicketDialog = new AlertDialog.Builder(context);
        confirmTicketDialog.setTitle("Confirm Seat")
                .setMessage("Are you sure you want to confirm reservation to seat: " + seatNumber)
                .setPositiveButton("Yes", (dialog, which) -> {
                    seatReservationBinding.loadingSeats.setVisibility(View.VISIBLE);
                    final ReservationRequest reservation = new ReservationRequest(uid, ticketId, reservationIds, seatId);
                    Log.d(TAG, "onSeatClick: " + reservation.toString());
                    new Handler().postDelayed(() -> passengerViewModel.getReservationResult(reservation).observe(getViewLifecycleOwner(), reservation1 -> {
                        seatReservationBinding.loadingSeats.setVisibility(View.INVISIBLE);
                        if (reservation1 != null) {
                            Toast.makeText(context, "reservation successfully done", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, TicketActivity.class));
                            if (getActivity() != null) {
                                getActivity().finish();
                            }
                        } else {
                            Toast.makeText(context, "seat was already reserved", Toast.LENGTH_SHORT).show();
                        }
                    }), new Random().nextInt(2000) * 2);
                })
                .setNeutralButton("Cancel", null)
                .show();
    }
}

