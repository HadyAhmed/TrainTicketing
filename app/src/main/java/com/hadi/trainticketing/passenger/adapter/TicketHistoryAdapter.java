package com.hadi.trainticketing.passenger.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.trainticketing.databinding.TicketHistoryItemBinding;
import com.hadi.trainticketing.passenger.model.pojo.ticket.TicketHistoryModel;

import java.util.List;

public class TicketHistoryAdapter extends RecyclerView.Adapter<TicketHistoryAdapter.TicketViewHolder> {
    private LayoutInflater inflater;
    private List<TicketHistoryModel> ticketHistoryModels;

    public void setTicketHistoryModels(List<TicketHistoryModel> ticketHistoryModels) {
        this.ticketHistoryModels = ticketHistoryModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new TicketViewHolder(TicketHistoryItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        holder.setTicket(ticketHistoryModels.get(position));
    }

    @Override
    public int getItemCount() {
        if (ticketHistoryModels == null) {
            return 0;
        }
        return ticketHistoryModels.size();
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {
        private TicketHistoryItemBinding historyItemBinding;

        TicketViewHolder(@NonNull TicketHistoryItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.historyItemBinding = itemBinding;
        }

        void setTicket(TicketHistoryModel ticket) {
            this.historyItemBinding.setTicket(ticket);
        }
    }
}
