package com.hadi.trainticketing.passenger.view.navigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hadi.trainticketing.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmTicket extends Fragment {


    public ConfirmTicket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_ticket, container, false);
    }

}
