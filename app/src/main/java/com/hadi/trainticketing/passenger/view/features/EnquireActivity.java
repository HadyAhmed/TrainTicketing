package com.hadi.trainticketing.passenger.view.features;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.EnquireLayoutBinding;
import com.hadi.trainticketing.passenger.adapter.EnquireAdapter;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.model.pojo.enquire.ArrayResult;
import com.hadi.trainticketing.passenger.model.pojo.enquire.TicketModel;
import com.hadi.trainticketing.passenger.model.pojo.stations.Station;
import com.hadi.trainticketing.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EnquireActivity extends AppCompatActivity implements View.OnClickListener {
    private EnquireLayoutBinding layoutBinding;
    private EnquireAdapter enquireAdapter = new EnquireAdapter(null);
    private PassengerViewModel passengerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.enquire_layout);
        layoutBinding.enquireToolbar.setTitle("Enquire");
        layoutBinding.enquireToolbar.setNavigationOnClickListener(v -> NavUtils.navigateUpFromSameTask(EnquireActivity.this));

        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

        passengerViewModel.requestStations();

        passengerViewModel.getStations().observe(this, stations -> {
            layoutBinding.searchProgress.setVisibility(View.INVISIBLE);
            if (stations != null) {
                List<String> stringList = new ArrayList<>();
                for (Station s : stations) {
                    stringList.add(s.getName());
                }
                ArrayAdapter<String> stationsAdapter = new ArrayAdapter<>(EnquireActivity.this, android.R.layout.simple_spinner_dropdown_item, stringList);
                layoutBinding.spinnerFrom.setAdapter(stationsAdapter);
                layoutBinding.spinnerTo.setAdapter(stationsAdapter);
            }
        });


        layoutBinding.enquireRv.setLayoutManager(new LinearLayoutManager(this));
        layoutBinding.enquireRv.setAdapter(enquireAdapter);

        layoutBinding.searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Utils.isNetworkAvailable(EnquireActivity.this)) {
            if (layoutBinding.spinnerFrom.getSelectedItem() == null) {
                Toast.makeText(EnquireActivity.this, "Please Select Station", Toast.LENGTH_SHORT).show();
            } else {
                fetchEnquireResults();
            }
        } else {
            Toast.makeText(EnquireActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchEnquireResults() {
        layoutBinding.searchProgress.setVisibility(View.VISIBLE);
        passengerViewModel.getEnquireSearchResult(layoutBinding.spinnerFrom.getSelectedItem().toString(),
                layoutBinding.spinnerTo.getSelectedItem().toString(),
                getDateChosen(),
                getClassChosen())
                .observe(this, resultArrays -> {
                    layoutBinding.searchProgress.setVisibility(View.INVISIBLE);
                    List<TicketModel> ticketsModels = new ArrayList<>();

                    SparseArray<List<ArrayResult>> justTrip = new SparseArray<>();

                    if (resultArrays != null && !resultArrays.isEmpty() && !resultArrays.get(0).getTickets().isEmpty()) {
                        for (int i = 0; i < resultArrays.size(); i++) {
                            justTrip.append(i, resultArrays.get(i).getArrayResult());
                            for (int j = 0; j < resultArrays.get(i).getTickets().size(); j++) {
                                ticketsModels.add(new TicketModel(resultArrays.get(i).getArrivalTime(), resultArrays.get(i).getEndTime(), resultArrays.get(i).getTickets().get(j), resultArrays.get(i).getArrayResult()));
                            }
                        }
                        enquireAdapter.setTicketResults(ticketsModels);
                    } else {
                        Toast.makeText(EnquireActivity.this, "no trip was found", Toast.LENGTH_SHORT).show();
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
