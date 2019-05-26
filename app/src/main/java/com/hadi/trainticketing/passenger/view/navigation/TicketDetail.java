package com.hadi.trainticketing.passenger.view.navigation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.passenger.view.features.TicketActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TicketDetail extends Fragment {
    private Context context;

    public TicketDetail() {
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context, TicketActivity.class));
            }
        }, 3000);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_detail, container, false);
    }

}
