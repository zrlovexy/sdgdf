package com.example.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.geek.R;
import com.example.geek.adapter.RlvShowAdapter;
import com.example.geek.base.BaseActivity;
import com.example.geek.base.Constants;
import com.example.geek.bean.GoldShowBean;
import com.example.geek.presenter.EmptyP;
import com.example.geek.view.EmptyV;
import com.example.geek.widget.SimpleTouchHelperCallBack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity<EmptyV, EmptyP> implements EmptyV {


    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rlv)
    RecyclerView rlv;

    private ArrayList<GoldShowBean> mList;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {
        mList = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra(Constants.DATA);

        toolBar.setTitle(R.string.special_show);
        toolBar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(toolBar);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        rlv.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(mList);
        rlv.setAdapter(adapter);
        rlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        SimpleTouchHelperCallBack simpleTouchHelperCallBack = new SimpleTouchHelperCallBack(adapter);


        simpleTouchHelperCallBack.setSwipeEnable(false);

        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);

        helper.attachToRecyclerView(rlv);
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, mList);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }


}
