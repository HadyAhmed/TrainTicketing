package com.hadi.trainticketing.passenger.login.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hadi.trainticketing.databinding.FragmentForgetPasswordBinding;

public class ForgetPasswordFragment extends Fragment implements View.OnClickListener {
    private FragmentForgetPasswordBinding forgetPasswordBinding;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        forgetPasswordBinding = FragmentForgetPasswordBinding.inflate(inflater, container, false);
        forgetPasswordBinding.resetPassBtn.setOnClickListener(this);

        return forgetPasswordBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == forgetPasswordBinding.resetPassBtn.getId()) {
            Toast.makeText(context, "you will be sent email with reset link if it was valid", Toast.LENGTH_SHORT).show();
        }
    }
}
