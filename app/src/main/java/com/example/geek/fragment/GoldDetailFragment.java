package com.example.geek.fragment;

import android.os.Bundle;
import android.widget.TextView;


import com.example.geek.R;
import com.example.geek.base.BaseFragment;
import com.example.geek.base.Constants;
import com.example.geek.presenter.EmptyP;
import com.example.geek.view.EmptyV;

import butterknife.BindView;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class GoldDetailFragment extends BaseFragment<EmptyV,EmptyP>
        implements EmptyV {
    @BindView(R.id.tv)
    TextView mTv;

    /**
     *
     * @param text 简单文本
     * @return
     */
    public static GoldDetailFragment newInstance(String text){
        GoldDetailFragment fragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA,text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        String data = arguments.getString(Constants.DATA);
        mTv.setText(data);
    }
}
