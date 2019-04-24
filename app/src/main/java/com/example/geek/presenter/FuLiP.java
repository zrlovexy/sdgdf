package com.example.geek.presenter;


import com.example.geek.base.BasePresenter;
import com.example.geek.bean.MeiNvBean;
import com.example.geek.model.FuLiM;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.EmptyV;
import com.example.geek.view.FuLiV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class FuLiP extends BasePresenter<FuLiV> {

    private FuLiM fuLiM;

    @Override
    protected void initModel() {
        fuLiM = new FuLiM();
        models.add(fuLiM);
    }

    public void getData(int mPage) {
        fuLiM.getData(mPage, new ResultCallBack<MeiNvBean>() {
            @Override
            public void onSuccess(MeiNvBean bean) {
                if (mView != null){
                    mView.setData(bean);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView != null){
                    mView.onFail(msg);
                }
            }
        });
    }
}
