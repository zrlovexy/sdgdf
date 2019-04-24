package com.example.geek.net;

import com.example.geek.bean.LoginBean;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface ResultCallBack<D> {
    void onSuccess(D bean);
    void onFail(String msg);
}
