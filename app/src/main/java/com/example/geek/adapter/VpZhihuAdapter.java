package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.geek.base.BaseFragment;

import java.util.ArrayList;

public class VpZhihuAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<BaseFragment> fragments;
    private ArrayList<Integer> titles;

    public VpZhihuAdapter(Context context, FragmentManager childFragmentManager, ArrayList<BaseFragment> fragments, ArrayList<Integer> titles) {
        super(childFragmentManager);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(titles.get(position));
    }
}
