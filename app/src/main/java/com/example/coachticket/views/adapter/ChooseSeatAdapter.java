package com.example.coachticket.views.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ItemSeatBinding;
import com.example.coachticket.models.Seat;
import com.example.coachticket.viewmodels.ChooseSeatViewModel;

import java.util.ArrayList;

public class ChooseSeatAdapter extends RecyclerView.Adapter<ChooseSeatAdapter.ChooseSeatViewHolder> {

    private ArrayList<Seat> mListSeat;
    private Activity context;
    private ChooseSeatViewModel viewModel;

    public ChooseSeatAdapter(ArrayList<Seat> mListSeat, Activity context, ChooseSeatViewModel viewModel) {
        this.mListSeat = mListSeat;
        this.context = context;
        this.viewModel = viewModel;
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
            // Ghế đã chọn, hiển thị màu và chữ tương ứng
            holder.itemSeatBinding.buttonSeat.setBackgroundResource(R.color.grey);
            holder.itemSeatBinding.buttonSeat.setTextColor(Color.GRAY);
            holder.itemSeatBinding.buttonSeat.setText("");
        } else {
            if (viewModel.getSelectedSeats().contains(seat)) {
                // Ghế chưa chọn
                holder.itemSeatBinding.buttonSeat.setBackgroundResource(R.color.black);
                holder.itemSeatBinding.buttonSeat.setTextColor(Color.WHITE);
                holder.itemSeatBinding.buttonSeat.setText(seat.getId());
            } else {
                // Ghế đang chọn
//                holder.itemSeatBinding.buttonSeat.setBackgroundResource(R.color.white);
                holder.itemSeatBinding.buttonSeat.setTextColor(Color.BLACK);
//                holder.itemSeatBinding.buttonSeat.setText("");
            }
        }

        holder.itemSeatBinding.buttonSeat.setOnClickListener(v -> {
            if (viewModel.getSelectedSeatCount().getValue() < 3 || viewModel.getSelectedSeats().contains(seat)) {
                viewModel.onSeatSelected(seat);
            } else {
                Toast.makeText(context, "Bạn chỉ được chọn tối đa 3 ghế.", Toast.LENGTH_SHORT).show();
            }
            notifyItemChanged(position);
//            if (viewModel.getSelectedSeatCount().getValue() < 3 || viewModel.getSelectedSeats().contains(seat)) {
//                viewModel.onSeatSelected(seat);
//                // Gọi lại notifyItemChanged để cập nhật màu nền cho ghế thứ 2
//                if (viewModel.getSelectedSeats().contains(seat)) {
//                    int positionOfSeat2 = mListSeat.indexOf(seat2); // Thay seat2 bằng ghế thứ 2
//                    if (positionOfSeat2 != -1) {
//                        notifyItemChanged(positionOfSeat2);
//                    }
//                }
//            } else {
//                Toast.makeText(context, "Bạn chỉ được chọn tối đa 3 ghế.", Toast.LENGTH_SHORT).show();
//            }
//            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        if (mListSeat != null) {
            return mListSeat.size();
        }
        return 0;
    }

    public void notifyAdapter(ArrayList<Seat> mListSeat) {
        this.mListSeat = mListSeat;
        notifyDataSetChanged();
    }

    public void updateSeatStatus2(Seat seat) {
        int position = mListSeat.indexOf(seat);
        if (position != -1) {
            Seat updatedSeat = mListSeat.get(position);
            updatedSeat.setStatusSeat(!updatedSeat.getStatusSeat());
            notifyItemChanged(position);
        }
    }

    public void updateSeatStatus(Seat seat) {
        int position = mListSeat.indexOf(seat);
        if (position != -1) {
            notifyItemChanged(position);
        }
    }



    public class ChooseSeatViewHolder extends RecyclerView.ViewHolder {
        private final ItemSeatBinding itemSeatBinding;

        public ChooseSeatViewHolder(@NonNull ItemSeatBinding itemSeatBinding) {
            super(itemSeatBinding.getRoot());
            this.itemSeatBinding = itemSeatBinding;
        }
    }
}
