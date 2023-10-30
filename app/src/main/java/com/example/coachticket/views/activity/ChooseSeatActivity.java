package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivityChooseSeatBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;
import com.example.coachticket.viewmodels.ChooseSeatViewModel;
import com.example.coachticket.views.adapter.ChooseSeatAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ChooseSeatActivity extends AppCompatActivity {
    private ChooseSeatViewModel chooseSeatViewModel;
    private ActivityChooseSeatBinding activityChooseSeatBinding;
    private Routes routes;
    private RecyclerView recyclerViewGroup1, recyclerViewGroup2;
    private ChooseSeatAdapter adapter1, adapter2;
    private ArrayList<Seat> listSeat1, listSeat2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Routes routes = Parcels.unwrap(intent.getParcelableExtra("routes"));
        ArrayList<Seat> seat1 = Parcels.unwrap(intent.getParcelableExtra("seat1"));
        ArrayList<Seat> seat2 = Parcels.unwrap(intent.getParcelableExtra("seat2"));

        activityChooseSeatBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_seat);
//        chooseSeatViewModel = new ChooseSeatViewModel(this);
        chooseSeatViewModel = new ViewModelProvider(this).get(ChooseSeatViewModel.class);
//        chooseSeatViewModel.initAdapter1(seat1, this);
//        chooseSeatViewModel.initAdapter2(seat2, this);
        activityChooseSeatBinding.setChooSeatViewModel(chooseSeatViewModel);
        activityChooseSeatBinding.setLifecycleOwner(this);//thêm mới

        adapter1 = new ChooseSeatAdapter(seat1, this, chooseSeatViewModel);
        adapter2 = new ChooseSeatAdapter(seat2, this, chooseSeatViewModel);
        activityChooseSeatBinding.recyclerViewGroup1.setLayoutManager(new GridLayoutManager(this, 3));
        activityChooseSeatBinding.recyclerViewGroup2.setLayoutManager(new GridLayoutManager(this, 3));
        activityChooseSeatBinding.recyclerViewGroup1.setAdapter(adapter1);
        activityChooseSeatBinding.recyclerViewGroup2.setAdapter(adapter2);
//        android:text="@{Seat.Id}"
//        setContentView(activityChooseSeatBinding.getRoot());
    }
}