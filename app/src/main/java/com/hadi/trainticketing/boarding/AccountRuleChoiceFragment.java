package com.hadi.trainticketing.boarding;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.hadi.trainticketing.databinding.AccountFragmentChoiceBinding;

/**
 * @author Hady Ahmed
 * @version 1.0
 * this page show up accounts type to be selected by the user and make the app destination
 */
public class AccountRuleChoiceFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    private AccountFragmentChoiceBinding choiceBinding;
    private Context context;

    public interface AccountChoice {
        enum Choices {
            PASSENGER(0), VALIDATOR(1);

            private int choice;

            Choices(int choice) {
                this.choice = choice;
            }
        }

        void startAccountRule(Enum<Choices> rule);
    }

    private AccountChoice accountChoiceListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        try {
            accountChoiceListener = (AccountChoice) context;
        } catch (ClassCastException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        choiceBinding = AccountFragmentChoiceBinding.inflate(inflater, container, false);

        choiceBinding.passengerBtn.setOnClickListener(this);
        choiceBinding.passengerBtn.setOnLongClickListener(this);
        choiceBinding.validatorBtn.setOnClickListener(this);
        choiceBinding.validatorBtn.setOnLongClickListener(this);
        return choiceBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == choiceBinding.validatorBtn.getId()) {
            accountChoiceListener.startAccountRule(AccountChoice.Choices.VALIDATOR);
        } else if (v.getId() == choiceBinding.passengerBtn.getId()) {
            accountChoiceListener.startAccountRule(AccountChoice.Choices.PASSENGER);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        if (v.getId() == choiceBinding.validatorBtn.getId()) {
            Snackbar.make(v, "Validator is the person who works for the railway and scan electronic ticket", Snackbar.LENGTH_INDEFINITE).show();
            return true;
        } else if (v.getId() == choiceBinding.passengerBtn.getId()) {
            Snackbar.make(v, "Passenger will enjoy the railway services, enquire, reserve and more.", Snackbar.LENGTH_INDEFINITE).show();
            return true;
        } else {
            return false;
        }
    }
}
