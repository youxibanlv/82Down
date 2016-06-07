package com.a82down.app.fragment;

import com.a82down.app.R;
import com.a82down.app.base.BaseFragment;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.MyCallBack;
import com.a82down.app.http.request.RecommendReq;
import com.a82down.app.http.response.RecommendRsp;

import org.xutils.view.annotation.ContentView;

/**
 * Created by strike on 16/6/5.
 */
@ContentView(R.layout.fragment_recommend)
public class RecommendFragment extends BaseFragment {

    @Override
    public void onResume() {
        super.onResume();
        getRecommend();
    }

    private void getRecommend(){
        RecommendReq req = new RecommendReq("0","6","0");
        req.sendRequest(new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                RecommendRsp rsp = (RecommendRsp) BaseResponse.getRsp(result,RecommendRsp.class);
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
