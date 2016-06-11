package com.a82down.app.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a82down.app.R;
import com.a82down.app.adapter.HomeAdapter;
import com.a82down.app.base.BaseFragment;
import com.a82down.app.db.table.App;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.Constance;
import com.a82down.app.http.MyCallBack;
import com.a82down.app.http.entity.WheelPage;
import com.a82down.app.http.request.RecommendReq;
import com.a82down.app.http.request.WheelPageReq;
import com.a82down.app.http.response.RecommendRsp;
import com.a82down.app.http.response.WheelPageRsp;
import com.a82down.app.utils.PullToRefreshUtils;
import com.a82down.app.view.library.PullToRefreshBase;
import com.a82down.app.view.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by strike on 16/6/5.
 */
@ContentView(R.layout.fragment_recommend)
public class HomeFragment extends BaseFragment {

    @ViewInject(R.id.pull_to_refresh)
    private PullToRefreshListView pull_to_refresh;

    private HomeAdapter adapter;

    private int pageNo = 0,pageSize = 3,totalPage;//热门游戏部分
    private int recommedPageNo = 0,recommendPageSize = 3;//精品推荐显示数目
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        adapter = new HomeAdapter(getActivity());
        pull_to_refresh.setAdapter(adapter);
        pull_to_refresh.setMode(PullToRefreshBase.Mode.BOTH);
        //初始化提示文字
        PullToRefreshUtils.initRefresh(pull_to_refresh);
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getWheelPage();
                getRecommend(recommedPageNo,recommendPageSize);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        //加载轮播图片
        getWheelPage();
        getRecommend(recommedPageNo,recommendPageSize);
        return view;
    }

    private void getWheelPage(){
        WheelPageReq req = new WheelPageReq();
        req.sendRequest(new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    WheelPageRsp rsp = (WheelPageRsp) BaseResponse.getRsp(result,WheelPageRsp.class);
                    if (rsp!= null && rsp.result == Constance.HTTP_SUCCESS){
                        List<WheelPage> list = rsp.getWheelPages();
                        if (list!= null && list.size()>0){
                           adapter.setWheelPages(list);
                        }
                    }
                }
            }
            @Override
            public void onFinished() {
                pull_to_refresh.onRefreshComplete();
            }
        });
    }
    //加载精品推荐
    private void getRecommend(final int pageNo, int pageSize){
        RecommendReq req = new RecommendReq(String.valueOf(pageNo),String.valueOf(pageSize),"0");
        req.sendRequest(new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                RecommendRsp rsp = (RecommendRsp) BaseResponse.getRsp(result,RecommendRsp.class);
                if (rsp.result == Constance.HTTP_SUCCESS){
                    List<App> list = rsp.getAppList();
                    if (list!= null && list.size()>0){
                        adapter.setRecommends(list);
                    }
                }
            }

            @Override
            public void onFinished() {
                pull_to_refresh.onRefreshComplete();
            }
        });
    }
}
