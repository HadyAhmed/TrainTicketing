package com.hadi.trainticketing.passenger.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hadi.trainticketing.databinding.ReserveLayoutBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReservationFirstPhase extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ReserveLayoutBinding layoutBinding = ReserveLayoutBinding.inflate(inflater, container, false);
        return layoutBinding.getRoot();
    }
}
