package com.a82down.app.activity;

import android.content.Intent;
import android.view.View;

import com.a82down.app.MainActivity;
import com.a82down.app.R;
import com.a82down.app.base.BaseActivity;
import com.a82down.app.db.dao.UserDao;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by strike on 16/6/7.
 */
@ContentView(R.layout.activity_user_info)
public class UserInfoActivity extends BaseActivity {

    @Event(value = {R.id.btn_logout})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.btn_logout:
                UserDao.logOut();
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
        }
    }
}
