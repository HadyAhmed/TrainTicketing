package com.hadi.trainticketing.passenger.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.trainticketing.databinding.TicketItemBinding;
import com.hadi.trainticketing.passenger.pojo.enquire.Result;

import java.util.List;

public class EnquireAdapter extends RecyclerView.Adapter<EnquireAdapter.TicketViewHolder> {
    private LayoutInflater inflater;
    private List<Result> resultList;

    public EnquireAdapter() {
    }

    private OnTicketClickListener ticketClickListener;

    public EnquireAdapter(OnTicketClickListener ticketClickListener) {
        this.ticketClickListener = ticketClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Result currentTicket = resultList.get(position);
        holder.setTicket(currentTicket);
        holder.setTicketClickListener(ticketClickListener);
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new TicketViewHolder(TicketItemBinding.inflate(inflater, parent, false));
    }

    public interface OnTicketClickListener {
        void onTicketClick(String id, View view);
    }

    @Override
    public int getItemCount() {
        if (resultList == null) {
            return 0;
        } else {
            return resultList.size();
        }
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {
        private TicketItemBinding itemBinding;

        TicketViewHolder(@NonNull TicketItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }

        void setTicket(Result tickets) {
            this.itemBinding.setTicket(tickets);
        }

        void setTicketClickListener(OnTicketClickListener ticketClickListener) {
            this.itemBinding.setTicketClickListener(ticketClickListener);
        }
    }

}
