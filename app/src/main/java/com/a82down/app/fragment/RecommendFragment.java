package com.a82down.app.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a82down.app.R;
import com.a82down.app.adapter.RecommendAdapter;
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
import com.a82down.app.view.LoadMoreRecyclerView;
import com.a82down.app.view.WheelViewPage;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by strike on 16/6/5.
 */
@ContentView(R.layout.fragment_recommend)
public class RecommendFragment extends BaseFragment {

    @ViewInject(R.id.swipeRefresh)
    private SwipeRefreshLayout swipeRefresh;

    @ViewInject(R.id.rv_recommend)
    private LoadMoreRecyclerView rv_recommend;

    @ViewInject(R.id.myWheelPages)
    private WheelViewPage myWheelPages;

    private RecommendAdapter adapter;

    private List<App> appList = new ArrayList<>();

    private int pageNo = 0,pageSize = 120,totalPage;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 0;
//                getRecommend(pageNo,pageSize,false);
                getWheelPage();
            }
        });
        rv_recommend.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adapter = new RecommendAdapter(appList);
        adapter.switchMode(true);
        rv_recommend.setAdapter(adapter);
        rv_recommend.setAutoLoadMoreEnable(true);
        rv_recommend.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (pageNo ++ > totalPage){
                    pageNo = totalPage;
                }
                getRecommend(pageNo,pageSize,true);
            }
        });
        adapter.notifyDataSetChanged();
//        getRecommend(pageNo,pageSize,false);
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
                            myWheelPages.setViewPage(list);
                        }
                    }
                }
            }

            @Override
            public void onFinished() {
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void getRecommend(final int pageNo, int pageSize, final boolean isLoadMore){
        RecommendReq req = new RecommendReq(String.valueOf(pageNo),String.valueOf(pageSize),"0");
        req.sendRequest(new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                RecommendRsp rsp = (RecommendRsp) BaseResponse.getRsp(result,RecommendRsp.class);
                if (rsp.result == Constance.HTTP_SUCCESS){
                    appList = rsp.getAppList();
                    totalPage = rsp.getTotalPage();
                    if (appList != null && appList.size()>0){
                        if (isLoadMore){
                            //上拉加载
                            adapter.addDatas(appList);
                            rv_recommend.notifyMoreFinish(pageNo< totalPage?true:false);
                            adapter.notifyDataSetChanged();
                        }else{
                            //下拉刷新
                            rv_recommend.setHasFixedSize(true);
                            swipeRefresh.setRefreshing(false);
                            adapter.setData(appList);
                            adapter.notifyDataSetChanged();
                        }

//                        AppDao.saveAppList(appList);

                    }
                }
            }

            @Override
            public void onFinished() {
                swipeRefresh.setRefreshing(false);
            }
        });
    }
}
