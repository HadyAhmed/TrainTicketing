package com.hadi.trainticketing.passenger.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hadi.trainticketing.databinding.PassengerWelcomeItemBinding;
import com.hadi.trainticketing.passenger.interfaces.OnWelcomeItemClickListener;
import com.hadi.trainticketing.passenger.model.PassengerCards;
import com.hadi.trainticketing.passenger.viewholder.WelcomeItemViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeItemViewHolder> {
    private List<PassengerCards> mPassengerCardsList;
    private LayoutInflater inflater;
    private OnWelcomeItemClickListener welcomeItemClickListener;

    public WelcomeAdapter(List<PassengerCards> mPassengerCardsList, OnWelcomeItemClickListener welcomeItemClickListener) {
        this.mPassengerCardsList = mPassengerCardsList;
        this.welcomeItemClickListener = welcomeItemClickListener;
    }

    @NonNull
    @Override
    public WelcomeItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        PassengerWelcomeItemBinding itemBinding = PassengerWelcomeItemBinding.inflate(inflater, viewGroup, false);
        return new WelcomeItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WelcomeItemViewHolder welcomeItemViewHolder, int i) {
        PassengerCards currentModel = mPassengerCardsList.get(i);
        welcomeItemViewHolder.setWelcomeItemBinding(currentModel);

        welcomeItemViewHolder.getWelcomeItemBinding().setItemClickHandler(welcomeItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mPassengerCardsList.size();
    }

}
