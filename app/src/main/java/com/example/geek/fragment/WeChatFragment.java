package com.example.geek.fragment;

import com.example.geek.R;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.WeChatP;
import com.example.geek.view.WeChatV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class WeChatFragment extends BaseFragment<WeChatV,WeChatP>
        implements WeChatV {
    @Override
    protected WeChatP initPresenter() {
        return new WeChatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }
}
