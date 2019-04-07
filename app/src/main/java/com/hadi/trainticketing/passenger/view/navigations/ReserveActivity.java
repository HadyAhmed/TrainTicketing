package com.hadi.trainticketing.passenger.view.navigations;

import android.os.Bundle;
import android.view.View;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityReserveBinding;
import com.hadi.trainticketing.passenger.view.fragments.ReservationFirstPhase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;

public class ReserveActivity extends AppCompatActivity {
    private ActivityReserveBinding reserveBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reserveBinding = DataBindingUtil.setContentView(this, R.layout.activity_reserve);
        reserveBinding.reserveToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(ReserveActivity.this);
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.enquire_fragment_container, new ReservationFirstPhase())
                .commit();
    }
}
