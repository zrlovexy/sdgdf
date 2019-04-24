package com.example.geek.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.geek.R;
import com.example.geek.base.BaseApp;
import com.example.geek.bean.MeiNvBean;
import com.example.geek.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author xts
 *         Created by asus on 2019/4/23.
 */

public class RlvMeiNvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<MeiNvBean.ResultsBean> mList;

    public RlvMeiNvAdapter(Context context, ArrayList<MeiNvBean.ResultsBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_img, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        MeiNvBean.ResultsBean bean = mList.get(position);

        //在设置图片之前把ImageView的宽高重新设置一下
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) vh.mIv.getLayoutParams();
        int imageWidth = BaseApp.mWidthPixels / 2 - SystemUtil.dp2px(8);
        layoutParams.width = imageWidth;
        if (bean.getScale() != 0){
            layoutParams.height = (int) (imageWidth/bean.getScale());
        }
        layoutParams.leftMargin = layoutParams.topMargin = SystemUtil.dp2px(4);
        vh.mIv.setLayoutParams(layoutParams);

        RequestOptions options = new RequestOptions()
                .transform(new RoundedCornersTransformation(SystemUtil.dp2px(6),0));
        Glide.with(mContext).load(bean.getUrl()).apply(options).into(vh.mIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<MeiNvBean.ResultsBean> results) {
        mList.addAll(results);
        setImageScale();
    }

    //计算图片的宽高比
    private void setImageScale() {
        for (final MeiNvBean.ResultsBean bean :mList) {
            if (bean.getScale() == 0) {
                //未计算过宽高比
                Glide.with(mContext).load(bean.getUrl()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {
                        //宽高比
                        float scale = resource.getIntrinsicWidth() * 1.0f / resource.getIntrinsicHeight();
                        bean.setScale(scale);
                        notifyDataSetChanged();
                    }
                });
            }else {
                notifyDataSetChanged();
            }
        }
    }

    class VH extends RecyclerView.ViewHolder{

        @BindView(R.id.iv)
        ImageView mIv;
        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
