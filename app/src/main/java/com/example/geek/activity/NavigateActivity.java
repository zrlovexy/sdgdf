package com.example.geek.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.geek.R;
import com.example.geek.adapter.RlvAdapter;
import com.example.geek.base.BaseActivity;
import com.example.geek.bean.Car;
import com.example.geek.presenter.EmptyP;
import com.example.geek.utils.ToastUtil;
import com.example.geek.view.EmptyV;
import com.example.geek.widget.FlowLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import qdx.stickyheaderdecoration.NormalDecoration;

public class NavigateActivity extends BaseActivity<EmptyV, EmptyP> implements EmptyV {


    private static final String TAG = "NavigateActivity";

  /*  @BindView(R.id.rlv)
    RecyclerView rlv;*/

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.fl)
    FlowLayout fl;


    private String mUrl = "https://www.v2ex.com/";
    private ArrayList<Car> cars;
    private Car car;
    private RlvAdapter rlvAdapter;


    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navigate;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {

            private String title;
            private String titleTop;

            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();

                  /*  Elements tabs = doc.select("div.fr");

                    for (Element elements : tabs) {
                        Elements tab = elements.select("a[href]");
                        if (tab.size() > 2) {
                            Element element = tab.get(2);
                            String text = element.text();
                            Log.e(TAG, "标题: " + text);
                        }

                    }*/

                    Elements items = doc.select("div.cell");

                    for (Element element : items) {
                        Elements titleTops = element.select("table tbody tr td span.fade");
                        titleTop = titleTops.text();
                        Log.e(TAG, "标题: " + titleTop);

                        if (titleTop.length() > 0) {
                            Elements titles = element.select("table tbody tr td > a");
                            title = titles.text();
//                       Log.e(TAG, "标题: "+title);

                            car = new Car(title, titleTop);
                            cars.add(car);
                        }

                    }

                    Elements inner = doc.select("div.inner");

                    for (Element element : inner) {
                        Elements titleTops = element.select("table tbody tr td span.fade");

                        titleTop = titleTops.text();
//                       Log.e(TAG, "标题: "+titleTop);

                        if (titleTop.length() > 0) {
                            Elements titles = element.select("table tbody tr td > a");
                            title = titles.text();
//                       Log.e(TAG, "标题: "+title);

                            car = new Car(title, titleTop);
                            cars.add(car);
                        }

                    }


//                    Log.e(TAG, "集合: "+cars.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*rlvAdapter.setmCars(cars);
                        rlvAdapter.notifyDataSetChanged();*/

                        for (int i = 0; i < cars.size(); i++) {
                            //获取视图,视图可以自定义,可以添加自己想要的效果
                            TextView label = (TextView) View.inflate(NavigateActivity.this, R.layout.item_label, null);
                            //获取数据
                            final Car car = cars.get(i);
                            //绑定数据
                            label.setText(car.getName());

                            label.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showToast(car.getName());
                                }
                            });

                            //加到容器中,parent是FlowLayout
                            fl.addView(label);
                        }
                    }
                });

            }
        }).start();


    }

    private void showToast(String data) {
        ToastUtil.showShort(data);
    }

    @Override
    protected void initView() {

        toolBar.setTitle("节点导航");
        toolBar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(toolBar);

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cars = new ArrayList<>();

//        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlvAdapter = new RlvAdapter(cars);
        //返回头布局的内容

        final NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return cars.get(i).header;
            }
        };





//
//        rlv.addItemDecoration(decoration);
//        rlv.setAdapter(rlvAdapter);


    }

}
