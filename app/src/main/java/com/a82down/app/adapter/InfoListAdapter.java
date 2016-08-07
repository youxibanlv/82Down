package com.a82down.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.base.MyBaseAdapter;
import com.a82down.app.http.entity.Info;
import com.a82down.app.images.ImgConfig;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by strike on 16/8/4.
 */
public class InfoListAdapter extends MyBaseAdapter<Info> {
    public InfoListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_info,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Info info = getItem(position);
        x.image().bind(viewHolder.icon,info.getInfo_img(), ImgConfig.getImgOption());
        viewHolder.title.setText(info.getInfo_title());
        viewHolder.body.setText(info.getInfo_body());
        viewHolder.visitors.setText("阅读量："+info.getInfo_visitors());
        return convertView;
    }

    class ViewHolder{

        @ViewInject(R.id.icon)
        ImageView icon;

        @ViewInject(R.id.title)
        TextView title;

        @ViewInject(R.id.body)
        TextView body;

        @ViewInject(R.id.visitors)
        TextView visitors;

        public ViewHolder(View view){
            x.view().inject(this,view);
        }

    }
}
