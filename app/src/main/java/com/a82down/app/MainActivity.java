package com.a82down.app;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a82down.app.base.BaseActivity;
import com.a82down.app.db.dao.UserDao;
import com.a82down.app.db.table.User;
import com.a82down.app.http.Constance;
import com.a82down.app.http.MyCallBack;
import com.a82down.app.http.request.RegisterReq;
import com.a82down.app.http.response.RegisterRsp;
import com.a82down.app.utils.LogFactory;
import com.a82down.app.utils.UiUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import gson.Gson;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.et_user)
    private EditText etUser;

    @ViewInject(R.id.et_pass)
    private EditText etPass;

    @ViewInject(R.id.btn_login)
    private Button btnLogin;

    @ViewInject(R.id.btn_register)
    private Button btnRegister;


    @Event(value = {R.id.btn_login, R.id.btn_register})
    private void getEvent(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                UiUtils.showTipToast(false, "login");
                break;
            case R.id.btn_register:
                String userName = etUser.getText().toString();
                String password = etPass.getText().toString();
                register(userName, password);
                break;
        }
    }

    private void register(String userName, String password) {
        RegisterReq registerReq = new RegisterReq(userName, password);
        registerReq.sendRequest(new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    LogFactory.e(result);
                    Gson gson = new Gson();
                    RegisterRsp rsp = gson.fromJson(result, RegisterRsp.class);
                    if (rsp != null) {
                        if (rsp.result == Constance.HTTP_SUCCESS) {
                            User user = gson.fromJson(gson.toJson(rsp.resultData), User.class);
                            if (user != null) {
                                UserDao.saveUser(user);
                                LogFactory.e(user.toString());
                                UiUtils.showTipToast(true, getString(R.string.register_success));
                            }
                        }else {
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
}
