package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.coachticket.R;
import com.example.coachticket.SharedPreferences.SharedPrefOriginDestination;
import com.example.coachticket.databinding.ActivityChooseSeatBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;
import com.example.coachticket.presenter.Presenter;
import com.example.coachticket.viewmodels.ChooseSeatViewModel;
import com.example.coachticket.views.adapter.ChooseSeatAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ChooseSeatActivity extends AppCompatActivity {
    private ChooseSeatViewModel chooseSeatViewModel;
    private ActivityChooseSeatBinding activityChooseSeatBinding;
    private Routes routes;
    private RecyclerView recyclerViewGroup1, recyclerViewGroup2;
    private ChooseSeatAdapter adapter1, adapter2;
    private ArrayList<Seat> listSeat1, listSeat2;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Routes routes = Parcels.unwrap(intent.getParcelableExtra("routes"));
        int position = (int) getIntent().getSerializableExtra("position");

        int count = 0;
        ArrayList<Seat> allSeat1 = new ArrayList<>();
        ArrayList<Seat> allSeat2 = new ArrayList<>();
        ArrayList<Seat> filteredList1 = new ArrayList<>();
        ArrayList<Seat> filteredList2 = new ArrayList<>();

//        if (!routes.getTrips().get(0).getSeats().isEmpty()) {
//            for (Seat seat : routes.getTrips().get(0).getSeats()) {
//                for (int i = 0; i < routes.getTrips().size(); i++) {
//                    if (count < 18) {
//                        filteredList1.add(seat);
//                        count++;
//                    } else {
//                        filteredList2.add(seat);
//                    }
//                }
//            }
        if (!routes.getTrips().getSeats().isEmpty()) {
            for (Seat seat : routes.getTrips().getSeats()) {
                    if (count < 18) {
                        filteredList1.add(seat);
                        count++;
                    } else {
                        filteredList2.add(seat);
                    }
            }
            allSeat1.clear();
            allSeat2.clear();
            allSeat1.addAll(filteredList1);
            allSeat2.addAll(filteredList2);
        }


        ArrayList<String> listLocation1 = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        for (String location1 : routes.getOrigin().get(0).getLocations()) {
            for (int i = 0; i < routes.getOrigin().size(); i++) {
                list1.add(location1);
            }
        }
        listLocation1.clear();
        listLocation1.addAll(list1);

        ArrayList<String> listLocation2 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        for (String location2 : routes.getDestination().get(0).getLocations()) {
            for (int i = 0; i < routes.getDestination().size(); i++) {
                list2.add(location2);
            }
        }
        listLocation2.clear();
        listLocation2.addAll(list2);

//        ArrayList<Seat> seat1 = Parcels.unwrap(intent.getParcelableExtra("seat1"));
//        ArrayList<Seat> seat2 = Parcels.unwrap(intent.getParcelableExtra("seat2"));
//        ArrayList<String> listLocation1 =  getIntent().getStringArrayListExtra("listLocation1");
//        ArrayList<String> listLocation2 =  getIntent().getStringArrayListExtra("listLocation2");

        activityChooseSeatBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_seat);
        chooseSeatViewModel = new ViewModelProvider(this).get(ChooseSeatViewModel.class);
        chooseSeatViewModel.initAdapters(allSeat1, allSeat2, this);
        activityChooseSeatBinding.setChooSeatViewModel(chooseSeatViewModel);
        activityChooseSeatBinding.setLifecycleOwner(this);//thêm mới

        adapter1 = new ChooseSeatAdapter(allSeat1, this, chooseSeatViewModel);
        adapter2 = new ChooseSeatAdapter(allSeat2, this, chooseSeatViewModel);
        activityChooseSeatBinding.recyclerViewGroup1.setLayoutManager(new GridLayoutManager(this, 3));
        activityChooseSeatBinding.recyclerViewGroup2.setLayoutManager(new GridLayoutManager(this, 3));
        activityChooseSeatBinding.recyclerViewGroup1.setAdapter(adapter1);
        activityChooseSeatBinding.recyclerViewGroup2.setAdapter(adapter2);
        int priceTrip = routes.getPrice();
        chooseSeatViewModel.setPriceTrip(priceTrip);

        activityChooseSeatBinding.setPresenter(new Presenter() {
            @Override
            public void intentSelectPickupAndDrop() {
                int price = chooseSeatViewModel.getPrice();
                List<Seat> selectedSeats = chooseSeatViewModel.getSelectedSeats();
                if (selectedSeats.size() == 0) {
                    Toast.makeText(ChooseSeatActivity.this, "Vui lòng chọn ghế", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(ChooseSeatActivity.this, SelectPickUpPointActivity.class);
                intent.putParcelableArrayListExtra("selectedSeats", new ArrayList<>(selectedSeats));
                intent.putExtra("routes", Parcels.wrap(routes));
                intent.putExtra("listLocation1", listLocation1);
                intent.putExtra("listLocation2", listLocation2);
                intent.putExtra("price", price);
                startActivity(intent);
            }
        });
    }

    public void goback(View view) {
        finish();
    }
}