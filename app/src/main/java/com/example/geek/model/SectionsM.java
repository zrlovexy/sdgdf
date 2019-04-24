package com.example.geek.model;

import com.example.geek.base.BaseModel;
import com.example.geek.bean.DailyNewsBean;
import com.example.geek.bean.SectionsBean;
import com.example.geek.net.BaseObserver;
import com.example.geek.net.HttpUtils;
import com.example.geek.net.ResultCallBack;
import com.example.geek.net.RxUtils;
import com.example.geek.net.SectionsService;
import com.example.geek.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SectionsM extends BaseModel {
    public void getData(final ResultCallBack<SectionsBean> resultCallBack) {
        SectionsService apiserver = HttpUtils.getInstance().getApiserver(SectionsService.sBaseUrl, SectionsService.class);
        Observable<SectionsBean> call = apiserver.getLastSections();
        call.compose(RxUtils.<SectionsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SectionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SectionsBean sectionsBean) {
                        resultCallBack.onSuccess(sectionsBean);
                    }
                });
    }
}
