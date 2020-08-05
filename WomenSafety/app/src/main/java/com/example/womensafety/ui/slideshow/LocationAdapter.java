package com.example.womensafety.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.womensafety.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    List<showLocation> listLocation;
    Context ctx;

    public LocationAdapter(Context ctx,List<showLocation> listLocation) {
        this.listLocation = listLocation;
        this.ctx=ctx;
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.show_location_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        final showLocation listloc= listLocation.get(position);
        Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
        try{

            List<Address> addressList = geocoder.getFromLocation(
                    Double.parseDouble(listloc.getLat()), Double.parseDouble(listloc.getLog()), 1);
            Address obj = addressList.get(0);

            holder.area.setText(obj.getAddressLine(0));
            holder.date.setText(listloc.getDate());
            holder.c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent latlog=new Intent(ctx,viewLocationOnMap.class);
                    latlog.putExtra("lat",listloc.getLat());
                    latlog.putExtra("log",listloc.getLog());
                    latlog.putExtra("id",listloc.getId());
                    ctx.startActivity(latlog);
                }
            });
//            String add = obj.getAddressLine(0);
//            add = add + "\n" + obj.getCountryNamee.printStackTrace();();
//            add = add + "\n" + obj.getCountryCode();
//            add = add + "\n" + obj.getAdminArea();
//            add = add + "\n" + obj.getPostalCode();
//            add = add + "\n" + obj.getSubAdminArea();
//            add = add + "\n" + obj.getLocality();
//            add = add + "\n" + obj.getSubThoroughfare();
//
//            Log.v("IGA", "Address" + add);
//             Toast.makeText(ctx, "Address=>" + add,
//             Toast.LENGTH_SHORT).show();
            }catch (IOException e){

        }

    }

    @Override
    public int getItemCount() {
        return listLocation.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date,area,state;
        CardView c1;
        public ViewHolder(View itemView) {
            super(itemView);
            area=itemView.findViewById(R.id.area);
            c1=itemView.findViewById(R.id.c1);
            date=itemView.findViewById(R.id.date);
        }
    }
}
