package com.a82down.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.db.table.App;
import com.a82down.app.http.UrlConfig;
import com.a82down.app.view.LoadMoreRecyclerView;

import org.xutils.x;

import java.util.List;

/**
 * Created by strike on 16/6/7.
 */
public class RecommendAdapter extends RecyclerView.Adapter {

    private List<App> appList = null;
    private boolean mIsStagger;

    public RecommendAdapter(List<App> list) {
        appList = list;
    }

    public void switchMode(boolean mIsStagger) {
        this.mIsStagger = mIsStagger;
    }

    public void setData(List<App> apps){
        appList = apps;
    }

    public void addDatas(List<App> apps){
        appList.addAll(apps);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LoadMoreRecyclerView.TYPE_STAGGER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_grid,parent,false);
            return new StaggerViewHolder(view);
        }else {
            return null;
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mIsStagger){
            StaggerViewHolder staggerViewHolder = (StaggerViewHolder) holder;
            x.image().bind(staggerViewHolder.iconView, UrlConfig.BASE_URL+appList.get(position).getApp_logo());
            staggerViewHolder.mContentView.setText(appList.get(position).getApp_title());
        }
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }


    public class StaggerViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public ImageView iconView;
        public TextView mContentView;

        public StaggerViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            iconView = (ImageView) itemView.findViewById(R.id.icon);
            mContentView = (TextView) itemView.findViewById(R.id.content);
        }
    }

}
