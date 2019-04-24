package com.example.geek.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.geek.R;
import com.example.geek.adapter.V2exItemsAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.V2exItemsBean;
import com.example.geek.presenter.EmptyP;
import com.example.geek.view.EmptyV;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 * Created by asus on 2019/4/3.
 */

@SuppressLint("ValidFragment")
public class V2exItemsFragment extends BaseFragment<EmptyV, EmptyP>
        implements EmptyV {

    @BindView(R.id.rl)
    RecyclerView rl;

    private static final String TAG = "V2exFragment";

    private String mUrl = "https://www.v2ex.com";
    private ArrayList<V2exItemsBean> itemsList;
    private V2exItemsAdapter my;
    private String link;

    @SuppressLint("ValidFragment")
    public V2exItemsFragment(String link) {
       this.link = link;
    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2exitems;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {

            private String commentPeople;
            private String author;
            private String commentCount;
            private String src;

            @Override
            public void run() {

                try {
                    Document doc = Jsoup.connect(mUrl + link).get();

                    //新闻的子条目数据
                    Elements items = doc.select("div.cell.item");
                    for (Element element:items) {
                        //图片
                        Element image = element.select("table tbody tr td > a >img.avatar").first();
                        src = image.attr("src");
                        Log.d(TAG, "图片: "+src);
//                        bean.setImage(src);

                        //评论,有可能没有,需要判空
                        Element comment = element.select("table tbody tr td >a.count_livid").first();
                        if (comment != null) {
                            commentCount = comment.text();
                            String href = comment.attr("href");

//                            bean.setCommentCount(commentCount);
                        }

                        //新闻的主体信息
                        Element titleElement = element.select("table tbody tr td span.item_title > a").first();
                        String text = titleElement.text();

//                        bean.setText(text);

                        //评论的信息
                        Elements topicElement = element.select("table tbody tr td span.topic_info");
                        String topic = topicElement.text();

                        Element secondTab = topicElement.select("a.node").first();
                        String text1 = secondTab.text();
//                        bean.setText1(text1);

                        Elements people = topicElement.select("strong > a");
                        if (people.size() > 0) {
                            Element authorEl = people.get(0);
                            author = authorEl.text();
//                            bean.setAuthor(author);
                        }

                        if (people.size() > 1) {
                            Element commentPeopleEl = people.get(1);
                            commentPeople = commentPeopleEl.text();
//                            bean.setCommentPeople(commentPeople);
                        }

                        V2exItemsBean bean = new V2exItemsBean("https:" +src, commentCount, text, text1, author, commentPeople);

                        itemsList.add(bean);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        my.setList(itemsList);
                        my.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void initView() {
        itemsList = new ArrayList<>();

        rl.setLayoutManager(new LinearLayoutManager(getActivity()));

        my = new V2exItemsAdapter(getActivity(), itemsList);
        rl.setAdapter(my);
    }
}
