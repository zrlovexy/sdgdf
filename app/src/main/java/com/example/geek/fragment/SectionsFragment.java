package com.example.geek.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.SectionsAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.SectionsBean;
import com.example.geek.presenter.EmptyP;
import com.example.geek.presenter.SectionsP;
import com.example.geek.view.EmptyV;
import com.example.geek.view.SectionsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class SectionsFragment extends BaseFragment<SectionsV, SectionsP>
        implements SectionsV {
    @BindView(R.id.rl)
    RecyclerView rl;
    private ArrayList<SectionsBean.DataBean> list;
    private SectionsAdapter my;


    @Override
    protected SectionsP initPresenter() {
        return new SectionsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sections;
    }

    @Override
    protected void initView() {
        rl.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        list = new ArrayList<>();

        my = new SectionsAdapter(getContext(), list);
        rl.setAdapter(my);

    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }


    @Override
    public void setData(SectionsBean bean) {
        my.setData(bean);
    }
}
