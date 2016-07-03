package com.a82down.app.fragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.a82down.app.R;
import com.a82down.app.adapter.AppLIstAdapter;
import com.a82down.app.adapter.CategoryAdapter;
import com.a82down.app.base.BaseFragment;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.HttpConstance;
import com.a82down.app.http.NormalCallBack;
import com.a82down.app.http.entity.Category;
import com.a82down.app.http.request.GetCategoryReq;
import com.a82down.app.http.response.GetCategoryRsp;
import com.a82down.app.utils.PullToRefreshUtils;
import com.a82down.app.view.library.PullToRefreshBase;
import com.a82down.app.view.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by strike on 16/6/5.
 */
@ContentView(R.layout.frament_game)
public class GameFragment extends BaseFragment {

    private final int type_hot = 0;
    private final int type_new = 1;

    private final int game = 2;

    @ViewInject(R.id.rg_nav)
    private RadioGroup rg_nav;

    @ViewInject(R.id.pull_to_refresh)
    private PullToRefreshListView pull_to_refresh;

    @ViewInject(R.id.app_class)
    private RadioButton app_class;

    private AppLIstAdapter adapter;
    private int pageNo = 0,pageSize = 5,total;

    private PopupWindow popupWindow;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList = new ArrayList<>();
    private View view;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        context = getActivity();
        rg_nav.check(R.id.app_hot);
        pull_to_refresh.setMode(PullToRefreshBase.Mode.BOTH);
        PullToRefreshUtils.initRefresh(pull_to_refresh);
        adapter = new AppLIstAdapter(getContext());
        pull_to_refresh.setAdapter(adapter);
        rg_nav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.app_class:
                        if (categoryList!= null && categoryList.size()>0 && popupWindow != null){
                            popupWindow.showAsDropDown(app_class);
                        } else {
                            getGameClass();
                        }
                        break;
                    case R.id.app_hot:

                        break;
                    case R.id.app_new:

                        break;
                    default:
                        break;
                }
            }
        });
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        app_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryList!= null && categoryList.size()>0 && popupWindow != null){
                    popupWindow.showAsDropDown(app_class);
                }else{
                    getGameClass();
                }
            }
        });
        return view;
    }

    private void getContent(int serachType,boolean isRefresh,int pageNo,int pageSize){

    }

    private void getGameClass(){
        GetCategoryReq req = new GetCategoryReq(game);
        req.sendRequest(new NormalCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    GetCategoryRsp rsp = (GetCategoryRsp) BaseResponse.getRsp(result,GetCategoryRsp.class);
                    if (rsp != null && rsp.result == HttpConstance.HTTP_SUCCESS){
                        categoryList= rsp.getResultList();
                        if (categoryList.size()>0){
                            showPopuWindow(categoryList);
                        }
                    }
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }

    //显示关键词列表
    private void showPopuWindow(final List<Category> categoryList) {
        if (popupWindow == null) {
            int width = app_class.getWidth();
            View viewContent = LayoutInflater.from(getContext()).inflate(R.layout.pop_keyword, null);
            popupWindow = new PopupWindow(viewContent, width, LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setContentView(viewContent);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            ListView listView = (ListView) viewContent.findViewById(R.id.lv_key);
            categoryAdapter = new CategoryAdapter(getContext(), categoryList);
            listView.setAdapter(categoryAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (int i=0;i<categoryList.size();i++){
                       if (i == position){
                           categoryList.get(i).setChecked(true);
                       }else {
                           categoryList.get(i).setChecked(false);
                       }
                    }
                    categoryAdapter.notifyDataSetChanged();
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
        }
        popupWindow.showAsDropDown(app_class);

    }
}
