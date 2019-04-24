package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.bean.MeiNvBean;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public interface FuLiV extends BaseMvpView {

    void setData(MeiNvBean bean);

    void onFail(String msg);
}
