package com.example.geek.presenter;


import com.example.geek.base.BasePresenter;
import com.example.geek.bean.DailyNewsBean;
import com.example.geek.bean.HotBean;
import com.example.geek.model.DailyNewsM;
import com.example.geek.model.HotM;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.DailyNewsV;
import com.example.geek.view.HotV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class HotP extends BasePresenter<HotV> {

    private HotM hotM;

    @Override
    protected void initModel() {
        hotM = new HotM();
        models.add(hotM);
    }

    public void getData() {
        hotM.getData(new ResultCallBack<HotBean>() {

            @Override
            public void onSuccess(HotBean bean) {
                if (bean != null){
                    if (mView != null){
                        mView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
