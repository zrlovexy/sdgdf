package com.example.geek.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.geek.R;
import com.example.geek.base.BaseActivity;
import com.example.geek.presenter.LoginPresenter;
import com.example.geek.utils.ToastUtil;
import com.example.geek.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.et_name)
    EditText et_Name;
    @BindView(R.id.et_password)
    EditText et_Password;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
*/


    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.btn})
    public void onViewClicked(View v) {
        presenter.login();
    }

    @Override
    public void setData(String data) {
        btn.setText(data);
    }

    @Override
    public String getUserName() {
        return et_Name.getText().toString().trim();
    }

    @Override
    public String getPsd() {
        return et_Password.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
