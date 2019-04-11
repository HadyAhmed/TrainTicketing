package com.hadi.trainticketing.passenger.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hadi.trainticketing.databinding.TicketItemBinding;
import com.hadi.trainticketing.passenger.pojo.enquire.Result;
import com.hadi.trainticketing.passenger.viewholder.TicketViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EnquireAdapter extends RecyclerView.Adapter<TicketViewHolder> {
    private LayoutInflater inflater;
    private List<Result> resultList;

    public EnquireAdapter() {
        // TODO: 4/9/2019 add listener for the reservation
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

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Result currentTicket = resultList.get(position);
        holder.setTicket(currentTicket);
    }

    @Override
    public int getItemCount() {
        if (resultList == null) {
            return 0;
        } else {
            return resultList.size();
        }
    }
}
