package com.example.geek.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geek.R;
import com.example.geek.activity.ShowActivity;
import com.example.geek.adapter.VpGoldAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.base.Constants;
import com.example.geek.bean.GoldShowBean;
import com.example.geek.presenter.GoldP;
import com.example.geek.view.GankV;
import com.example.geek.view.GoldV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class GoldFragment extends BaseFragment<GoldV, GoldP>
        implements GankV {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.vp)
    ViewPager vp;

    private ArrayList<GoldShowBean> mList;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected GoldP initPresenter() {
        return new GoldP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        initTitle();
        setFragments();
    }

    private void setFragments() {
        initFragments();

        VpGoldAdapter adapter = new VpGoldAdapter(getChildFragmentManager(), mFragments, mList);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            GoldShowBean bean = mList.get(i);
            if (bean.isChecked){
                mFragments.add(GoldDetailFragment.newInstance(bean.title));
            }
        }
    }

    private void initTitle() {
        mList = new ArrayList<>();
        mList.add(new GoldShowBean("工具资源",true));
        mList.add(new GoldShowBean("Android",true));
        mList.add(new GoldShowBean("iOS",true));
        mList.add(new GoldShowBean("设计",true));
        mList.add(new GoldShowBean("产品",true));
        mList.add(new GoldShowBean("阅读",true));
        mList.add(new GoldShowBean("前端",true));
        mList.add(new GoldShowBean("后端",true));
    }

    @OnClick({R.id.iv})
    public void click(View v){
        switch (v.getId()) {
            case R.id.iv:
                go2ShowActivity();
                break;
        }
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA,mList);
        startActivityForResult(intent,100);
        //getActivity().startActivityForResult();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
                mList = (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
                //刷新界面
                setFragments();
            }

        }
    }
}
