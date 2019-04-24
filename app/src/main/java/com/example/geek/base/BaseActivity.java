package com.example.geek.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseMvpView,P extends BasePresenter> extends AppCompatActivity implements BaseMvpView {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = initPresenter();
        if (presenter != null){
            presenter.bind( (V)this);
        }
        initView();
        initListener();
        initData();
        initEventAndData();
    }

    protected  void initEventAndData(){

    }

    protected abstract P initPresenter();

    protected void initData() {


    }

    protected void initListener(){

    };

    protected  void initView(){

    };

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
        presenter = null;
    }
}
