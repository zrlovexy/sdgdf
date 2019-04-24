package com.example.geek.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.RlvMeiNvAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.MeiNvBean;
import com.example.geek.presenter.FuLiP;
import com.example.geek.utils.ToastUtil;
import com.example.geek.view.FuLiV;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuLiFragment extends BaseFragment<FuLiV, FuLiP> implements FuLiV {

    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    private int mPage = 1;
    private RlvMeiNvAdapter mAdapter;


    @Override
    protected FuLiP initPresenter() {
        return new FuLiP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fu_li;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager manager = new
                StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        //防止图片在上下滑动的时候移动.
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rlv.setLayoutManager(manager);
        ArrayList<MeiNvBean.ResultsBean> list = new ArrayList<>();
        mAdapter = new RlvMeiNvAdapter(getContext(),list);
        rlv.setAdapter(mAdapter);

        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多
                mPage++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                mPage = 1;
                mAdapter.mList.clear();
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(mPage);
    }

    @Override
    public void onFail(String msg) {
        ToastUtil.showShort(msg);

        finishLoadMoreAndRefresh();
    }

    private void finishLoadMoreAndRefresh() {
        srl.finishRefresh();
        srl.finishLoadMore();
    }

    @Override
    public void setData(MeiNvBean bean) {
        if (bean.getResults() != null && bean.getResults().size()>0){
            mAdapter.addData(bean.getResults());
        }
        finishLoadMoreAndRefresh();
    }

}
