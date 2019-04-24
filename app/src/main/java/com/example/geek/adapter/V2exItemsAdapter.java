package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geek.R;
import com.example.geek.bean.V2exItemsBean;

import java.util.ArrayList;

public class V2exItemsAdapter extends RecyclerView.Adapter<V2exItemsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<V2exItemsBean> list;

    public V2exItemsAdapter(Context context, ArrayList<V2exItemsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<V2exItemsBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_v2exitems, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        V2exItemsBean bean = list.get(position);

        holder.author.setText(bean.getAuthor());
        holder.commentCount.setText(bean.getCommentCount());
        holder.text1.setText(bean.getText1());
        holder.text.setText(bean.getText());
        holder.commentPeople.setText(bean.getCommentPeople());

        Glide.with(context).load(bean.getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView text;
        private TextView text1;
        private TextView author;
        private TextView commentPeople;
        private Button commentCount;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            text = itemView.findViewById(R.id.text);
            text1 = itemView.findViewById(R.id.text1);
            author = itemView.findViewById(R.id.author);
            commentPeople = itemView.findViewById(R.id.commentPeople);
            commentCount = itemView.findViewById(R.id.commentCount);
        }
    }
}
