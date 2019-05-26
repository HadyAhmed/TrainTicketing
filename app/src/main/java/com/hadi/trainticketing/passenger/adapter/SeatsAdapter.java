package com.hadi.trainticketing.passenger.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.trainticketing.databinding.SeatsItemBinding;

public class SeatsAdapter extends RecyclerView.Adapter<SeatsAdapter.SeatViewHolder> {

    private static final int TRAIN_SEATS_NUMBER = 20;
    private LayoutInflater inflater;
    private int seatNumber = 1;
    private OnSeatClickListener onSeatClickListener;

    public SeatsAdapter(OnSeatClickListener onSeatClickListener) {
        this.onSeatClickListener = onSeatClickListener;
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
        holder.setSeatNumber(seatNumber);
        holder.setOnSeatClickListener(onSeatClickListener);
        seatNumber++;
    }

    @Override
    public int getItemCount() {
        return TRAIN_SEATS_NUMBER;
    }

    public interface OnSeatClickListener {
        // TODO: 4/20/2019 Changle the seat id to your logic
        void onSeatClick(View view, int seatId);
    }

    class SeatViewHolder extends RecyclerView.ViewHolder {
        private SeatsItemBinding itemBinding;

        public SeatViewHolder(@NonNull SeatsItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }

        // TODO: 4/20/2019 change the seat setter
        public void setSeatNumber(int seatNumber) {
            this.itemBinding.setSeatInfo(String.valueOf(seatNumber));
        }

        public void setOnSeatClickListener(OnSeatClickListener onSeatClickListener) {
            this.itemBinding.setSeatClickListener(onSeatClickListener);
        }
    }

}
