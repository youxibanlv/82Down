package com.a82down.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a82down.app.R;
import com.a82down.app.adapter.InfoListAdapter;
import com.a82down.app.base.BaseFragment;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.HttpConstance;
import com.a82down.app.http.NormalCallBack;
import com.a82down.app.http.entity.Info;
import com.a82down.app.http.request.InfoReq;
import com.a82down.app.http.response.InfoRsp;
import com.a82down.app.utils.Constance;
import com.a82down.app.utils.PullToRefreshUtils;
import com.a82down.app.utils.UiUtils;
import com.a82down.app.view.library.PullToRefreshBase;
import com.a82down.app.view.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by strike on 16/8/7.
 */
@ContentView(R.layout.fragment_list)
public class InfoFragment extends BaseFragment {
    private View view;

    @ViewInject(R.id.pull_to_refresh)
    private PullToRefreshListView pull_to_refresh;

    private InfoListAdapter adapter;

    private int pageNo = 0,pageSize = 4,totalPage = 0;

    private int infoType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        infoType = getArguments().getInt(Constance.INFO_TYPE);
        adapter = new InfoListAdapter(getActivity());
        pull_to_refresh.setMode(PullToRefreshBase.Mode.BOTH);
        PullToRefreshUtils.initRefresh(pull_to_refresh);
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getInfo(true);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (pageNo < totalPage) {
                    pageNo++;
                } else {
                    UiUtils.showTipToast(false, getString(R.string.this_is_last));
                    UiUtils.stopRefresh(pull_to_refresh);
                    return;
                }
                getInfo(false);
            }
        });
        getInfo(true);
        return view;
    }

    private void getInfo(final boolean isRefresh) {
        InfoReq req = new InfoReq(pageNo,pageSize,infoType);
        showProgressDialogCloseDelay(getString(R.string.loading), HttpConstance.DEFAULT_TIMEOUT);
        req.sendRequest(new NormalCallBack() {
            @Override
            public void onSuccess(String result) {
                InfoRsp rsp = (InfoRsp) BaseResponse.getRsp(result,InfoRsp.class);
                List<Info> infoList = rsp.getInfoLists();
                totalPage = rsp.getTotalPage();
                if (infoList != null){
                    pull_to_refresh.setAdapter(adapter);
                    if (isRefresh){
                        adapter.refresh(infoList);
                    }else {
                        adapter.addData(infoList);
                    }

                }
            }

            @Override
            public void onFinished() {
                dismissProgressDialog();
                pull_to_refresh.onRefreshComplete();
            }
        });
    }

}
