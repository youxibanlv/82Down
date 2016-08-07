package com.a82down.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a82down.app.R;
import com.a82down.app.adapter.SubjectAdapter;
import com.a82down.app.base.BaseFragment;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.HttpConstance;
import com.a82down.app.http.NormalCallBack;
import com.a82down.app.http.entity.Subject;
import com.a82down.app.http.request.SubjectReq;
import com.a82down.app.http.response.SubjectRsp;
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
public class SubjectFragment extends BaseFragment {

    private View view;

    @ViewInject(R.id.pull_to_refresh)
    private PullToRefreshListView pull_to_refresh;

    private SubjectAdapter subjectAdapter;

    private int pageNo = 0,pageSize = 4,totalPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        subjectAdapter = new SubjectAdapter(getActivity());
        pull_to_refresh.setMode(PullToRefreshBase.Mode.BOTH);
        PullToRefreshUtils.initRefresh(pull_to_refresh);
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getSubject(true);
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
                getSubject(false);
            }
        });
        getSubject(true);
        return view;
    }

    private void getSubject(final boolean isRefresh) {
        SubjectReq req = new SubjectReq(pageNo, pageSize);
        showProgressDialogCloseDelay(getString(R.string.loading), HttpConstance.DEFAULT_TIMEOUT);
        req.sendRequest(new NormalCallBack() {
            @Override
            public void onSuccess(String result) {
                SubjectRsp rsp = (SubjectRsp) BaseResponse.getRsp(result, SubjectRsp.class);
                totalPage = rsp.getTotalPage();
                List<Subject> list = rsp.getSubjects();
                pull_to_refresh.setAdapter(subjectAdapter);
                if (isRefresh) {
                    subjectAdapter.refresh(list);
                } else {
                    subjectAdapter.addData(list);
                }

            }

            @Override
            public void onFinished() {
                pull_to_refresh.onRefreshComplete();
                dismissProgressDialog();
            }
        });
    }
}
