package com.example.geek.net;


import com.example.geek.bean.SectionsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 */

public interface SectionsService {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("sections ")
    Observable<SectionsBean> getLastSections();
}
