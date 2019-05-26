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
import com.hadi.trainticketing.databinding.ActivityTicketBinding;
import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.passenger.adapter.EnquireAdapter;
import com.hadi.trainticketing.passenger.pojo.enquire.EnquireResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketActivity extends AppCompatActivity {
    private ActivityTicketBinding ticketBinding;
    // place holder for adapter holding past tickets
    private EnquireAdapter enquireAdapter = new EnquireAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ticketBinding = DataBindingUtil.setContentView(this, R.layout.activity_ticket);
        ticketBinding.ticketToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(TicketActivity.this);
            }
        });


        ticketBinding.previousRv.setAdapter(enquireAdapter);
        ticketBinding.previousRv.setLayoutManager(new LinearLayoutManager(this));

        fetchEnquireResults();
    }

    private void fetchEnquireResults() {
        WebServices.serverConnection.create(WebServices.class)
                .getTripEnquire("", "", "", "").enqueue(new Callback<EnquireResponse>() {
            @Override
            public void onResponse(@NonNull Call<EnquireResponse> call, @NonNull Response<EnquireResponse> response) {
                if (response.body() != null) {
                    if (!response.body().getResult().isEmpty()) {
                        enquireAdapter.setResultList(response.body().getResult());
                    } else {
                        Toast.makeText(TicketActivity.this, "No trip was found at the moment", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<EnquireResponse> call, @NonNull Throwable t) {
                Toast.makeText(TicketActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
