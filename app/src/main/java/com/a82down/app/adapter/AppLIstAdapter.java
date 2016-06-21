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
import com.a82down.app.utils.DownLoadUtils;
import com.a82down.app.view.DownloadBtn;

import org.xutils.view.annotation.ViewInject;
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
            holder = new AppListViewHolder(convertView);
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
        new DownLoadUtils(context).initDownLoad(app,holder.tv_down);
        return convertView;
    }

    class AppListViewHolder {
        @ViewInject(R.id.iv_app_icon)
        ImageView iv_app_icon;

        @ViewInject(R.id.tv_app_title)
        TextView tv_app_title;

        @ViewInject(R.id.app_score)
        RatingBar app_score;

        @ViewInject(R.id.tv_des)
        TextView tv_des;

        @ViewInject(R.id.tv_down)
        DownloadBtn tv_down;

        @ViewInject(R.id.tv_down_num)
        TextView tv_down_num;

        public AppListViewHolder(View view){
            x.view().inject(this,view);
        }
    }
}
