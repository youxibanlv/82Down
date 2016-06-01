package com.a82down.app;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a82down.app.base.BaseActivity;
import com.a82down.app.utils.UiUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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

    @Event(value = {R.id.btn_login,R.id.btn_register})
   private void getEvent(View view){

       switch (view.getId()){
           case R.id.btn_login:
               UiUtils.showTipToast(false,"login");
               break;
           case R.id.btn_register:

                UiUtils.showTipToast(true,"register");
               break;
       }
   }
}
