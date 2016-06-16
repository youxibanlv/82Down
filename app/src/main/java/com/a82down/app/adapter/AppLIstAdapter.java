package com.a82down.app.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.base.MyBaseAdapter;
import com.a82down.app.db.table.App;
import com.a82down.app.images.ImgConfig;

import org.xutils.x;

/**
 * Created by strike on 16/6/14.
 */
public class AppLIstAdapter extends MyBaseAdapter<App> {

    public AppLIstAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppListViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_app_list, parent, false);
            holder = new AppListViewHolder();
            holder.iv_app_icon = (ImageView) convertView.findViewById(R.id.iv_app_icon);
            holder.tv_app_title = (TextView) convertView.findViewById(R.id.tv_app_title);
            holder.app_score = (RatingBar) convertView.findViewById(R.id.app_score);
            holder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
            holder.tv_down = (TextView) convertView.findViewById(R.id.tv_down);
            holder.tv_down_num = (TextView) convertView.findViewById(R.id.tv_down_num);
            convertView.setTag(holder);
        } else {
            holder = (AppListViewHolder) convertView.getTag();
        }
        App app = list.get(position);
        x.image().bind(holder.iv_app_icon, app.getApp_logo(), ImgConfig.getImgOption());
        holder.tv_app_title.setText(app.getApp_title());
        int score = app.getApp_recomment() == null ? 0 : (int) (Float.parseFloat(app.getApp_recomment()) / 2);
        holder.app_score.setNumStars(score);
        holder.tv_des.setText(Html.fromHtml(app.getApp_desc()));
        String down = app.getApp_down() == null ? "0" : app.getApp_down();
        holder.tv_down_num.setText("下载：" + down);
        return convertView;
    }

    class AppListViewHolder {
        ImageView iv_app_icon;
        TextView tv_app_title;
        RatingBar app_score;
        TextView tv_des;
        //        TextView tv_time;
        TextView tv_down;
        TextView tv_down_num;
    }
}
