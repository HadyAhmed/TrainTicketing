package com.hadi.trainticketing.passenger.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.trainticketing.databinding.TicketItemBinding;
import com.hadi.trainticketing.passenger.model.pojo.enquire.ArrayResult;
import com.hadi.trainticketing.passenger.model.pojo.enquire.TicketModel;

import java.util.List;

public class EnquireAdapter extends RecyclerView.Adapter<EnquireAdapter.TicketViewHolder> {
    private LayoutInflater inflater;
    private List<TicketModel> ticketResults;

    private OnTicketClickListener ticketClickListener;

    public interface OnTicketClickListener {
        void onTicketClick(View view, int classType, int ticketPrice, String ticketId, String trainId, List<ArrayResult> arrayResults);
    }

    public EnquireAdapter(OnTicketClickListener ticketClickListener) {
        this.ticketClickListener = ticketClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, final int position) {
        holder.setTicket(ticketResults.get(position));
        holder.setOnTicketClick(ticketClickListener);
    }

    public void setTicketResults(List<TicketModel> ticketResults) {
        this.ticketResults = ticketResults;
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

    @Override
    public int getItemCount() {
        if (ticketResults == null) {
            return 0;
        } else {
            return ticketResults.size();
        }
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {
        private TicketItemBinding itemBinding;

        TicketViewHolder(@NonNull TicketItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }

        void setTicket(TicketModel tickets) {
            this.itemBinding.setTicket(tickets);
        }

        void setOnTicketClick(OnTicketClickListener onTicketClick) {
            this.itemBinding.setTicketClickListener(onTicketClick);
        }
    }

}
