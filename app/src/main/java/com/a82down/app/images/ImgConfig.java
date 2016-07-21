package com.a82down.app.images;


import android.widget.ImageView;

import com.a82down.app.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

/**
 * Created by strike on 16/6/16.
 */
public class ImgConfig {
    public static ImageOptions getImgOption(){
        ImageOptions options = new ImageOptions.Builder()
                .setRadius(DensityUtil.dip2px(5))
                .setFailureDrawableId(R.mipmap.load_faild)
                .setLoadingDrawableId(R.mipmap.loading)
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setUseMemCache(true)
                .setAutoRotate(true)
                .build();
        return options;
    }
    public static ImageOptions getImgOptionNoCache(){
        ImageOptions options = new ImageOptions.Builder()
                .setRadius(DensityUtil.dip2px(5))
                .setFailureDrawableId(R.mipmap.load_faild)
                .setLoadingDrawableId(R.mipmap.loading)
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setUseMemCache(false)
                .setAutoRotate(true)
                .build();
        return options;
    }
    public static ImageOptions getMatrixImgOption(){
        ImageOptions options = new ImageOptions.Builder()
                .setRadius(DensityUtil.dip2px(5))
                .setFailureDrawableId(R.mipmap.load_faild)
                .setLoadingDrawableId(R.mipmap.loading)
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.MATRIX)
                .setUseMemCache(true)
                .setAutoRotate(true)
                .build();
        return options;
    }
}
