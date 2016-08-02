package com.a82down.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a82down.app.R;
import com.a82down.app.base.MyBaseAdapter;
import com.a82down.app.http.entity.Subject;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by strike on 16/8/2.
 */
public class SubjectAdapter extends MyBaseAdapter<Subject> {
    public SubjectAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_subject,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Subject subject = getItem(position);
        x.image().bind( holder.icon,subject.getArea_logo());
        return convertView;
    }

    class ViewHolder{

        @ViewInject(R.id.icon)
        private ImageView icon;


        public ViewHolder(View view){
            x.view().inject(this,view);
        }
    }
}
