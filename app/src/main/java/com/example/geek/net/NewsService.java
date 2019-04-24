package com.example.geek.net;


import com.example.geek.bean.HotBean;
import com.example.geek.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 */

public interface NewsService {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("news/{news_id} ")
    Observable<NewsBean> getLastNews(@Path("news_id") int news_id);
}
