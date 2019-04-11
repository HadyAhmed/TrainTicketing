package com.hadi.trainticketing.passenger.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hadi.trainticketing.databinding.PassengerWelcomeItemBinding;
import com.hadi.trainticketing.passenger.pojo.PassengerCards;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Hady Ahmed
 * @version 1.0
 * {@link androidx.recyclerview.widget.RecyclerView.Adapter} that handles
 * the {@link RecyclerView} of the {@link com.hadi.trainticketing.welcome.WelcomeActivity}
 */
public class WelcomeAdapter extends RecyclerView.Adapter<WelcomeAdapter.WelcomeItemViewHolder> {
    private List<PassengerCards> mPassengerCardsList;
    private LayoutInflater inflater;
    private OnWelcomeItemClickListener welcomeItemClickListener;

    public WelcomeAdapter(List<PassengerCards> mPassengerCardsList, OnWelcomeItemClickListener welcomeItemClickListener) {
        this.mPassengerCardsList = mPassengerCardsList;
        this.welcomeItemClickListener = welcomeItemClickListener;
    }

    @BindingAdapter("iconSrc")
    public static void setIconSrc(ImageView imageView, int imageResourceId) {
        imageView.setImageResource(imageResourceId);
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

    public interface OnWelcomeItemClickListener {
        void onWelcomeItemClick(int itemPosition);
    }

    @Override
    public int getItemCount() {
        return mPassengerCardsList.size();
    }

    class WelcomeItemViewHolder extends RecyclerView.ViewHolder {
        private PassengerWelcomeItemBinding welcomeItemBinding;

        WelcomeItemViewHolder(@NonNull PassengerWelcomeItemBinding itemView) {
            super(itemView.getRoot());
            this.welcomeItemBinding = itemView;
        }

        PassengerWelcomeItemBinding getWelcomeItemBinding() {
            return welcomeItemBinding;
        }

        void setWelcomeItemBinding(PassengerCards passengerCards) {
            this.welcomeItemBinding.setItemVariable(passengerCards);
        }
    }
}
