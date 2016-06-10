package com.a82down.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.db.table.App;
import com.a82down.app.http.UrlConfig;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by strike on 16/6/10.
 */
public class ExtrAppVertical extends LinearLayout {
    @ViewInject(R.id.app_icon)
    private ImageView app_icon;

    @ViewInject(R.id.app_title)
    private TextView app_title;

    @ViewInject(R.id.app_score)
    private RatingBar app_score;

    @ViewInject(R.id.app_size)
    private TextView app_size;

    @ViewInject(R.id.app_install)
    private TextView app_install;

    private View view;

    public ExtrAppVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.app_v, this, true);
        x.view().inject(view);
    }

    public void setApp(App app){
       if (app_icon != null){
           x.image().bind(app_icon, UrlConfig.BASE_URL+app.getApp_logo());
       }
        if (app_title != null) {
            app_title.setText(app.getApp_title());
        }
        if (app_score != null) {
            app_score.setNumStars(Integer.parseInt(app.getApp_recomment()));
        }
        if (app_size != null) {
            app_size.setText(app.getApp_size());
        }
    }
}
