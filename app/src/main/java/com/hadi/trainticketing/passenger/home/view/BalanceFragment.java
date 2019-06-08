package com.hadi.trainticketing.passenger.home.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentBalanceBinding;
import com.hadi.trainticketing.passenger.home.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.home.view.activities.AddCreditCardActivity;
import com.hadi.trainticketing.passenger.login.view.PassengerSignInActivity;

public class BalanceFragment extends Fragment {
    private PassengerViewModel passengerViewModel;
    private FragmentBalanceBinding balanceBinding;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);
        passengerViewModel.requestUserInfo(PreferenceManager.getDefaultSharedPreferences(context).getString(PassengerSignInActivity.USER_TOKEN, ""));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        balanceBinding = FragmentBalanceBinding.inflate(inflater, container, false);

        passengerViewModel.getUserInfo().observe(this, userResponse -> {
            balanceBinding.balanceProgress.setVisibility(View.INVISIBLE);
            if (userResponse != null) {
                balanceBinding.userBalanceTv.setText(String.format(getString(R.string.creditEg), userResponse.getResult().getBalance()));
            }
        });

        balanceBinding.materialButton.setOnClickListener(v -> startActivity(new Intent(context, AddCreditCardActivity.class)));

        return balanceBinding.getRoot();
    }

}
