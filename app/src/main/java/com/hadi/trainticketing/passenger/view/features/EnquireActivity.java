package com.hadi.trainticketing.passenger.view.features;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.EnquireLayoutBinding;
import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.passenger.adapter.EnquireAdapter;
import com.hadi.trainticketing.passenger.pojo.enquire.EnquireResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnquireActivity extends AppCompatActivity implements View.OnClickListener {
    private EnquireLayoutBinding layoutBinding;
    private EnquireAdapter enquireAdapter = new EnquireAdapter();

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

        layoutBinding.enquireRv.setLayoutManager(new LinearLayoutManager(this));
        layoutBinding.enquireRv.setAdapter(enquireAdapter);

        layoutBinding.searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        fetchEnquireResults();
    }

    private void fetchEnquireResults() {
        layoutBinding.searchProgress.setVisibility(View.VISIBLE);
        WebServices.serverConnection.create(WebServices.class)
                .getTripEnquire(layoutBinding.spinnerFrom.getSelectedItem().toString(),
                        layoutBinding.spinnerTo.getSelectedItem().toString(),
                        getDateChosen(),
                        getClassChosen()
                ).enqueue(new Callback<EnquireResponse>() {
            @Override
            public void onResponse(@NonNull Call<EnquireResponse> call, @NonNull Response<EnquireResponse> response) {
                layoutBinding.searchProgress.setVisibility(View.INVISIBLE);
                if (response.body() != null) {
                    if (!response.body().getResult().isEmpty()) {
                        enquireAdapter.setResultList(response.body().getResult());
                    } else {
                        Toast.makeText(EnquireActivity.this, "No trip was found at the moment", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<EnquireResponse> call, @NonNull Throwable t) {
                layoutBinding.searchProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(EnquireActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getDateChosen() {
        switch (layoutBinding.timeRadioGroup.getCheckedRadioButtonId()) {
            case R.id.all_time_radio_btn:
                return "";
            case R.id.am_radio_btn:
                return "am";
            default:
                return "pm";
        }
    }

    private String getClassChosen() {
        switch (layoutBinding.classRadioGroup.getCheckedRadioButtonId()) {
            case R.id.class_all_btn:
                return "";
            case R.id.class_a_radio_btn:
                return "1";
            default:
                return "2";
        }
    }

}
