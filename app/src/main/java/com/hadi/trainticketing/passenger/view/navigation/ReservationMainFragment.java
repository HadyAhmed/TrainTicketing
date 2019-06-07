package com.hadi.trainticketing.passenger.view.navigation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentReservationMainBinding;
import com.hadi.trainticketing.passenger.adapter.EnquireAdapter;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.model.pojo.enquire.ArrayResult;
import com.hadi.trainticketing.passenger.model.pojo.enquire.ResultArray;
import com.hadi.trainticketing.passenger.model.pojo.enquire.TicketModel;
import com.hadi.trainticketing.passenger.model.pojo.stations.Station;
import com.hadi.trainticketing.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReservationMainFragment extends Fragment implements View.OnClickListener, EnquireAdapter.OnTicketClickListener {
    private FragmentReservationMainBinding reservationMainBinding;
    private EnquireAdapter enquireAdapter = new EnquireAdapter(this);
    private Context context;
    private static final String TAG = "ReservationMainFragment";
    private PassengerViewModel passengerViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);
        passengerViewModel.requestStations();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reservationMainBinding = FragmentReservationMainBinding.inflate(inflater, container, false);

        reservationMainBinding.enquireRv.setLayoutManager(new LinearLayoutManager(context));
        reservationMainBinding.enquireRv.setAdapter(enquireAdapter);

        reservationMainBinding.searchBtn.setOnClickListener(this);

        passengerViewModel.getStations().observe(this, new Observer<List<Station>>() {
            @Override
            public void onChanged(List<Station> stations) {
                reservationMainBinding.searchProgress.setVisibility(View.INVISIBLE);
                if (stations != null) {
                    List<String> stringList = new ArrayList<>();
                    for (Station s : stations) {
                        stringList.add(s.getName());
                    }
                    ArrayAdapter<String> stationsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, stringList);
                    reservationMainBinding.spinnerFrom.setAdapter(stationsAdapter);
                    reservationMainBinding.spinnerTo.setAdapter(stationsAdapter);
                }
            }
        });
        return reservationMainBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (Utils.isNetworkAvailable(context)) {
            if (reservationMainBinding.spinnerFrom.getSelectedItem() == null) {
                Toast.makeText(context, "Please Select Station", Toast.LENGTH_SHORT).show();
            } else {
                fetchEnquireResults();
            }
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    private void fetchEnquireResults() {
        reservationMainBinding.searchProgress.setVisibility(View.VISIBLE);
        passengerViewModel.getEnquireSearchResult(reservationMainBinding.spinnerFrom.getSelectedItem().toString(),
                reservationMainBinding.spinnerTo.getSelectedItem().toString(),
                getDateChosen(),
                getClassChosen())
                .observe(this, new Observer<List<ResultArray>>() {
                    @Override
                    public void onChanged(List<ResultArray> resultArrays) {
                        reservationMainBinding.searchProgress.setVisibility(View.INVISIBLE);
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
    public void onTicketClick(View view, String ticketId, String trainId, List<ArrayResult> arrayResults) {
        Log.d(TAG, "onTicketClick: " + trainId + " reservation ids: " + arrayResults.size());
        List<String> stringList = new ArrayList<>();
        for (ArrayResult ar : arrayResults) {
            stringList.add(ar.getId());
        }
        String[] ids = stringList.toArray(new String[0]);
        Navigation.findNavController(view).navigate(ReservationMainFragmentDirections.moveToSeatFragment(trainId, ticketId, ids));
    }
}
