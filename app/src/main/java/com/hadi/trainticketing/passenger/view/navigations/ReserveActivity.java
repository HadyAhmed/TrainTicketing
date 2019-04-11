package com.hadi.trainticketing.passenger.view.navigations;

import android.os.Bundle;
import android.view.View;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.EnquireLayoutBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;

public class ReserveActivity extends AppCompatActivity {
    private EnquireLayoutBinding reserveBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reserveBinding = DataBindingUtil.setContentView(this, R.layout.enquire_layout);
        reserveBinding.enquireToolbar.setTitle("Book Ticket");
        reserveBinding.enquireToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(ReserveActivity.this);
            }
        });

    }
}
