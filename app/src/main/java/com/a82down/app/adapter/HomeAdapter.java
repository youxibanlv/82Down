package com.a82down.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a82down.app.R;
import com.a82down.app.activity.RecommendActivity;
import com.a82down.app.db.table.App;
import com.a82down.app.http.entity.WheelPage;
import com.a82down.app.view.NoScrollGridView;
import com.a82down.app.view.WheelViewPage;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by strike on 16/6/7.
 */
public class HomeAdapter extends BaseAdapter {

    private final int TYPE_HEAD = 0;//轮播图
    private final int TYPE_RECOMMEND = 1;//精品推荐
    private final int TYPE_LIST = 2;//热门新游

    private List<WheelPage> wheelPages = new ArrayList<>();//轮播图集合
    private List<App> recommends = new ArrayList<>();//精品推荐
    private List<App> newGames = new ArrayList<>();//热门新游

    private GridRecommendAdapter gridAdapter;

    private Context context;
    private LayoutInflater inflater;

    public HomeAdapter(Context context) {
        this.context = context;
        gridAdapter = new GridRecommendAdapter(context);
        inflater = LayoutInflater.from(context);
    }

    //设置轮播图数据
    public void refreshWheelPages(List<WheelPage> list) {
        wheelPages.clear();
        wheelPages = list;
    }

    //    设置精品推荐数据
    public void refreshRecommends(List<App> list) {
        recommends.clear();
        recommends = list;
    }

    @Override
    public int getCount() {
        int count = 2;
        if (newGames.size() > 0) {
            count = count + newGames.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        if (position < 2) {
            return null;
        } else {
            return newGames.get(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
//            return TYPE_RECOMMEND;
        } else if (position == 1) {
            return TYPE_RECOMMEND;
        } else {
            return TYPE_LIST;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        WheelPageHolder wheelPageHolder = null;
        RecommendHolder recommendHolder = null;
        if (convertView == null) {
            switch (type) {
                case TYPE_HEAD:
                    convertView = inflater.inflate(R.layout.item_wheel_page, parent, false);
                    wheelPageHolder = new WheelPageHolder();
                    wheelPageHolder.myWheelPages = (WheelViewPage) convertView.findViewById(R.id.myWheelPages);
                    convertView.setTag(wheelPageHolder);
                    break;
                case TYPE_RECOMMEND:
                    convertView = inflater.inflate(R.layout.item_recommed, parent, false);
                    recommendHolder = new RecommendHolder(convertView);
                    convertView.setTag(recommendHolder);
                    break;
                case TYPE_LIST:

                    break;

            }
        } else {
            switch (type) {
                case TYPE_HEAD:
                    wheelPageHolder = (WheelPageHolder) convertView.getTag();
                    break;
                case TYPE_RECOMMEND:
                    recommendHolder = (RecommendHolder) convertView.getTag();
                    break;
                case TYPE_LIST:

                    break;
            }
        }
        //设置资源
        switch (type) {
            case TYPE_HEAD:
                if (wheelPages.size() > 0) {
                    wheelPageHolder.myWheelPages.setViewPage(wheelPages);
                }
                break;
            case TYPE_RECOMMEND:
                recommendHolder.tv_recommend.setText(context.getResources().getString(R.string.recommend));
                recommendHolder.gv_recommend.setAdapter(gridAdapter);
                if (recommends.size() > 0) {
                    gridAdapter.setList(recommends);
                    gridAdapter.notifyDataSetChanged();
                }
                recommendHolder.tv_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击更多，跳转到app推荐列表界面
                        Intent intent = new Intent(context, RecommendActivity.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case TYPE_LIST:

                break;
        }
        return convertView;
    }

    class WheelPageHolder {
        WheelViewPage myWheelPages;
    }

    class RecommendHolder {
        @ViewInject(R.id.tv_recommend)
        TextView tv_recommend;
        @ViewInject(R.id.tv_more)
        TextView tv_more;
        @ViewInject(R.id.gv_recommend)
        NoScrollGridView gv_recommend;

        public RecommendHolder(View view) {
            x.view().inject(this, view);
        }


    }
}
