package com.hadi.trainticketing.passenger.viewholder;

import com.hadi.trainticketing.databinding.PassengerWelcomeItemBinding;
import com.hadi.trainticketing.passenger.model.PassengerCards;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WelcomeItemViewHolder extends RecyclerView.ViewHolder {
    private PassengerWelcomeItemBinding welcomeItemBinding;

    public WelcomeItemViewHolder(@NonNull PassengerWelcomeItemBinding itemView) {
        super(itemView.getRoot());
        this.welcomeItemBinding = itemView;
    }

    public PassengerWelcomeItemBinding getWelcomeItemBinding() {
        return welcomeItemBinding;
    }

    public void setWelcomeItemBinding(PassengerCards passengerCards) {
        this.welcomeItemBinding.setItemVariable(passengerCards);
    }
}
