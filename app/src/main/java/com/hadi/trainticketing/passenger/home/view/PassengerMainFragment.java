package com.hadi.trainticketing.passenger.home.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentPassengerMainBinding;
import com.hadi.trainticketing.datasource.database.StaticDataSource;
import com.hadi.trainticketing.passenger.home.adapter.WelcomeAdapter;

public class PassengerMainFragment extends Fragment implements WelcomeAdapter.OnWelcomeItemClickListener {
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPassengerMainBinding passengerMainBinding = FragmentPassengerMainBinding.inflate(inflater, container, false);

        passengerMainBinding.passengerFeaturesRv.setAdapter(new WelcomeAdapter(StaticDataSource.getPassengerCardsList(), this));
        return passengerMainBinding.getRoot();
    }

    @Override
    public void onWelcomeItemClick(View view, int itemPosition) {
        if (itemPosition == StaticDataSource.ENQUIRE_ITEM) {
            Navigation.findNavController(view).navigate(R.id.action_passengerMainFragment_to_enquireFragment);
        } else if (itemPosition == StaticDataSource.RESERVE_ITEM) {
            Navigation.findNavController(view).navigate(R.id.action_passengerMainFragment_to_reservationFragment);
        } else if (itemPosition == StaticDataSource.BALANCE_ITEM) {
            Navigation.findNavController(view).navigate(R.id.action_passengerMainFragment_to_balanceFragment);
        } else if (itemPosition == StaticDataSource.TICKET_ITEM) {
            Navigation.findNavController(view).navigate(R.id.action_passengerMainFragment_to_ticketFragment);
        } else if (itemPosition == StaticDataSource.HISTORY_ITEM) {
            Toast.makeText(context, "History", Toast.LENGTH_SHORT).show();
        }
    }
}
