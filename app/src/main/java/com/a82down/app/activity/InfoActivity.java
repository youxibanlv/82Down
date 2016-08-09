package com.a82down.app.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.base.BaseActivity;
import com.a82down.app.http.UrlConfig;
import com.a82down.app.http.entity.Info;
import com.a82down.app.utils.Constance;
import com.a82down.app.utils.VerifyUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.net.URL;

/**
 * Created by jacky on 16/4/6.
 */
@ContentView(R.layout.activity_info)
public class InfoActivity extends BaseActivity {

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.info_content)
    private TextView info_content;

    private Info info;

    private Html.ImageGetter imageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            try{
                if (!VerifyUtils.isUrl(source)){
                    source = UrlConfig.BASE_URL + source;
                }
                URL url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(),"");
                drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            }catch (Exception e){
                e.printStackTrace();
            }
            return drawable;
        }
    };


    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        info = (Info) intent.getExtras().getSerializable(Constance.INFO);
        struct();
        info_content.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void onResume() {
        super.onResume();
        if (info!= null){
            tv_title.setText(info.getInfo_title());
            Spanned text = Html.fromHtml(info.getInfo_body(),imageGetter,null);
            info_content.setText(text);
        }
    }
    public static void struct() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // or
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
    }

}
