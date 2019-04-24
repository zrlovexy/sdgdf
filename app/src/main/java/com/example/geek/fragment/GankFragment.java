package com.example.geek.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.VpAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.GankP;
import com.example.geek.view.GankV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankV, GankP>
        implements GankV {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    private ArrayList<Fragment> list;
    private ArrayList<String> titles;
    private VpAdapter my;

    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        titles = new ArrayList<>();

        list.add(new FuLiFragment());
        list.add(new FuLiFragment());
        list.add(new FuLiFragment());
        list.add(new FuLiFragment());

        titles.add("ANDROID");
        titles.add("IOS");
        titles.add("前端");
        titles.add("福利");

        tab.setupWithViewPager(vp);

        my = new VpAdapter(getChildFragmentManager(), list, titles);
        vp.setAdapter(my);
    }
}
