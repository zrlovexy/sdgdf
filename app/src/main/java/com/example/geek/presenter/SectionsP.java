package com.example.geek.presenter;


import com.example.geek.base.BasePresenter;
import com.example.geek.bean.DailyNewsBean;
import com.example.geek.bean.SectionsBean;
import com.example.geek.model.DailyNewsM;
import com.example.geek.model.SectionsM;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.EmptyV;
import com.example.geek.view.SectionsV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class SectionsP extends BasePresenter<SectionsV> {
    private SectionsM sectionsM;

    @Override
    protected void initModel() {
        sectionsM = new SectionsM();
        models.add(sectionsM);
    }

    public void getData() {
        sectionsM.getData(new ResultCallBack<SectionsBean>() {

            @Override
            public void onSuccess(SectionsBean bean) {
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
