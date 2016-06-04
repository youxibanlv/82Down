package com.a82down.app.base;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

/**
 * Created by xiaowuyue on 16/5/31.
 */
public class BaseActivity extends AppCompatActivity {

    protected BaseActivity context;

    protected ProgressDialog progressDialog;

    protected Handler dialogHandler = new Handler();

    protected CloseProgressRunnable closeProgressRunnable = new CloseProgressRunnable();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        x.view().inject(this);
        context = this;
    }

    private void showProgressDialogCloseDelay(String msg,long time){
        dialogHandler.removeCallbacks(closeProgressRunnable);
        if (progressDialog == null){
            progressDialog = ProgressDialog.show(this,"",msg);
            progressDialog.setCanceledOnTouchOutside(false);
        }else{
            progressDialog.setMessage(msg);
            progressDialog.show();
        }
        if (dialogHandler!= null){
            dialogHandler.postDelayed(closeProgressRunnable,time);
        }

    }

    public class CloseProgressRunnable implements Runnable {
        @Override
        public void run() {
            if (null != progressDialog && progressDialog.isShowing()) {
                try {
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
