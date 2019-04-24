package com.example.geek.net;


import com.example.geek.bean.HotBean;
import com.example.geek.bean.SectionsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 */

public interface HotService {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("news/hot ")
    Observable<HotBean> getLastHot();
}
