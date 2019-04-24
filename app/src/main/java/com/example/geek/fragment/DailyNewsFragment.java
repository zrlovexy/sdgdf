package com.example.geek.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.activity.CalendarActivity;
import com.example.geek.activity.MaterialActivity;
import com.example.geek.adapter.RlDailyNewsAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.DailyNewsBean;
import com.example.geek.presenter.DailyNewsP;
import com.example.geek.utils.CircularAnimUtil;
import com.example.geek.view.DailyNewsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class DailyNewsFragment extends BaseFragment<DailyNewsV, DailyNewsP>
        implements DailyNewsV, RlDailyNewsAdapter.OnItemClickListener {

    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;

    @BindView(R.id.rl)
    RecyclerView rl;
    private RlDailyNewsAdapter my;

    @Override
    protected DailyNewsP initPresenter() {
        return new DailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected void initView() {
        rl.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<DailyNewsBean.StoriesBean> newsList = new ArrayList<>();
        ArrayList<DailyNewsBean.TopStoriesBean> banners = new ArrayList<>();

        my = new RlDailyNewsAdapter(getContext(),newsList,banners);

        my.setOnItemClickListener(this);

        rl.setAdapter(my);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getData();
    }

    @Override
    public void setData(DailyNewsBean bean) {
        my.setData(bean);
    }

    @OnClick(R.id.fab_calender)
    void startCalender() {
        Intent it = new Intent();
        it.setClass(getContext(),CalendarActivity.class);
        CircularAnimUtil.startActivity(getActivity(),it,fabCalender,R.color.fab_bg);
    }

    @Override
    public void onItemClick(DailyNewsBean.StoriesBean storiesBean) {
        Intent intent = new Intent(getActivity(), MaterialActivity.class);
        intent.putExtra("id",storiesBean.getId());
        startActivity(intent);
    }
}
