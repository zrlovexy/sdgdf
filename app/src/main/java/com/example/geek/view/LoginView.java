package com.example.geek.view;

import com.example.geek.base.BaseMvpView;

public interface LoginView extends BaseMvpView {
    void setData(String data);

    String getUserName();
    String getPsd();


    void showToast(String msg);
}
