package com.example.coachticket.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coachticket.models.BookingSeat;
import com.example.coachticket.models.BookingSeatDetails;
import com.example.coachticket.models.Seat;

import java.util.ArrayList;

public class DetailHistoryViewModel extends ViewModel {
    private BookingSeat bookingSeat;

    private MutableLiveData<String> seat = new MutableLiveData<>();

    public BookingSeat getBookingSeat() {
        return bookingSeat;
    }

    public void setBookingSeat(BookingSeat bookingSeat) {
        this.bookingSeat = bookingSeat;
    }

    public MutableLiveData<String> getSeat() {
        return seat;
    }

    public void updateSeatIds(ArrayList<BookingSeatDetails> selectedSeats) {
        StringBuilder builder = new StringBuilder();
        for (BookingSeatDetails seat : selectedSeats) {
            builder.append(seat.getSeatNumber()).append(", ");
        }
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2); // Xóa dấu phẩy cuối cùng
        }
        seat.setValue(builder.toString());
    }

}
