package com.example.coachticket.viewmodels;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coachticket.models.Seat;
import com.example.coachticket.views.activity.ChooseSeatActivity;
import com.example.coachticket.views.adapter.ChooseSeatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChooseSeatViewModel extends ViewModel {

    private ChooseSeatAdapter adapter1, adapter2;

    public ChooseSeatViewModel() {
    }

    private final MutableLiveData<String> selectedSeatsText = new MutableLiveData<>();
    private final MutableLiveData<String> totalPrice = new MutableLiveData<>();
    private final List<Seat> selectedSeats = new ArrayList<>();
    private MutableLiveData<Integer> selectedSeatCount = new MutableLiveData<>(0);

//    public ChooseSeatAdapter getAdapter1() {
//        return adapter1;
//    }
//
//    public ChooseSeatAdapter getAdapter2() {
//        return adapter2;
//    }

//    public void initAdapter1(ArrayList<Seat> mListSeat, Activity context) {
//        adapter1 = new ChooseSeatAdapter(mListSeat, context, this);
//    }
//
//    public void initAdapter2(ArrayList<Seat> mListSeat, Activity context) {
//        adapter2 = new ChooseSeatAdapter(mListSeat, context, this);
//    }
    public LiveData<Integer> getSelectedSeatCount() {
        return selectedSeatCount;
    }

    public LiveData<String> getSelectedSeatsText() {
        return selectedSeatsText;
    }

    public LiveData<String> getTotalPrice() {
        return totalPrice;
    }

    public List<Seat> getSelectedSeats() {
        return selectedSeats;
    }

    public void onSeatSelected(Seat seat) {
        if (seat.getStatusSeat()) {
            // Ghế đã bị chọn
            return;
        }

        if (selectedSeats.contains(seat)) {
            // Bỏ chọn ghế nếu đã chọn
            selectedSeats.remove(seat);
            selectedSeatCount.setValue(selectedSeatCount.getValue() - 1);
        } else {
            // Chọn ghế nếu chưa chọn
            selectedSeats.add(seat);
            selectedSeatCount.setValue(selectedSeatCount.getValue() + 1);
        }

        // Cập nhật danh sách ghế đã chọn và tính tổng giá tiền
        updateSelectedSeatsText();
        updateTotalPrice();

        // Gọi phương thức cập nhật trạng thái trong Adapter
//        adapter1.updateSeatStatus(seat);
//        adapter2.updateSeatStatus(seat);
    }

    private void updateSelectedSeatsText() {
        StringBuilder sb = new StringBuilder();
        for (Seat seat : selectedSeats) {
            sb.append(seat.getId()).append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Loại bỏ dấu phẩy và khoảng trắng cuối cùng
        }
        selectedSeatsText.setValue(sb.toString());
    }

    private void updateTotalPrice() {
        // Tính tổng giá tiền dựa trên số ghế đã chọn và giá tiền mỗi ghế
        int pricePerSeat = 285000; // Giá tiền mỗi ghế
        int total = selectedSeats.size() * pricePerSeat;
        totalPrice.setValue(String.format("%,d VND", total));
    }
}
