package com.a82down.app.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.a82down.app.R;
import com.a82down.app.activity.LoginActivity;
import com.a82down.app.activity.UserInfoActivity;
import com.a82down.app.db.dao.UserDao;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.MyCallBack;
import com.a82down.app.http.request.KeywordsReq;
import com.a82down.app.http.response.KeywordsRsp;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by strike on 16/6/6.
 */
public class TitleBar extends RelativeLayout {

    @ViewInject(R.id.rv_user_icon)
    private ImageView rvUserIcon;

    @ViewInject(R.id.iv_manager)
    private ImageView ivManager;

    @ViewInject(R.id.edt_search)
    private EditText edtSearch;

    @ViewInject(R.id.btn_search)
    private ImageView btn_search;

    private View view;

    private int keySize = 15;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.extr_title,this,true);
        x.view().inject(view);
        edtSearch.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    String key = edtSearch.getText().toString();
                    showKeywords(key);
                }
            }
        });
    }

    private void showKeywords(String keyword){
        KeywordsReq req = new KeywordsReq(keyword,keySize);
        req.sendRequest(new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    KeywordsRsp rsp = (KeywordsRsp) BaseResponse.getRsp(result,KeywordsRsp.class);
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(value = {R.id.rv_user_icon,R.id.iv_manager,R.id.btn_search})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.rv_user_icon://用户信息界面
                String token = UserDao.getToken();
                if (TextUtils.isEmpty(token)){
                    getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                }else{
                    getContext().startActivity(new Intent(getContext(), UserInfoActivity.class));
                }
                break;
            case R.id.iv_manager://app管理界面

                break;
            case R.id.btn_search://搜索界面

                break;
        }
    }

}
