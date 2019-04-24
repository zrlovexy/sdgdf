package com.example.geek.model;

import com.example.geek.base.BaseModel;
import com.example.geek.bean.DailyNewsBean;
import com.example.geek.bean.HotBean;
import com.example.geek.net.BaseObserver;
import com.example.geek.net.HotService;
import com.example.geek.net.HttpUtils;
import com.example.geek.net.ResultCallBack;
import com.example.geek.net.RxUtils;
import com.example.geek.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HotM extends BaseModel {
    public void getData(final ResultCallBack<HotBean> resultCallBack) {
        HotService apiserver = HttpUtils.getInstance().getApiserver(HotService.sBaseUrl, HotService.class);
        Observable<HotBean> call = apiserver.getLastHot();
        call.compose(RxUtils.<HotBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        resultCallBack.onSuccess(hotBean);
                    }
                });
    }
}
