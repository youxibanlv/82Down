package com.a82down.app.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a82down.app.R;
import com.a82down.app.adapter.AppLIstAdapter;
import com.a82down.app.base.BaseFragment;
import com.a82down.app.db.table.App;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.Constance;
import com.a82down.app.http.MyCallBack;
import com.a82down.app.http.request.GetAppByKeywordReq;
import com.a82down.app.http.response.GetAppByKeywordRsp;
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
public class AppFragment extends BaseFragment {

    @ViewInject(R.id.pull_to_refresh)
    private PullToRefreshListView pull_to_refresh;

    private String keyword;
    private View view;
    private int pageNo = 0,pageSize = 5,total;
    private AppLIstAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater,container,savedInstanceState);
        try {
            keyword = this.getArguments().getString(getString(R.string.keyword));
        }catch (Exception e){
            e.printStackTrace();
        }
        pull_to_refresh.setMode(PullToRefreshBase.Mode.BOTH);
        PullToRefreshUtils.initRefresh(pull_to_refresh);
        adapter = new AppLIstAdapter(getActivity());
        pull_to_refresh.setAdapter(adapter);
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                searchAppByKey(true,keyword);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                searchAppByKey(false,keyword);
            }
        });
        searchAppByKey(true,keyword);
        return view;
    }

    public void refreshKey(String key){
        this.keyword = key;
        searchAppByKey(true,key);
    }
    private void searchAppByKey(boolean isRefresh,String key){
        if (isRefresh){
            pageNo = 0;
        }else {
            pageNo ++;
        }
        GetAppByKeywordReq req = new GetAppByKeywordReq(key,pageNo,pageSize);
        req.sendRequest(new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    GetAppByKeywordRsp rsp = (GetAppByKeywordRsp) BaseResponse.getRsp(result,GetAppByKeywordRsp.class);
                    if (rsp!= null && rsp.result == Constance.HTTP_SUCCESS){
                        List<App> list = rsp.getAppList();
                        adapter.refresh(list);
                    }
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
