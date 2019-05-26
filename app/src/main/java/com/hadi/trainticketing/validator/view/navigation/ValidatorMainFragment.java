package com.hadi.trainticketing.validator.view.navigation;


import android.content.Context;
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
    private FragmentValidatorMainBinding validatorMainBinding;
    private Context context;

    public ValidatorMainFragment() {
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
        validatorMainBinding = FragmentValidatorMainBinding.inflate(inflater, container, false);
        validatorMainBinding.placeHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.moveToScanFragment);
            }
        });
        return validatorMainBinding.getRoot();
    }

}
