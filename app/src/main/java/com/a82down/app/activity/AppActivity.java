package com.a82down.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.a82down.app.R;
import com.a82down.app.adapter.KeywordAdapter;
import com.a82down.app.base.BaseActivity;
import com.a82down.app.fragment.AppFragment;
import com.a82down.app.http.BaseResponse;
import com.a82down.app.http.Constance;
import com.a82down.app.http.NormalCallBack;
import com.a82down.app.http.entity.Keyword;
import com.a82down.app.http.request.KeywordsReq;
import com.a82down.app.http.response.KeywordsRsp;
import com.a82down.app.utils.UiUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by strike on 16/6/14.
 */
@ContentView(R.layout.activity_app)
public class AppActivity extends BaseActivity {

    @ViewInject(R.id.edt_search)
    private EditText edt_search;

    @ViewInject(R.id.fl_content)
    private FrameLayout fl_content;

    private AppFragment fragment;
    private int keySize = 5;
    private PopupWindow popupWindow;
    private KeywordAdapter adapter;

    private List<Keyword> keywords;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle!= null){
            String keyword = bundle.getString(getString(R.string.keyword));
            if (keyword!= null){
                edt_search.setText(keyword);
            }
            fragment = new AppFragment();
            fragment.setArguments(bundle);
        }
        edt_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showKeywords(edt_search.getText().toString());
                } else {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            }
        });
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                showKeywords(s.toString());
                edt_search.setSelection(s.toString().length());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFragment(R.id.fl_content,fragment);
    }

    @Event(value = {R.id.rv_back,R.id.btn_search,R.id.iv_manager})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.rv_back:
                finish();
                break;
            case R.id.btn_search:
                String keyword = edt_search.getText().toString();
                if (popupWindow!= null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                fragment.refreshKey(keyword);
                UiUtils.closeKeybord(edt_search,this);
                break;
            case R.id.iv_manager:

                break;
        }
    }
    //获取热搜词列表
    private void showKeywords(String keyword) {
        KeywordsReq req = new KeywordsReq(keyword, keySize);
        req.sendRequest(new NormalCallBack() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    KeywordsRsp rsp = (KeywordsRsp) BaseResponse.getRsp(result, KeywordsRsp.class);
                    if (rsp != null && rsp.result == Constance.HTTP_SUCCESS) {
                        keywords = rsp.getKeywords();
                        showPopuWindow();
                    }
                }
            }

            @Override
            public void onFinished() {

            }
        });
    }
    //显示关键词列表
    private void showPopuWindow() {
        if (popupWindow == null) {
            int width = edt_search.getWidth();
            View viewContent = LayoutInflater.from(context).inflate(R.layout.pop_keyword, null);
            popupWindow = new PopupWindow(viewContent, width, LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setContentView(viewContent);

            ListView listView = (ListView) viewContent.findViewById(R.id.lv_key);
            adapter = new KeywordAdapter(context, keywords);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Keyword keyword = keywords.get(position);
                    if (keyword != null && keyword.getQ() != null) {
                        edt_search.setText(keyword.getQ());
                        fragment.refreshKey(keyword.getQ());
                    }
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            });
        }
        popupWindow.showAsDropDown(edt_search);
        if (keywords == null || keywords.size() == 0) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        }
        adapter.refresh(keywords);
    }
}
