package com.example.geek.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.HotAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.HotBean;
import com.example.geek.presenter.EmptyP;
import com.example.geek.presenter.HotP;
import com.example.geek.view.EmptyV;
import com.example.geek.view.HotV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class HotFragment extends BaseFragment<HotV, HotP>
        implements HotV {
    @BindView(R.id.rl)
    RecyclerView rl;
    private ArrayList<HotBean.RecentBean> list;
    private HotAdapter my;


    @Override
    protected HotP initPresenter() {
        return new HotP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        rl.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();

        my = new HotAdapter(getContext(), list);
        rl.setAdapter(my);


    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void setData(HotBean bean) {
        my.setData(bean);
    }
}
