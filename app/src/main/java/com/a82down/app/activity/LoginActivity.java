package com.a82down.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.a82down.app.R;
import com.a82down.app.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by xiaowuyue on 16/6/3.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.edt_username)
    private EditText edtUsername;

    @ViewInject(R.id.edt_password)
    private EditText edtPassword;

    @ViewInject(R.id.btn_login)
    private Button btnLogin;

    @ViewInject(R.id.btn_qq)
    private ImageView btnQq;

    @ViewInject(R.id.btn_weixin)
    private ImageView btnWeixin;

    @ViewInject(R.id.btn_weibo)
    private ImageView btnWibo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }


    private void getEvent(View view){
        switch (view.getId()){
            case R.id.btn_login:

                break;
            case R.id.btn_qq:

                break;
            case R.id.btn_weixin:

                break;
            case R.id.btn_weibo:

                break;
        }
    }
}
