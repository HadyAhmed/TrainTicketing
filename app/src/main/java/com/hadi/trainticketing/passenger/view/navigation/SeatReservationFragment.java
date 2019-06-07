package com.hadi.trainticketing.passenger.view.navigation;


import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.databinding.FragmentSeatReservationBinding;
import com.hadi.trainticketing.passenger.adapter.SeatsAdapter;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.model.pojo.reservation.ReservationResponse;
import com.hadi.trainticketing.passenger.model.pojo.reservation.request.ReservationRequest;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.Reservation;
import com.hadi.trainticketing.passenger.view.activities.PassengerSignInActivity;


public class SeatReservationFragment extends Fragment implements SeatsAdapter.OnSeatClickListener {
    private static final String TAG = "SeatReservationTag";
    private Context context;
    private FragmentSeatReservationBinding seatReservationBinding;
    private SeatsAdapter seatsAdapter = new SeatsAdapter(this);
    private PassengerViewModel passengerViewModel;
    private String uid;
    private String trainId;
    private String[] reservationIds;
    private String ticketId;

    public SeatReservationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        seatReservationBinding = FragmentSeatReservationBinding.inflate(inflater, container, false);

        seatReservationBinding.seatsRv.setAdapter(seatsAdapter);

        if (getArguments() != null) {
            trainId = SeatReservationFragmentArgs.fromBundle(getArguments()).getTrainId();
            reservationIds = SeatReservationFragmentArgs.fromBundle(getArguments()).getReservationId();
            ticketId = SeatReservationFragmentArgs.fromBundle(getArguments()).getTicketId();
            uid = PreferenceManager.getDefaultSharedPreferences(context).getString(PassengerSignInActivity.USER_ID, "");
            passengerViewModel.getReservationSeats(trainId, reservationIds)
                    .observe(getViewLifecycleOwner(), new Observer<ReservationResponse>() {
                        @Override
                        public void onChanged(ReservationResponse reservationResponse) {
                            seatReservationBinding.loadingSeats.setVisibility(View.INVISIBLE);
                            if (reservationResponse != null) {
                                seatsAdapter.setSeatList(reservationResponse.getAvailableSeats());
                            }
                        }
                    });
        }
        return seatReservationBinding.getRoot();
    }

    @Override
    public void onSeatClick(View view, String seatId) {
        for (String ids : reservationIds) {
            Log.d(TAG, "onSeatClick: " + ids);
        }
        Log.d(TAG, "onSeatClick: uid " + uid + " ticketId: " + ticketId + " seatId " + seatId + " trainId " + trainId + " reservation ids: " + reservationIds.length);

        ReservationRequest reservation = new ReservationRequest(uid, ticketId, reservationIds, seatId);
        passengerViewModel.getReservationResult(reservation).observe(getViewLifecycleOwner(), new Observer<Reservation>() {
            @Override
            public void onChanged(Reservation reservation) {
                if (reservation != null) {
                    Toast.makeText(context, "reservation successfully done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "seat was already reserved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

