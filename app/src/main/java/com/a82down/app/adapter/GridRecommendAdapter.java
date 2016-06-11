package com.a82down.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a82down.app.R;
import com.a82down.app.db.table.App;
import com.a82down.app.view.ExtrAppVertical;

import java.util.List;

/**
 * Created by strike on 16/6/10.
 */
public class GridRecommendAdapter extends BaseAdapter {

    private Context context;
    private List<App> list;
    private LayoutInflater inflater;

    public GridRecommendAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<App> appList){
        list = appList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public App getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_recommed_grid,parent,false);
            holder = new ViewHolder();
            holder.grid_recommend = (ExtrAppVertical) convertView.findViewById(R.id.grid_recommend);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //配置app界面
        holder.grid_recommend.setApp(list.get(position));
        return convertView;
    }
    class ViewHolder{
        ExtrAppVertical grid_recommend;
    }
}
