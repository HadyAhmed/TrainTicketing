package com.hadi.trainticketing.passenger.view.navigations;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.EnquireLayoutBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;

public class EnquireActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EnquireActivity";
    private EnquireLayoutBinding layoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.enquire_layout);
        layoutBinding.enquireToolbar.setTitle("Enquire");
        layoutBinding.enquireToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(EnquireActivity.this);
            }
        });

        layoutBinding.searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        fetchAllTripsResult();
    }

    private void fetchAllTripsResult() {
        ProgressDialog resultProgressDialog = new ProgressDialog(this);
        resultProgressDialog.setMessage("Searching");
        resultProgressDialog.show();
        resultProgressDialog.setCancelable(false);

        // TODO: 2019-03-21 get the result back
    }
}
