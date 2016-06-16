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
import com.a82down.app.http.NormalCallBack;
import com.a82down.app.http.request.GetAppByKeywordReq;
import com.a82down.app.http.response.GetAppRsp;
import com.a82down.app.utils.PullToRefreshUtils;
import com.a82down.app.utils.UiUtils;
import com.a82down.app.view.library.PullToRefreshBase;
import com.a82down.app.view.library.PullToRefreshListView;

import org.xutils.common.util.LogUtil;
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
        pull_to_refresh.setMode(PullToRefreshBase.Mode.BOTH);
        PullToRefreshUtils.initRefresh(pull_to_refresh);
        adapter = new AppLIstAdapter(getActivity());
        pull_to_refresh.setAdapter(adapter);
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 0;
                searchAppByKey(true,keyword);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if ( ++pageNo<= total){
                    searchAppByKey(false,keyword);
                }else {
                    UiUtils.showTipToast(false,getString(R.string.this_is_last));
                    UiUtils.stopRefresh(pull_to_refresh);
                }

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            keyword = this.getArguments().getString(getString(R.string.keyword));
        }catch (Exception e){
            e.printStackTrace();
        }
        searchAppByKey(true,keyword);
    }

    public void refreshKey(String key){
        this.keyword = key;
        searchAppByKey(true,key);
    }
    private void searchAppByKey(final boolean isRefresh, String key){
        GetAppByKeywordReq req = new GetAppByKeywordReq(key,pageNo,pageSize);
        req.sendRequest(new NormalCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    GetAppRsp rsp = (GetAppRsp) BaseResponse.getRsp(result,GetAppRsp.class);
                    if (rsp!= null && rsp.result == Constance.HTTP_SUCCESS){
                        if (pageNo == 0){
                            total = rsp.getTotalPage();
                        }
                        List<App> list = rsp.getAppList();
                        if (isRefresh){
                            adapter.refresh(list);
                        }else{
                           adapter.getList().addAll(list);
                            adapter.notifyDataSetChanged();
                        }

                    }
                }
            }

            @Override
            public void onFinished() {
                pull_to_refresh.onRefreshComplete();
                LogUtil.e("pageNo = "+pageNo+",totalPage = "+total);
            }
        });
    }
}
