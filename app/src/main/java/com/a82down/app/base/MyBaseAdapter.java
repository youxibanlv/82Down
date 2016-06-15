package com.a82down.app.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by strike on 16/6/14.
 */
public abstract class MyBaseAdapter <T> extends BaseAdapter {
    protected List<T> list = new ArrayList<>();
    protected Context context;
    protected LayoutInflater inflater;
    public MyBaseAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public void refresh(List<T> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}