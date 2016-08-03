package com.a82down.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;

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
 * Created by strike on 16/6/5.
 */
@ContentView(R.layout.fragment_article)
public class ArticleFragment extends BaseFragment {

    @ViewInject(R.id.rg_nav)
    private RadioGroup rg_nav;

    @ViewInject(R.id.pull_to_refresh)
    private PullToRefreshListView pull_to_refresh;

    private int pageNo = 0,pageSize = 6,total = 0;
    private int sNo = 0,sSize = 4,sTotal = 0;

    private SubjectAdapter subjectAdapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater,container,savedInstanceState);
        subjectAdapter = new SubjectAdapter(getActivity());
        pull_to_refresh.setMode(PullToRefreshBase.Mode.BOTH);
        PullToRefreshUtils.initRefresh(pull_to_refresh);
        rg_nav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.subject:
                        getSubject(true);
                        break;
                    case R.id.info:
                        getInfo();
                        break;
                }
            }
        });
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
               int checkId = rg_nav.getCheckedRadioButtonId();
                if (checkId == R.id.subject){//专题
                    sNo = 0;
                    getSubject(true);
                }else{

                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                int checkId = rg_nav.getCheckedRadioButtonId();
                if (checkId == R.id.subject){//专题
                    if (sNo < sTotal){
                        sNo ++;
                    }else{
                        UiUtils.showTipToast(false,getString(R.string.this_is_last));
                        UiUtils.stopRefresh(pull_to_refresh);
                        return;
                    }
                    getSubject(false);
                }else{

                }
            }
        });
        return view;
    }

    private void getInfo() {
    }

    private void getSubject(final boolean isRefresh){
        SubjectReq req = new SubjectReq(sNo,sSize);
        showProgressDialogCloseDelay(getString(R.string.loading), HttpConstance.DEFAULT_TIMEOUT);
        req.sendRequest(new NormalCallBack() {
            @Override
            public void onSuccess(String result) {
                SubjectRsp rsp = (SubjectRsp) BaseResponse.getRsp(result,SubjectRsp.class);
                sTotal = rsp.getTotalPage();
                List<Subject> list = rsp.getSubjects();
                pull_to_refresh.setAdapter(subjectAdapter);
                if (isRefresh){
                    subjectAdapter.refresh(list);
                }else{
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

    @Override
    public void onStart() {
        super.onStart();
        int checkId = rg_nav.getCheckedRadioButtonId();
        if (checkId == -1){
            rg_nav.check(R.id.subject);
        }
    }
}
