package com.example.geek.presenter;


import com.example.geek.base.BasePresenter;
import com.example.geek.bean.DailyNewsBean;
import com.example.geek.bean.LoginBean;
import com.example.geek.model.DailyNewsM;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.DailyNewsV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class DailyNewsP extends BasePresenter<DailyNewsV> {

    private DailyNewsM dailyNewsM;

    @Override
    protected void initModel() {
        dailyNewsM = new DailyNewsM();
        models.add(dailyNewsM);
    }

    public void getData() {
        dailyNewsM.getData(new ResultCallBack<DailyNewsBean>() {

            @Override
            public void onSuccess(DailyNewsBean bean) {
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
