package com.a82down.app.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a82down.app.utils.UiUtils;

import org.xutils.x;

/**
 * Created by strike on 16/6/5.
 */
public class BaseFragment extends Fragment {
    private boolean injected = false;

    private String title;

    private int iconId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    public void showTipToast(boolean isSuccess, String msg) {
        UiUtils.showTipToast(true,msg);
    }

    public void freshView() {

    }

}
