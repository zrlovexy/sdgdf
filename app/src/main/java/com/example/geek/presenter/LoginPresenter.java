package com.example.geek.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.LoginBean;
import com.example.geek.model.LoginModel;
import com.example.geek.net.ResultCallBack;
import com.example.geek.utils.Logger;
import com.example.geek.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginView> {

    private static final String TAG = LoginPresenter.class.getName();
    private LoginModel mainModel;

    public void getData(){
        String data = "网络回来的数据";

        if (mView != null){
            mView.setData(data);
        }
    }

    public void login() {
      String name = mView.getUserName();

      String psd = mView.getPsd();

      if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psd)){
          mView.showToast("用户名或密码不能为空");
          return;
      }

      mainModel.login(name, psd, new ResultCallBack<LoginBean>() {
          @Override
          public void onSuccess(LoginBean bean) {
              Log.e(TAG, "onSuccess: "+bean.toString() );
              if (bean != null){
                  if (bean.getCode() == 200){
                      if (mView != null){
                          mView.showToast("登录成功");
                      }
                  }else {
                      if (mView != null){
                          mView.showToast("登录失败");
                      }
                  }
              }
          }

          @Override
          public void onFail(String msg) {

              Logger.logE(TAG,msg);
              if (mView != null){
                  mView.showToast("登录失败");
              }
          }
      });

    }

    @Override
    protected void initModel() {
        mainModel = new LoginModel();
        models.add(mainModel);
    }
}
