package com.hadi.trainticketing.validator.view.navigation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hadi.trainticketing.databinding.FragmentValidatorScanBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class ValidatorScanFragment extends Fragment {

    private FragmentValidatorScanBinding scanBinding;
    private Context context;

    public ValidatorScanFragment() {
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
        scanBinding = FragmentValidatorScanBinding.inflate(inflater, container, false);

        scanBinding.scanTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanTicket();
            }
        });


        return scanBinding.getRoot();
    }

    private void scanTicket() {
        new IntentIntegrator(getActivity()).initiateScan();
    }

    // Get the results:
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
