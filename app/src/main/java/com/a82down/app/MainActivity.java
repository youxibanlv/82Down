package com.a82down.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.a82down.app.base.BaseActivity;
import com.a82down.app.base.BaseFragment;
import com.a82down.app.fragment.AppFragment;
import com.a82down.app.fragment.ArticleFragment;
import com.a82down.app.fragment.GameFragment;
import com.a82down.app.fragment.HomeFragment;
import com.a82down.app.utils.UiUtils;
import com.a82down.app.view.IconTabPageIndicator;
import com.a82down.app.view.TabAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    /**
     * The instance.
     */
    public static MainActivity instance;

    /**
     * The Constant TAG.
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    @ViewInject(R.id.indicator)
    private IconTabPageIndicator mIndicator;

    @ViewInject(R.id.view_pager)
    private ViewPager mViewPager;


    private long mExitTime;

    private TabAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

        List<BaseFragment> fragments = initFragments();
        mAdapter = new TabAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(fragments.size());//三页都进行预加载，避免每次都多次切换进行重新创建
        mIndicator.setViewPager(mViewPager);
    }

    public void setCurrentFragment(int position) {
        mViewPager.setCurrentItem(position);
        BaseFragment baseFragment = (BaseFragment) mAdapter.instantiateItem(mViewPager, position);
        baseFragment.freshView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mViewPager != null) {
            int curItem = mViewPager.getCurrentItem();
            if (mAdapter != null && mAdapter.getCount() > curItem) {
                BaseFragment baseFragment = (BaseFragment) mAdapter.instantiateItem(mViewPager, curItem);
                baseFragment.freshView();
            }
        }
    }

    private List<BaseFragment> initFragments() {
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();
        BaseFragment recommend = new HomeFragment();
        recommend.setTitle("推荐");
        recommend.setIconId(R.drawable.recommend_icon_selector);
        fragments.add(recommend);

        BaseFragment game = new GameFragment();
        game.setTitle("游戏");
        game.setIconId(R.drawable.recommend_icon_selector);
        fragments.add(game);

        BaseFragment app = new AppFragment();
        app.setTitle("应用");
        app.setIconId(R.drawable.recommend_icon_selector);
        fragments.add(app);

        BaseFragment article = new ArticleFragment();
        article.setTitle("软文");
        article.setIconId(R.drawable.recommend_icon_selector);
        fragments.add(article);

        return fragments;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                UiUtils.showTipToast(true,getString(R.string.press_to_exit));
                mExitTime = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
