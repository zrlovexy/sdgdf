package com.example.geek.model;

import com.example.geek.base.BaseModel;
import com.example.geek.bean.HotBean;
import com.example.geek.bean.MeiNvBean;
import com.example.geek.net.BaseObserver;
import com.example.geek.net.FuLiService;
import com.example.geek.net.HotService;
import com.example.geek.net.HttpUtils;
import com.example.geek.net.ResultCallBack;
import com.example.geek.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class FuLiM extends BaseModel {
    public void getData(int mPage, final ResultCallBack<MeiNvBean> callBack) {
        FuLiService apiserver = HttpUtils.getInstance().getApiserver(FuLiService.sBaseUrl, FuLiService.class);
        apiserver.getMeiNvData(mPage)
                .compose(RxUtils.<MeiNvBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MeiNvBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MeiNvBean meiNvBean) {
                        if (meiNvBean != null && !meiNvBean.isError()){
                            callBack .onSuccess(meiNvBean);
                        }else {
                            callBack.onFail("请求失败");
                        }
                    }
                });
    }
}
