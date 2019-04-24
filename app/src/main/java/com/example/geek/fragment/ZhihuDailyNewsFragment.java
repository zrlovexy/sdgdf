package com.example.geek.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.geek.R;
import com.example.geek.adapter.VpZhihuAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.ZhihuDailyNewsP;
import com.example.geek.view.ZhihuDailyNewsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class ZhihuDailyNewsFragment extends BaseFragment<ZhihuDailyNewsV, ZhihuDailyNewsP>
        implements ZhihuDailyNewsV {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Integer> titles;
    private ArrayList<BaseFragment> fragments;

    @Override
    protected ZhihuDailyNewsP initPresenter() {
        return new ZhihuDailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily_news;
    }

    @Override
    protected void initView() {
        initTitles();
        initFragments();

        VpZhihuAdapter my = new VpZhihuAdapter(getContext(),getChildFragmentManager(),fragments,titles);
        vp.setAdapter(my);
        tab.setupWithViewPager(vp);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new DailyNewsFragment());
        fragments.add(new SectionsFragment());
        fragments.add(new HotFragment());
    }

    private void initTitles() {
        titles = new ArrayList<>();

        titles.add(R.string.dailyNews);
        titles.add(R.string.sections);
        titles.add(R.string.hot);
    }

}
