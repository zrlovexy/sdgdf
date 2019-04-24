package com.example.geek.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geek.R;
import com.example.geek.activity.NavigateActivity;
import com.example.geek.adapter.V2exItemAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.V2exTabBean;
import com.example.geek.presenter.V2exP;
import com.example.geek.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

public class V2exFragment extends BaseFragment<V2exV, V2exP>
        implements V2exV {

    private static final String TAG = "V2exFragment";

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;

    @BindView(R.id.iv)
    ImageView iv;

    private String mUrl = "https://www.v2ex.com/";
    private ArrayList<V2exTabBean> tabList;
    private ArrayList<Fragment> list;
    private V2exItemAdapter my;

    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    tabList = new ArrayList<>();

                    Document doc = Jsoup.connect(mUrl).get();

                    Element tabs = doc.select("div#Tabs").first();

                    Elements allTabs = tabs.select("a[href]");

                    for (Element element : allTabs) {
                        String linkHref = element.attr("href");
                        String tab = element.text();
                        V2exTabBean bean = new V2exTabBean(linkHref, tab);
                        tabList.add(bean);
                    }

                   getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           for (V2exTabBean bean : tabList) {
                               tab.addTab(tab.newTab().setText(bean.tab));
                               list.add(new V2exItemsFragment(bean.link));
                           }

                           tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                               @Override
                               public void onTabSelected(TabLayout.Tab tab) {
                                   vp.setCurrentItem(tab.getPosition());
                               }

                               @Override
                               public void onTabUnselected(TabLayout.Tab tab) {

                               }

                               @Override
                               public void onTabReselected(TabLayout.Tab tab) {

                               }
                           });

                           vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

                           my = new V2exItemAdapter(getChildFragmentManager(), list);
                           vp.setAdapter(my);
                       }
                   });



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void initView() {

        list = new ArrayList<>();

    }

    @OnClick({R.id.iv})
    public void click(View v){
        switch (v.getId()) {
            case R.id.iv:
                go2NavigateActivity();
                break;
        }
    }

    private void go2NavigateActivity() {
        Intent intent = new Intent(getActivity(), NavigateActivity.class);
        startActivity(intent);
    }
}
