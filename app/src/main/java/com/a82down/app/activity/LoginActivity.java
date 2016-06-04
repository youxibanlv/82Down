package com.a82down.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.base.BaseActivity;
import com.a82down.app.db.dao.UserDao;
import com.a82down.app.db.table.User;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.Constance;
import com.a82down.app.http.MyCallBack;
import com.a82down.app.http.request.LoginReq;
import com.a82down.app.http.response.LoginRsp;
import com.a82down.app.utils.UiUtils;
import com.a82down.app.utils.VerifyUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import gson.Gson;

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

    @ViewInject(R.id.tv_forgot_pass)
    private TextView forgotPass;

    @ViewInject(R.id.tv_register)
    private TextView register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void login(String userName, String password) {
        LoginReq loginReq = new LoginReq(userName,password);
        showProgressDialogCloseDelay("登录中，请稍后...",Constance.DEFAULT_TIMEOUT);
        loginReq.sendRequest(new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                dismissProgressDialog();
                if (!TextUtils.isEmpty(result)){
                    Gson gson = new Gson();
                    LoginRsp rsp = (LoginRsp) BaseResponse.getRsp(result,LoginRsp.class);
                    if (rsp!= null){
                        if (rsp.result == Constance.HTTP_SUCCESS){
                            User user = gson.fromJson(gson.toJson(rsp.resultData), User.class);
                            if (user != null) {
                                UserDao.saveUser(user);
                                UiUtils.showTipToast(true, getString(R.string.login_success));
                            }
                        }else{
                            UiUtils.showTipToast(false, rsp.failReason);
                        }
                    }
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(value = {R.id.btn_login,R.id.btn_qq,R.id.btn_weixin,R.id.btn_weibo,R.id.tv_forgot_pass,R.id.tv_register})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.btn_login:
                String userName = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (!VerifyUtils.checkUserName(userName)){
                    UiUtils.showTipToast(false,getString(R.string.format_error_username));
                    return;
                }
                if (!VerifyUtils.checkPassword(password)){
                    UiUtils.showTipToast(false,getString(R.string.format_error_password));
                    return;
                }
                login(userName,password);
                break;
            case R.id.tv_forgot_pass:

                break;
            case R.id.tv_register:

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
