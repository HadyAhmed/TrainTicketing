package com.hadi.trainticketing.passenger.home.view;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentReservationEnquireBinding;
import com.hadi.trainticketing.passenger.home.adapter.EnquireAdapter;
import com.hadi.trainticketing.passenger.home.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.home.model.pojo.enquire.ArrayResult;
import com.hadi.trainticketing.passenger.home.model.pojo.enquire.TicketModel;
import com.hadi.trainticketing.passenger.home.model.pojo.reservation.response.seats.SeatRequest;
import com.hadi.trainticketing.passenger.home.model.pojo.stations.Station;
import com.hadi.trainticketing.passenger.login.view.PassengerSignInActivity;
import com.hadi.trainticketing.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReservationEnquireFragment extends Fragment implements View.OnClickListener, EnquireAdapter.OnTicketClickListener {
    private static final String TAG = "ReservationEnquireTag";
    private EnquireAdapter enquireAdapter = new EnquireAdapter(this);
    private Context context;
    private FragmentReservationEnquireBinding reservationMainBinding;
    private PassengerViewModel passengerViewModel;
    private Integer userBalance;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);
        passengerViewModel.requestStations();
        passengerViewModel.requestUserInfo(PreferenceManager.getDefaultSharedPreferences(context).getString(PassengerSignInActivity.USER_TOKEN, ""));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reservationMainBinding = FragmentReservationEnquireBinding.inflate(inflater, container, false);

        reservationMainBinding.enquireRv.setAdapter(enquireAdapter);

        reservationMainBinding.searchBtn.setOnClickListener(this);

        observeUserInfo();

        observeSearchResult();

        return reservationMainBinding.getRoot();
    }

    private void observeSearchResult() {
        Log.d(TAG, "observeSearchResult: getting stations");
        passengerViewModel.getStations().observe(this, stations -> {
            reservationMainBinding.searchProgress.setVisibility(View.INVISIBLE);
            if (stations != null) {
                List<String> stringList = new ArrayList<>();
                for (Station s : stations) {
                    stringList.add(s.getName());
                }
                ArrayAdapter<String> stationsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, stringList);
                reservationMainBinding.spinnerFrom.setAdapter(stationsAdapter);
                reservationMainBinding.spinnerTo.setAdapter(stationsAdapter);
            } else {
                Toast.makeText(context, "something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });
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

    private void observeUserInfo() {
        passengerViewModel.getUserInfo().observe(getViewLifecycleOwner(), userResponse -> {
            if (userResponse != null) {
                userBalance = userResponse.getResult().getBalance();
            }
        });
    }

    private void fetchEnquireResults() {
        reservationMainBinding.searchProgress.setVisibility(View.VISIBLE);
        Log.d(TAG, "fetchEnquireResults: " + reservationMainBinding.spinnerFrom.getSelectedItem().toString() +
                reservationMainBinding.spinnerTo.getSelectedItem().toString() +
                getDateChosen() +
                getClassChosen());
        passengerViewModel.getEnquireSearchResult(reservationMainBinding.spinnerFrom.getSelectedItem().toString(),
                reservationMainBinding.spinnerTo.getSelectedItem().toString(),
                getDateChosen(),
                getClassChosen())
                .observe(this, resultArrays -> {
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
    public void onTicketClick(final View view, final int classType, final int ticketPrice, final String ticketId, final String trainId, List<ArrayResult> arrayResults) {
        if (userBalance != 0 && userBalance >= ticketPrice) {
            List<String> stringList = new ArrayList<>();
            for (ArrayResult ar : arrayResults) {
                stringList.add(ar.getId());
            }
            final SeatRequest seatRequest = new SeatRequest(classType, trainId, ticketId, stringList.toArray(new String[0]));
            passengerViewModel.getUserInfo().observe(getViewLifecycleOwner(), userResponse -> {
                if (userResponse != null) {
                    if (userResponse.getResult().getValidation()) {
                        Navigation.findNavController(view).navigate(ReservationMainFragmentDirections.actionReservationFragmentToReservationSeatFragment(seatRequest));
                    } else {
                        Toast.makeText(context, "you must validate your account before making any reservations", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(context, "you don't have enough balance, please charge your wallet", Toast.LENGTH_SHORT).show();
        }
    }
}
