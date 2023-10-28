package com.example.coachticket.views.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ItemSeatBinding;
import com.example.coachticket.models.Seat;

import java.util.ArrayList;

public class ChooseSeatAdapter extends RecyclerView.Adapter<ChooseSeatAdapter.ChooseSeatViewHolder> {

    private ArrayList<Seat> mListSeat;
    private Activity context;

    public ChooseSeatAdapter(ArrayList<Seat> mListSeat, Activity context) {
        this.mListSeat = mListSeat;
        this.context = context;
    }

    @NonNull
    @Override
    public ChooseSeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSeatBinding itemSeatBinding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_seat, parent, false);
        return new ChooseSeatViewHolder(itemSeatBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseSeatViewHolder holder, int position) {
        Seat seat = mListSeat.get(position);
        holder.itemSeatBinding.setSeat(seat);

        if (holder.itemSeatBinding.getSeat().getStatusSeat()) {
            holder.itemSeatBinding.buttonSeat.setBackgroundResource(R.color.grey);
            holder.itemSeatBinding.buttonSeat.setTextColor(Color.GRAY);
            holder.itemSeatBinding.buttonSeat.setText("");
        }
    }

    @Override
    public int getItemCount() {
        if(mListSeat != null) {
            return mListSeat.size();
        }
        return 0;
    }

    public void notifyAdapter(ArrayList<Seat> mListSeat) {
        this.mListSeat = mListSeat;
        notifyDataSetChanged();
    }

    public class ChooseSeatViewHolder extends RecyclerView.ViewHolder {
        private final ItemSeatBinding itemSeatBinding;
        public ChooseSeatViewHolder(@NonNull ItemSeatBinding itemSeatBinding) {
            super(itemSeatBinding.getRoot());
            this.itemSeatBinding = itemSeatBinding;
        }
    }
}
