package com.example.coachticket.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ItemCarriersBinding;
import com.example.coachticket.models.Routes;

import java.util.ArrayList;
import java.util.List;

public class ChooseCarrierAdapter extends RecyclerView.Adapter<ChooseCarrierAdapter.ChooseCarrierViewHolder>{

   private ArrayList<Routes> mListRoutes;
   private Activity context;

   public ChooseCarrierAdapter(Context context, ArrayList<Routes> mListRoutes) {
      this.mListRoutes =  mListRoutes;
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

   public class ChooseCarrierViewHolder extends RecyclerView.ViewHolder {

      private final ItemCarriersBinding itemCarriersBinding;
      public ChooseCarrierViewHolder(@NonNull ItemCarriersBinding itemCarriersBinding) {
         super(itemCarriersBinding.getRoot());
         this.itemCarriersBinding = itemCarriersBinding;
      }

   }

//   private String originTime;
//   private String destinationTime;
//   private String nameProvincesOrigin;
//   private String nameProvincesDestination;
//   private String duration;
//   private String distance;
//   private String price;
//   private String availableSeats;
//   private String nameCarriers;
//
//   public ChooseCarrierAdapter(String originTime, String destinationTime, String nameProvincesOrigin,
//                               String nameProvincesDestination, String duration, String distance,
//                               String price, String availableSeats, String nameCarriers) {
//      this.originTime = originTime;
//      this.destinationTime = destinationTime;
//      this.nameProvincesOrigin = nameProvincesOrigin;
//      this.nameProvincesDestination = nameProvincesDestination;
//      this.duration = duration;
//      this.distance = distance;
//      this.price = price;
//      this.availableSeats = availableSeats;
//      this.nameCarriers = nameCarriers;
//   }
}
