package com.example.geek.net;

import com.example.geek.bean.MeiNvBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FuLiService {

    String sBaseUrl = "http://gank.io/api/";

    @GET("data/%E7%A6%8F%E5%88%A9/10/{page}")
    Observable<MeiNvBean> getMeiNvData(@Path("page") int page);

}
