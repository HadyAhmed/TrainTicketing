package com.hadi.trainticketing.validator.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityValidatorMainBinding;
import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.validator.home.pojo.request.ScanBody;
import com.hadi.trainticketing.validator.home.pojo.response.ScanResponse;
import com.hadi.trainticketing.welcome.WelcomeActivity;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidatorMainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private static final String TAG = "ScanTag";
    private ActivityValidatorMainBinding validatorMainBinding;
    private int ticketNumber = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        validatorMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_validator_main);
        validatorMainBinding.validatorToolbar.inflateMenu(R.menu.main_menu);
        validatorMainBinding.validatorToolbar.setOnMenuItemClickListener(this);

        validatorMainBinding.validatorToolbar.getMenu().findItem(R.id.action_account_settings).setVisible(false);

        updateTicketCounter(ticketNumber);
        validatorMainBinding.scanTicketBtn.setOnClickListener(v -> new IntentIntegrator(this).initiateScan());

    }

    private void updateTicketCounter(int ticketNumber) {
        validatorMainBinding.scannedTicketsNumber.setText(String.valueOf(ticketNumber));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                scanTicket(result.getContents());
                Log.d(TAG, "onActivityResult: " + result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void scanTicket(String reservationId) {
        validatorMainBinding.scanProgress.setVisibility(View.VISIBLE);

        Log.d(TAG, "scanTicket: " + reservationId + " body: " + Arrays.toString(new ScanBody[]{new ScanBody("valid", "true")}));

        WebServices.serverConnection.create(WebServices.class)
                .scanTicket(reservationId, new ScanBody[]{new ScanBody("valid", "true")})
                .enqueue(new Callback<ScanResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ScanResponse> call, @NonNull Response<ScanResponse> response) {
                        validatorMainBinding.scanProgress.setVisibility(View.INVISIBLE);
                        if (response.body() != null) {
                            Toast.makeText(ValidatorMainActivity.this, "ticket is valid", Toast.LENGTH_SHORT).show();
                            ticketNumber++;
                            updateTicketCounter(ticketNumber);
                        } else {
                            Toast.makeText(ValidatorMainActivity.this, "ticket isn't valid", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ScanResponse> call, @NonNull Throwable t) {
                        validatorMainBinding.scanProgress.setVisibility(View.INVISIBLE);
                        Toast.makeText(ValidatorMainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ValidatorMainActivity.this);
            builder.setTitle("Logout!")
                    .setMessage("Are you sure to logout ?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ValidatorMainActivity.this);
                        preferences.edit().clear().apply();
                        startActivity(new Intent(ValidatorMainActivity.this, WelcomeActivity.class));
                        finish();
                    }).setNegativeButton("Cancel", null)
                    .show();
            return true;
        } else
            return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ValidatorMainActivity.this);
        builder.setTitle("Exit!")
                .setMessage("Are you sure to Exit?")
                .setPositiveButton("Yes", (dialog, which) -> finish()).setNegativeButton("Cancel", null)
                .show();
    }
}
