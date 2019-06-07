package com.hadi.trainticketing.passenger.view.features;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ReserveActivityBinding;

public class ReserveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReserveActivityBinding layoutBinding = DataBindingUtil.setContentView(this, R.layout.reserve_activity);

        layoutBinding.enquireToolbar.setTitle("Book Ticket");

        layoutBinding.enquireToolbar.setNavigationOnClickListener(v -> NavUtils.navigateUpFromSameTask(ReserveActivity.this));

    }
}
