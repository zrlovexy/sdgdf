package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geek.R;
import com.example.geek.bean.SectionsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SectionsBean.DataBean> list;

    public SectionsAdapter(Context context, ArrayList<SectionsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_sections, null);
        return new ViewHolder(inflate);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.description.setText(list.get(i).getDescription());
        viewHolder.name.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getThumbnail()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(SectionsBean bean) {
        List<SectionsBean.DataBean> data = bean.getData();

        list.clear();

        if (bean.getData() != null && bean.getData().size() > 0){
            list.addAll(bean.getData());
        }

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.name)
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
