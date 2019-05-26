package com.hadi.trainticketing.passenger.view.navigation;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.FragmentTicketNameDetailsBinding;
import com.hadi.trainticketing.passenger.view.activities.PassengerMainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TicketNameDetails extends Fragment implements View.OnClickListener, TextWatcher {
    private static final String TAG = "TicketNameDetails";
    private Context context;
    private FragmentTicketNameDetailsBinding ticketNameDetailsBinding;
    private int numberOfTickets;
    private int ticketNumber;

    public TicketNameDetails() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ticketNameDetailsBinding = FragmentTicketNameDetailsBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            initUi(getArguments().getInt("ticketNumber"));
        }

        return ticketNameDetailsBinding.getRoot();
    }

    private void initUi(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        ticketNameDetailsBinding.passengerNameEt.addTextChangedListener(this);

        ticketNameDetailsBinding.addPassengerBtn.setOnClickListener(this);
        ticketNameDetailsBinding.submitNamesBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ticketNameDetailsBinding.addPassengerBtn.getId()) {
            if (numberOfTickets == ticketNumber) {
                Toast.makeText(context, "you've added all passengers", Toast.LENGTH_SHORT).show();
                ticketNameDetailsBinding.passengerNameEt.setText(null);
            } else {
                if (ticketNameDetailsBinding.passengerNameEt != null && ticketNameDetailsBinding.passengerNameEt.getText().toString().contains(" ")) {
                    TextView nameTv = new TextView(context);
                    nameTv.setText(ticketNameDetailsBinding.passengerNameEt.getText().toString());
                    nameTv.setTextSize(18.0f);
                    ticketNameDetailsBinding.namesLayout.addView(nameTv);
                    ticketNameDetailsBinding.passengerNameEt.setText(null);
                    numberOfTickets++;
                } else {
                    ticketNameDetailsBinding.passengerNameLayout.setError("Please insert first and last name at least");
                }
            }
        } else {
            if (numberOfTickets == ticketNumber) {
                showConfirmDialog(v);
            } else {
                Toast.makeText(context, String.format("you must insert the %d passenger names", ticketNumber), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showConfirmDialog(final View v) {
        AlertDialog.Builder askForConfimration = new AlertDialog.Builder(context);
        askForConfimration.setTitle("Confirm")
                .setMessage("Are your sure to process reservation ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 4/20/2019 add name detail to the next fragment here
                        Navigation.findNavController(v).navigate(R.id.moveToTicketConfirmFragment);
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(context, PassengerMainActivity.class));
                    }
                })
                .show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ticketNameDetailsBinding.passengerNameLayout.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
