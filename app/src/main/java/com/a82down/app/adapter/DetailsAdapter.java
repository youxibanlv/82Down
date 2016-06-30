package com.a82down.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.base.MyBaseAdapter;
import com.a82down.app.images.ImgConfig;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by strike on 16/6/30.
 */
public class DetailsAdapter extends MyBaseAdapter<String> {

    public DetailsAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_imglist,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 0){
            holder.tv_des.setText(list.get(0));
            holder.tv_des.setVisibility(View.VISIBLE);
            holder.iv_img.setVisibility(View.GONE);
        }else{
            String url = list.get(position);
            x.image().bind(holder.iv_img,url, ImgConfig.getImgOption());
            holder.iv_img.setVisibility(View.VISIBLE);
            holder.tv_des.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder{
        @ViewInject(R.id.iv_img)
        ImageView iv_img;
        @ViewInject(R.id.tv_des)
        TextView tv_des;


        public ViewHolder(View view){
            x.view().inject(this,view);
        }
    }
}
