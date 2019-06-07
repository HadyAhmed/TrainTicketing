package com.hadi.trainticketing.passenger.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.trainticketing.databinding.SeatsItemBinding;
import com.hadi.trainticketing.passenger.model.pojo.reservation.AvailableSeat;

import java.util.List;

public class SeatsAdapter extends RecyclerView.Adapter<SeatsAdapter.SeatViewHolder> {

    private LayoutInflater inflater;
    private List<AvailableSeat> seatList;
    private OnSeatClickListener onSeatClickListener;

    public SeatsAdapter(OnSeatClickListener onSeatClickListener) {
        this.onSeatClickListener = onSeatClickListener;
    }

    public void setSeatList(List<AvailableSeat> seatList) {
        this.seatList = seatList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new SeatViewHolder(SeatsItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        holder.setSeatNumber(seatList.get(position));
        holder.setOnSeatClickListener(onSeatClickListener);
    }

    @Override
    public int getItemCount() {
        if (seatList == null) {
            return 0;
        }
        return seatList.size();
    }

    public interface OnSeatClickListener {
        void onSeatClick(View view, String seatId);
    }

    class SeatViewHolder extends RecyclerView.ViewHolder {
        private SeatsItemBinding itemBinding;

        SeatViewHolder(@NonNull SeatsItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }

        void setSeatNumber(AvailableSeat seat) {
            this.itemBinding.setSeat(seat);
        }

        void setOnSeatClickListener(OnSeatClickListener onSeatClickListener) {
            this.itemBinding.setSeatClickListener(onSeatClickListener);
        }
    }

}
