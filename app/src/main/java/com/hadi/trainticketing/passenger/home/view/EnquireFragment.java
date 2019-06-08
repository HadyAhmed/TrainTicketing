package com.hadi.trainticketing.passenger.home.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentEnquireLayoutBinding;
import com.hadi.trainticketing.passenger.home.adapter.EnquireAdapter;
import com.hadi.trainticketing.passenger.home.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.home.model.pojo.enquire.ArrayResult;
import com.hadi.trainticketing.passenger.home.model.pojo.enquire.TicketModel;
import com.hadi.trainticketing.passenger.home.model.pojo.stations.Station;
import com.hadi.trainticketing.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EnquireFragment extends Fragment implements View.OnClickListener {
    private FragmentEnquireLayoutBinding layoutBinding;
    private EnquireAdapter enquireAdapter = new EnquireAdapter(null);
    private PassengerViewModel passengerViewModel;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);
        passengerViewModel.requestStations();
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutBinding = FragmentEnquireLayoutBinding.inflate(inflater, container, false);

        observeStations();

        layoutBinding.enquireRv.setAdapter(enquireAdapter);

        layoutBinding.searchBtn.setOnClickListener(this);

        return layoutBinding.getRoot();
    }

    private void observeStations() {
        passengerViewModel.getStations().observe(this, stations -> {
            layoutBinding.searchProgress.setVisibility(View.INVISIBLE);
            if (stations != null) {
                List<String> stringList = new ArrayList<>();
                for (Station s : stations) {
                    stringList.add(s.getName());
                }
                ArrayAdapter<String> stationsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, stringList);
                layoutBinding.spinnerFrom.setAdapter(stationsAdapter);
                layoutBinding.spinnerTo.setAdapter(stationsAdapter);
            } else {
                Toast.makeText(context, "something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (Utils.isNetworkAvailable(context)) {
            if (layoutBinding.spinnerFrom.getSelectedItem() == null) {
                Toast.makeText(context, "Please Select Station", Toast.LENGTH_SHORT).show();
            } else {
                fetchEnquireResults();
            }
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(context, "no trip was found", Toast.LENGTH_SHORT).show();
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
