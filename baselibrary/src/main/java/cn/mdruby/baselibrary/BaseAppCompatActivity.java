package cn.mdruby.baselibrary;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Activity的基类
 * Created by 冯超 on 2017/1/10.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements ViewListener{
    protected static final String TAG = "BaseAppCompatActivity";
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        initBeforeDatas();
        if (getLayoutId()!=-1){
            setContentView(getLayoutId());
        }
        initViews();
        preventOverlap(savedInstanceState);
        setListeners();
    }
    protected void preventOverlap(Bundle savedInstanceState) {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void come_back(View view){
        onBackPressed();
    }
}
