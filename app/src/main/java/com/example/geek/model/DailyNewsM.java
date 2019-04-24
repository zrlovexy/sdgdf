package com.example.geek.model;

import com.example.geek.base.BaseModel;
import com.example.geek.bean.DailyNewsBean;
import com.example.geek.net.BaseObserver;
import com.example.geek.net.HttpUtils;
import com.example.geek.net.ResultCallBack;
import com.example.geek.net.RxUtils;
import com.example.geek.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DailyNewsM extends BaseModel {
    public void getData(final ResultCallBack<DailyNewsBean> resultCallBack) {
        ZhihuService apiserver = HttpUtils.getInstance().getApiserver(ZhihuService.sBaseUrl, ZhihuService.class);
        Observable<DailyNewsBean> call = apiserver.getLastDailyNews();
        call.compose(RxUtils.<DailyNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        resultCallBack.onSuccess(dailyNewsBean);
                    }
                });
    }
}
