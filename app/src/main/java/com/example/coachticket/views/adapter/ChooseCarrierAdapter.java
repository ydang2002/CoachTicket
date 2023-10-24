package com.example.coachticket.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachticket.R;
import com.example.coachticket.databinding.ItemCarriersBinding;
import com.example.coachticket.models.Routes;

import java.util.List;

public class ChooseCarrierAdapter extends RecyclerView.Adapter<ChooseCarrierAdapter.ChooseCarrierViewHolder>{

   private final List<Routes> mListRoutes;

   public ChooseCarrierAdapter(List<Routes> mListRoutes) {
      this.mListRoutes = mListRoutes;
   }

   @NonNull
   @Override
   public ChooseCarrierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      ItemCarriersBinding itemCarriersBinding = DataBindingUtil.inflate(LayoutInflater
              .from(parent.getContext()), R.layout.item_carriers, parent, false);
      return new ChooseCarrierViewHolder(itemCarriersBinding);
   }

   @Override
   public void onBindViewHolder(@NonNull ChooseCarrierViewHolder holder, int position) {
      Routes routes = mListRoutes.get(position);
      holder.itemCarriersBinding.setRoutes(routes);
   }

   @Override
   public int getItemCount() {
      if (mListRoutes != null) {
         return mListRoutes.size();
      }
      return 0;
   }

   public static class ChooseCarrierViewHolder extends RecyclerView.ViewHolder {

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
