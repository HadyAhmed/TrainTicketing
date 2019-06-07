package com.hadi.trainticketing.validator.view.navigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentValidatorMainBinding;

public class ValidatorMainFragment extends Fragment {

    public ValidatorMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentValidatorMainBinding validatorMainBinding = FragmentValidatorMainBinding.inflate(inflater, container, false);
        validatorMainBinding.placeHolder.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.moveToScanFragment));
        return validatorMainBinding.getRoot();
    }

}
