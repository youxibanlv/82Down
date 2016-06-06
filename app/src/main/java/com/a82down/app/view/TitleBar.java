package com.a82down.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.a82down.app.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by strike on 16/6/6.
 */
public class TitleBar extends RelativeLayout {

    @ViewInject(R.id.rv_user_icon)
    private RoundImageView rvUserIcon;

    @ViewInject(R.id.iv_manager)
    private ImageView ivManager;

    @ViewInject(R.id.edt_search)
    private EditText edtSearch;

    private View view;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.extr_title,this,true);
        x.view().inject(view);
    }

    public TitleBar(Context context) {
        super(context);
    }
    @Event(value = {R.id.rv_user_icon,R.id.iv_manager,R.id.edt_search})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.rv_user_icon://用户信息界面

                break;
            case R.id.iv_manager://app管理界面

                break;
            case R.id.edt_search://搜索界面

                break;
        }
    }
}
