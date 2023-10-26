package com.example.coachticket.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ActivityChooseSeatBinding;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;
import com.example.coachticket.viewmodels.ChooseSeatViewModel;

import org.parceler.Parcels;

public class ChooseSeatActivity extends AppCompatActivity {
    private ChooseSeatViewModel chooseSeatViewModel;
    private ActivityChooseSeatBinding activityChooseSeatBinding;
    private Routes routes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseSeatBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose_seat);
        chooseSeatViewModel = new ChooseSeatViewModel(this);
        activityChooseSeatBinding.setChooSeatViewModel(chooseSeatViewModel);
        Intent intent = getIntent();
//        String id = (String) intent.getSerializableExtra("routesId");
//        Log.d("GETID", routes.get_id());
        Routes routes = Parcels.unwrap(intent.getParcelableExtra("routes"));
//        Seat seat = Parcels.unwrap(intent.getParcelableExtra("seat"));
        if (routes == null) {
            Log.d("GETroutes", "routes null null");
        }
//        if (seat == null) {
//            Log.d("SEATNULL", "seat null null");
//        }
        Log.d("getDuration", routes.getDuration());
//        Log.d("SEAT", seat.getId());
//        for (Routes route : routes) {
//            for (int i = 0; i < route.getCarriers().size(); i++) {
//
//            }
//        Log.d("GETID", routes.getTrips());

        setContentView(activityChooseSeatBinding.getRoot());
    }
}