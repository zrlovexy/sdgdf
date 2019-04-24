package com.example.geek.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geek.R;
import com.example.geek.bean.Car;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xts
 *         Created by asus on 2019/4/22.
 */

public class RlvAdapter extends RecyclerView.Adapter{

    private ArrayList<Car> mCars;

    public RlvAdapter(ArrayList<Car> cars) {
        mCars = cars;
    }

    public void setmCars(ArrayList<Car> mCars) {
        this.mCars = mCars;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        vh.mTv.setText(mCars.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.tv)
        TextView mTv;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
