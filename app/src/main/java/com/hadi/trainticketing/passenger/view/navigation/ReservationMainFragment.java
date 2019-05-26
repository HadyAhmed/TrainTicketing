package com.hadi.trainticketing.passenger.view.navigation;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentReservationMainBinding;
import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.passenger.adapter.EnquireAdapter;
import com.hadi.trainticketing.passenger.pojo.enquire.EnquireResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationMainFragment extends Fragment implements View.OnClickListener, EnquireAdapter.OnTicketClickListener {
    private FragmentReservationMainBinding reservationMainBinding;
    private EnquireAdapter enquireAdapter = new EnquireAdapter(this);
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reservationMainBinding = FragmentReservationMainBinding.inflate(inflater, container, false);

        reservationMainBinding.enquireRv.setLayoutManager(new LinearLayoutManager(context));
        reservationMainBinding.enquireRv.setAdapter(enquireAdapter);

        reservationMainBinding.searchBtn.setOnClickListener(this);

        return reservationMainBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        fetchEnquireResults();
    }

    private void fetchEnquireResults() {
        reservationMainBinding.searchProgress.setVisibility(View.VISIBLE);
        WebServices.serverConnection.create(WebServices.class)
                .getTripEnquire(reservationMainBinding.spinnerFrom.getSelectedItem().toString(),
                        reservationMainBinding.spinnerTo.getSelectedItem().toString(),
                        getDateChosen(),
                        getClassChosen()
                ).enqueue(new Callback<EnquireResponse>() {
            @Override
            public void onResponse(@NonNull Call<EnquireResponse> call, @NonNull Response<EnquireResponse> response) {
                reservationMainBinding.searchProgress.setVisibility(View.INVISIBLE);
                if (response.body() != null) {
                    if (!response.body().getResult().isEmpty()) {
                        enquireAdapter.setResultList(response.body().getResult());
                    } else {
                        Toast.makeText(context, "No trip was found at the moment", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<EnquireResponse> call, @NonNull Throwable t) {
                reservationMainBinding.searchProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getDateChosen() {
        switch (reservationMainBinding.timeRadioGroup.getCheckedRadioButtonId()) {
            case R.id.all_time_radio_btn:
                return "";
            case R.id.am_radio_btn:
                return "am";
            default:
                return "pm";
        }
    }

    private String getClassChosen() {
        switch (reservationMainBinding.classRadioGroup.getCheckedRadioButtonId()) {
            case R.id.class_all_btn:
                return "";
            case R.id.class_a_radio_btn:
                return "1";
            default:
                return "2";
        }
    }

    @Override
    public void onTicketClick(String id, View view) {
        Toast.makeText(context, "ticket id: " + id, Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.moveToSeatFragment);

    }
}
