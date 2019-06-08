package com.hadi.trainticketing.passenger.home.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hadi.trainticketing.databinding.FragmentReservationMainBinding;

public class ReservationMainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("RESERVATION", "onCreateView: main reservation fragment");
        FragmentReservationMainBinding layoutBinding = FragmentReservationMainBinding.inflate(inflater, container, false);

        return layoutBinding.getRoot();
    }
}
