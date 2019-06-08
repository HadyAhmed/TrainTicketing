package com.hadi.trainticketing.passenger.home.view.activities;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityProfileBinding;
import com.hadi.trainticketing.passenger.home.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.login.view.PassengerSignInActivity;

public class PassengerProfilePage extends AppCompatActivity {
    private static final String TAG = "PassengerProfileTag";
    private ActivityProfileBinding profileBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        Log.d(TAG, "onCreate: ");
        PassengerViewModel passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

        profileBinding.profileToolbar.setNavigationOnClickListener(v -> onBackPressed());

        passengerViewModel.requestUserInfo(PreferenceManager.getDefaultSharedPreferences(this).getString(PassengerSignInActivity.USER_TOKEN, ""));

        passengerViewModel.getUserInfo().observe(this, userResponse -> {
            profileBinding.loadingProfile.setVisibility(View.INVISIBLE);
            if (userResponse != null) {
                profileBinding.profileContent.setVisibility(View.VISIBLE);
                profileBinding.setUser(userResponse);
            } else {
                Toast.makeText(PassengerProfilePage.this, "something went wrong with our server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
