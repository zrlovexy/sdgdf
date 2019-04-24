package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.bean.DailyNewsBean;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public interface DailyNewsV extends BaseMvpView {
    void setData(DailyNewsBean bean);
}
