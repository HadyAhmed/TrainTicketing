package com.hadi.trainticketing.passenger.view.navigation;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentSeatReservationBinding;
import com.hadi.trainticketing.passenger.adapter.SeatsAdapter;

import java.util.HashMap;
import java.util.Map;


public class SeatReservationFragment extends Fragment implements SeatsAdapter.OnSeatClickListener {
    private static final String TAG = "SeatReservationTag";
    private Map<Integer, Integer> seats = new HashMap<>();
    private Context context;
    private FragmentSeatReservationBinding seatReservationBinding;
    private SeatsAdapter adapter = new SeatsAdapter(this);

    public SeatReservationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        seatReservationBinding = FragmentSeatReservationBinding.inflate(inflater, container, false);

        seatReservationBinding.seatsRv.setLayoutManager(new GridLayoutManager(context, 2));
        seatReservationBinding.seatsRv.setItemViewCacheSize(20);
        seatReservationBinding.seatsRv.setAdapter(adapter);

        seatReservationBinding.continueReservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seats.isEmpty()) {
                    Toast.makeText(context, "you must choose seats to reserve", Toast.LENGTH_SHORT).show();
                } else if (seats.size() == 1) {
                    Navigation.findNavController(v).navigate(R.id.moveToTicketDetail);
                } else {
                    Bundle ticketNumbers = new Bundle();
                    ticketNumbers.putInt("ticketNumber", seats.size());
                    Navigation.findNavController(v).navigate(R.id.moveToTicketHolderNames, ticketNumbers);
                }
            }
        });

        return seatReservationBinding.getRoot();
    }

    @Override
    public void onSeatClick(View view, int seatId) {
        // TODO: 4/20/2019 Change the click listener impl here
        ToggleButton button = (ToggleButton) view;
        if (!button.isChecked()) {
            seats.put(seatId, seatId);
            Toast.makeText(context, String.format("seat number %d was added", seatId), Toast.LENGTH_SHORT).show();
            button.setChecked(false);
        } else {
            if (seats.containsKey(seatId)) {
                seats.remove(seatId);
                Toast.makeText(context, String.format("seat number %d was removed", seatId), Toast.LENGTH_SHORT).show();
                button.setChecked(true);
            }
        }
    }

}

