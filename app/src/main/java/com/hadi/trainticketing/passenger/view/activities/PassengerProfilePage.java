package com.hadi.trainticketing.passenger.view.activities;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.databinding.ActivityProfileBinding;
import com.hadi.trainticketing.passenger.model.PassengerViewModel;
import com.hadi.trainticketing.passenger.model.pojo.profile.UserResponse;

public class PassengerProfilePage extends AppCompatActivity {
    private static final String TAG = "PassengerProfileTag";
    private ActivityProfileBinding profileBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        Log.d(TAG, "onCreate: ");
        PassengerViewModel passengerViewModel = ViewModelProviders.of(this).get(PassengerViewModel.class);

        profileBinding.profileToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        passengerViewModel.requestUserInfo(PreferenceManager.getDefaultSharedPreferences(this).getString(PassengerSignInActivity.USER_TOKEN, ""));

        passengerViewModel.getUserInfo().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                profileBinding.loadingProfile.setVisibility(View.INVISIBLE);
                if (userResponse != null) {
                    profileBinding.profileContent.setVisibility(View.VISIBLE);
                    profileBinding.setUser(userResponse);
                } else {
                    Toast.makeText(PassengerProfilePage.this, "something went wrong with our server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
