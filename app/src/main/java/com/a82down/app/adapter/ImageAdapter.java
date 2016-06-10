package com.a82down.app.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a82down.app.http.UrlConfig;
import com.a82down.app.http.entity.WheelPage;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by strike on 16/6/8.
 */
public class ImageAdapter extends PagerAdapter {

    private List<WheelPage> list;
    private List<ImageView> imageViews;
    private Context context;

    public ImageAdapter(Context context,List<WheelPage> list) {
        this.list = list;
        this.context = context;
        imageViews = new ArrayList<>();
        ImageOptions.Builder builder = new ImageOptions.Builder();
        builder.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageOptions options = builder.build();
        if (list!= null && list.size()>0){
            for (WheelPage wheelPage : list) {
                ImageView imageView = new ImageView(context);
                x.image().bind(imageView, UrlConfig.BASE_URL+wheelPage.getResource_url(),options);
                imageViews.add(imageView);
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView(imageViews.get(position));
    }


}
