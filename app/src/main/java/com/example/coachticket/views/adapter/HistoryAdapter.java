package com.example.coachticket.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ItemTicketBinding;
import com.example.coachticket.models.BookingSeat;
import com.example.coachticket.views.activity.DetailHistory;
import com.example.coachticket.views.fragment.HistoryFragment;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{
    private Context context;
    private ArrayList<BookingSeat> mListBookingSeats;

    public HistoryAdapter(Context context, ArrayList<BookingSeat> mListBookingSeats) {
        this.context = context;
        this.mListBookingSeats = mListBookingSeats;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTicketBinding view = DataBindingUtil.inflate(LayoutInflater.from(context),
        R.layout.item_ticket, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        BookingSeat bookingSeat = mListBookingSeats.get(position);
        holder.itemTicketBinding.setBookingSeat(bookingSeat);

        holder.itemTicketBinding.detailHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHistory.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListBookingSeats.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        ItemTicketBinding itemTicketBinding;
        public HistoryViewHolder(@NonNull ItemTicketBinding itemTicketBinding) {
            super(itemTicketBinding.getRoot());
            this.itemTicketBinding = itemTicketBinding;
        }
    }

    public void notifyAdapter(ArrayList<BookingSeat> bookingSeats){
        this.mListBookingSeats = bookingSeats;
        notifyDataSetChanged();
    }
}
