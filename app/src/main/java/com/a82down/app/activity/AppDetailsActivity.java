package com.a82down.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.adapter.DetailsAdapter;
import com.a82down.app.base.BaseActivity;
import com.a82down.app.db.table.App;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.HttpConstance;
import com.a82down.app.http.NormalCallBack;
import com.a82down.app.http.request.AppDetailsReq;
import com.a82down.app.http.response.AppDetailsRsp;
import com.a82down.app.images.ImgConfig;
import com.a82down.app.utils.Constance;
import com.a82down.app.utils.DownLoadUtils;
import com.a82down.app.view.DownloadBtn;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by strike on 16/6/21.
 */
@ContentView(R.layout.activity_detals)
public class AppDetailsActivity extends BaseActivity {


    @ViewInject(R.id.iv_app_icon)
    private ImageView iv_app_icon;

    @ViewInject(R.id.tv_app_title)
    private TextView tv_app_title;

    @ViewInject(R.id.app_score)
    private RatingBar app_score;

    @ViewInject(R.id.tv_size)
    private TextView tv_size;

    @ViewInject(R.id.tv_version)
    private TextView tv_version;

    @ViewInject(R.id.tv_down)
    private DownloadBtn tv_down;

    @ViewInject(R.id.tv_down_num)
    private TextView tv_down_num;

    @ViewInject(R.id.lv_icon)
    private ListView lv_icon;


    private DownLoadUtils downloadUtils;

    private DetailsAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadUtils = new DownLoadUtils(this);
        adapter = new DetailsAdapter(this);
        lv_icon.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String appId = getIntent().getStringExtra(Constance.APP_ID);
        if (appId != null){
            getAppDetails(appId);
            showProgressDialogCloseDelay("加载中，请稍后",HttpConstance.DEFAULT_TIMEOUT);
        }
    }

    @Event(value = {R.id.iv_back})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void getAppDetails(String app_id){

        AppDetailsReq req = new AppDetailsReq(app_id);
        req.sendRequest(new NormalCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    AppDetailsRsp rsp = (AppDetailsRsp) BaseResponse.getRsp(result,AppDetailsRsp.class);
                    if (rsp != null){
                        if (rsp.result == HttpConstance.HTTP_SUCCESS){
                            App app = rsp.getApp();
                            if (app!= null)
                            initView(app);
                        }
                    }
                }

            }

            @Override
            public void onFinished() {
                dismissProgressDialog();
            }
        });
    }

    private void initView(App app) {
        downloadUtils.initDownLoad(app,tv_down);
        if (app.getApp_logo()!= null){
            x.image().bind(iv_app_icon,app.getApp_logo(), ImgConfig.getImgOption());
        }
        if (app.getApp_title()!= null){
            tv_app_title.setText(app.getApp_title());
        }
        int score = app.getApp_recomment() == null ? 0 : (int) (Float.parseFloat(app.getApp_recomment()) / 2);
        app_score.setNumStars(score);
        if (app.getApp_size()!= null){
            tv_size.setText("大小："+app.getApp_size());
        }
        if (app.getApp_version()!= null){
            tv_version.setText("版本："+app.getApp_version());
        }
        if (app.getApp_down()!= null){
            tv_down_num.setText("下载："+app.getApp_down());
        }
        if (app.getResource()!= null){
            List<String> list = new ArrayList<>();
            list.add(0,app.getApp_desc());
            list.addAll(app.getResource());
            adapter.refresh(list);
        }
    }

}
