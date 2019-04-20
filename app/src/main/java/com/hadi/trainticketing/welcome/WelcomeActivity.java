package com.hadi.trainticketing.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.passenger.view.activities.PassengerMainActivity;
import com.hadi.trainticketing.passenger.view.activities.PassengerSignInActivity;
import com.hadi.trainticketing.validator.ValidatorLoginActivity;

/**
 * This is the entry point for the application where the user will be able to know some
 * application features and demonstrate his rule.
 *
 * @author Hady Ahmed
 * @version 1.0
 */
public class WelcomeActivity extends AppCompatActivity
        implements WelcomeFragment.OnStartFragmentListener, AccountRuleChoiceFragment.AccountChoice {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean(PassengerSignInActivity.IS_SIGNED_IN, false)) {
            startActivity(new Intent(this, PassengerMainActivity.class));
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (savedInstanceState == null) {
            changeFragment(new WelcomeFragment());
        }
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void navigateToAccountRuleChoose() {
        changeFragment(new AccountRuleChoiceFragment());
    }

    @Override
    public void startAccountRule(Enum<Choices> rule) {
        if (rule == Choices.VALIDATOR) {
            startActivity(new Intent(WelcomeActivity.this, ValidatorLoginActivity.class));
            finish();
        } else if (rule == Choices.PASSENGER) {
            startActivity(new Intent(WelcomeActivity.this, PassengerSignInActivity.class));
            finish();
        }
    }
}



