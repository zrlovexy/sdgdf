package com.example.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.geek.R;
import com.example.geek.bean.HotBean;
import com.example.geek.bean.NewsBean;
import com.example.geek.net.NewsService;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaterialActivity extends AppCompatActivity{

    private Toolbar toolBar;
    private CollapsingToolbarLayout ctl;
    private AppBarLayout appBar;
    private RecyclerView rl;
    private FloatingActionButton fab;
    private int id;
    private ArrayList<NewsBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_activity);
        initView();
        initData();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsService.sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        NewsService newsService = retrofit.create(NewsService.class);

        Observable<NewsBean> call = newsService.getLastNews(id);

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView() {
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        appBar = (AppBarLayout) findViewById(R.id.appBar);

        rl = (RecyclerView) findViewById(R.id.rl);

        list = new ArrayList<>();

        fab = (FloatingActionButton) findViewById(R.id.fab);

        //标题必须在CollapsingToolbarLayout设置
        ctl.setTitle("折叠ToolBar");
        //扩张时候的title颜色
        ctl.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
        //收缩后显示title的颜色
        ctl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
    }

}
