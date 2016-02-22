package com.click_labs.kashishnalwa.loginappassignment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.click_labs.kashishnalwa.loginappassignment.Activity.DisplayDrivers;
import com.click_labs.kashishnalwa.loginappassignment.Activity.EditDriverInfo;
import com.click_labs.kashishnalwa.loginappassignment.Model.DriverData;
import com.click_labs.kashishnalwa.loginappassignment.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by kashish nalwa on 12-02-2016.
 */
public class VivzAdapter extends RecyclerView.Adapter<VivzAdapter.MyViewHolder> {
    LayoutInflater inflater;
    Activity context;
    List<DriverData> data = Collections.emptyList();

    public VivzAdapter(Activity context, List<DriverData> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context=context;
    }


    public void setDataAndNotifiy(List<DriverData> data)
    {
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DriverData driverData = data.get(position);
        holder.displayDriverName.setText(driverData.getName());
        holder.displayDriverAddress.setText(driverData.getAddress());
        holder.pos = position;
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyViewHolder holder = (MyViewHolder) v.getTag();
                DriverData driverDataPassed = data.get(holder.pos);
                Intent intent = new Intent(context, EditDriverInfo.class);
                intent.putExtra("hello", driverDataPassed);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView displayDriverName;
        TextView displayDriverAddress;
        int pos;

        public MyViewHolder(View itemView) {
            super(itemView);
            displayDriverName = (TextView) itemView.findViewById(R.id.driver_enter_name_tv);
            displayDriverAddress = (TextView) itemView.findViewById(R.id.driver_enter_address_tv);

        }
    }
}