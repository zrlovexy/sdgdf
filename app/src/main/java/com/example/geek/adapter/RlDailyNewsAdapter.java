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
import com.example.geek.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlDailyNewsAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<DailyNewsBean.StoriesBean> newsList;
    private ArrayList<DailyNewsBean.TopStoriesBean> banners;

    public static final int TYPE_BANNER = 0;
    public static final int TYPE_TIME = 1;
    public static final int TYPE_NEWS = 2;
    private String mDate = "今日新闻";


    public RlDailyNewsAdapter(Context context, ArrayList<DailyNewsBean.StoriesBean> newsList, ArrayList<DailyNewsBean.TopStoriesBean> banners) {
        this.context = context;
        this.newsList = newsList;
        this.banners = banners;
    }

    @Override
    public int getItemViewType(int position) {
        if (banners .size() > 0){
            if (position == 0){
                return TYPE_BANNER;
            } else if (position == 1) {
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }else {
            if (position == 0){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == TYPE_BANNER){
            return new BannerVH(inflater.inflate(R.layout.layout_banner,null));
        }else if (viewType == TYPE_TIME){
            return new TimeVH(inflater.inflate(R.layout.layout_time,null));
        }else {
            return new NewsVH(inflater.inflate(R.layout.layout_news,null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);

        if (viewType == TYPE_BANNER){
            BannerVH bannerVH = (BannerVH) viewHolder;

            bannerVH.mBanner.setImages(banners).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DailyNewsBean.TopStoriesBean bean = (DailyNewsBean.TopStoriesBean) path;
                    Glide.with(context).load(bean.getImage()).into(imageView);
                }
            }).start();
        }else if (viewType == TYPE_TIME){
            TimeVH timeVH = (TimeVH) viewHolder;

            timeVH.mTvTime.setText(mDate);
        }else {
            NewsVH newsVH = (NewsVH) viewHolder;

            int newsPosition = i - 1;

            if (banners.size() > 0){
                newsPosition -= 1;
            }

            final DailyNewsBean.StoriesBean storiesBean = newsList.get(newsPosition);

            newsVH.mTvTime.setText(storiesBean.getTitle());
            Glide.with(context).load(storiesBean.getImages().get(0)).into(newsVH.img);

            newsVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClick(storiesBean);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (banners.size() > 0){
            return newsList.size() + 1 + 1;
        }else {
            return newsList.size() + 1;
        }
    }

    public void setData(DailyNewsBean bean){
        mDate = bean.getDate();

        banners.clear();

        if (bean.getTop_stories() != null && bean.getTop_stories().size() > 0){
            banners.addAll(bean.getTop_stories());
        }

        newsList.clear();

        if (bean.getStories() != null && bean.getStories().size() > 0){
            newsList.addAll(bean.getStories());
        }

        notifyDataSetChanged();
    }

    class BannerVH extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner mBanner;
        public BannerVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class TimeVH extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_time)
        TextView mTvTime;
        public TimeVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class NewsVH extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView mTvTime;

        @BindView(R.id.img)
        ImageView img;

        public NewsVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(DailyNewsBean.StoriesBean storiesBean);
    }
}
