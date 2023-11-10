package com.example.coachticket.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ItemCarriersBinding;
import com.example.coachticket.models.Carriers;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.Seat;
import com.example.coachticket.views.activity.ChooseSeatActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChooseCarrierAdapter extends RecyclerView.Adapter<ChooseCarrierAdapter.ChooseCarrierViewHolder> {

    private ArrayList<Routes> mListRoutes;
    private Activity context;

    public ChooseCarrierAdapter(Context context, ArrayList<Routes> mListRoutes) {
        this.mListRoutes = mListRoutes;
        this.context = (Activity) context;
    }

    @NonNull
    @Override
    public ChooseCarrierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarriersBinding itemCarriersBinding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_carriers, parent, false);
        return new ChooseCarrierViewHolder(itemCarriersBinding);
//      ItemCarriersBinding view = DataBindingUtil.inflate(LayoutInflater.from(context),
//              R.layout.item_carriers, parent, false);
//      return new ChooseCarrierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseCarrierViewHolder holder, int position) {
        Routes routes = mListRoutes.get(position);
        holder.itemCarriersBinding.setRoutes(routes);
        int position1 = position;
//        int count = 0;
//        ArrayList<Seat> allSeat1 = new ArrayList<>();
//        ArrayList<Seat> allSeat2 = new ArrayList<>();
//        ArrayList<Seat> filteredList1 = new ArrayList<>();
//        ArrayList<Seat> filteredList2 = new ArrayList<>();
//        for (Seat seat : mListRoutes.get(position).getTrips().get(position).getSeats()) {
//            for (int i = 0; i < mListRoutes.get(position).getTrips().size(); i++) {
//                if (count < 19) {
//                    filteredList1.add(seat);
//                    count ++;
//                } else {
//                    filteredList2.add(seat);
//                }
//            }
//        }
//        allSeat1.clear();
//        allSeat2.clear();
//        allSeat1.addAll(filteredList1);
//        allSeat2.addAll(filteredList2);

//        int count = 0;
//        ArrayList<Seat> allSeat1 = new ArrayList<>();
//        ArrayList<Seat> allSeat2 = new ArrayList<>();
//        ArrayList<Seat> filteredList1 = new ArrayList<>();
//        ArrayList<Seat> filteredList2 = new ArrayList<>();
//
//        for (Seat seat : mListRoutes.get(position).getTrips().get(position).getSeats()) {
//            if (count < 19) {
//                filteredList1.add(seat);
//                count++;
//            } else {
//                filteredList2.add(seat);
//            }
//        }
//
//        allSeat1.clear();
//        allSeat2.clear();
//        allSeat1.addAll(filteredList1);
//        allSeat2.addAll(filteredList2);


//        ArrayList<String> listLocation1 = new ArrayList<>();
//        ArrayList<String> list1 = new ArrayList<>();
//        for (String location1 : mListRoutes.get(position).getOrigin().get(position).getLocations()) {
//            for (int i = 0; i < mListRoutes.get(position).getOrigin().size(); i++) {
//               list1.add(location1);
//            }
//        }
//        listLocation1.clear();
//        listLocation1.addAll(list1);
//
//        ArrayList<String> listLocation2 = new ArrayList<>();
//        ArrayList<String> list2 = new ArrayList<>();
//        for (String location2 : mListRoutes.get(position).getDestination().get(position).getLocations()) {
//            for (int i = 0; i < mListRoutes.get(position).getDestination().size(); i++) {
//                list2.add(location2);
//            }
//        }
//        listLocation2.clear();
//        listLocation2.addAll(list2);

        holder.itemCarriersBinding.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChooseSeatActivity.class);
                intent.putExtra("routesId", routes.get_id());
                intent.putExtra("routes", Parcels.wrap(routes));
                intent.putExtra("position", position1);
//                intent.putExtra("seat1", Parcels.wrap(allSeat1));
//                intent.putExtra("seat2", Parcels.wrap(allSeat2));
//                intent.putExtra("listLocation1",listLocation1);
//                intent.putExtra("listLocation2",listLocation2);
                context.startActivity(intent);
            }
        });
//      holder.itemCarriersBinding.setRoutes(mListRoutes.get(position));
    }

    @Override
    public int getItemCount() {
        if (mListRoutes != null) {
            return mListRoutes.size();
        }
        return 0;
    }

    public void notifyAdapter(ArrayList<Routes> mListRoutes) {
        this.mListRoutes = mListRoutes;
        notifyDataSetChanged();
    }

    public void updateData(ArrayList<Routes> updatedList) {
        mListRoutes.clear();
        mListRoutes.addAll(updatedList);
        notifyDataSetChanged();
    }

    public class ChooseCarrierViewHolder extends RecyclerView.ViewHolder {

        private final ItemCarriersBinding itemCarriersBinding;

        public ChooseCarrierViewHolder(@NonNull ItemCarriersBinding itemCarriersBinding) {
            super(itemCarriersBinding.getRoot());
            this.itemCarriersBinding = itemCarriersBinding;
        }

    }
}
