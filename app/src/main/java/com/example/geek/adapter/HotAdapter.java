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
import com.example.geek.bean.HotBean;
import com.example.geek.bean.SectionsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HotBean.RecentBean> list;

    public HotAdapter(Context context, ArrayList<HotBean.RecentBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_news, null);
        return new ViewHolder(inflate);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_title.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getThumbnail()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(HotBean bean) {
        List<HotBean.RecentBean> recent = bean.getRecent();

        list.clear();

        if (bean.getRecent() != null && bean.getRecent().size() > 0){
            list.addAll(bean.getRecent());
        }

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_title)
        TextView tv_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
