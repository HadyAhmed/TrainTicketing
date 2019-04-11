package com.hadi.trainticketing.passenger.viewholder;

import com.hadi.trainticketing.databinding.TicketItemBinding;
import com.hadi.trainticketing.passenger.pojo.enquire.Result;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TicketViewHolder extends RecyclerView.ViewHolder {
    private TicketItemBinding itemBinding;

    public TicketViewHolder(@NonNull TicketItemBinding itemView) {
        super(itemView.getRoot());
        this.itemBinding = itemView;
    }

    public void setTicket(Result tickets) {
        this.itemBinding.setTicket(tickets);
    }
}
