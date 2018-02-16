package com.kusofan.demo.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.kusofan.demo.R;
import com.kusofan.demo.widget.LoadingProgressDialog;

import butterknife.BindColor;

/**
 * Created by xujian on 16/2/3.
 */
public class MyBaseActivity extends BaseActivity {

    public static final String ICON_FLAG = "&#xe612;";
    public static final String ICON_BACK = "&#xe614;";
    public static final String ICON_CLOSE = "&#xe60e;";
    public static final String ICON_MORE = "&#xe60d;";
    public static final String ICON_SHARE = "&#xe60f;";
    public static final String ICON_SHARE_2 = "&#xe606;";
    public static final String ICON_PLANE = "&#xe610;";
    public static final String ICON_CANLENDAR = "&#xe600;";
    public static final String ICON_OK = "&#xe607;";
    public static final String ICON_OK_2 = "&#xe602;";
    public static final String ICON_OK_3 = "&#xe611;";
    public static final String ICON_OK_4 = "&#xe60a;";
    public static final String ICON_FAIL = "&#xe608;";
    public static final String ICON_FAIL_2 = "&#xe604;";
    public static final String ICON_TOP = "&#xe609;";
    public static final String ICON_ADD = "&#xe617;";
    public static final String ICON_REMOVE = "&#xe618;";
    public static final String ICON_END = "&#xe613;";
    public static final String ICON_BELL = "&#xe60b;";
    public static final String ICON_BELL_2 = "&#xe603;";
    public static final String ICON_INFO = "&#xe605;";
    public static final String ICON_BENING = "&#xe60c;";
    public static final String ICON_GONE = "";

    @BindColor(R.color.common_bg_white)
    int title_bg;
    @BindColor(R.color.common_text)
    int title_text;

//    protected ActionsCreator mAppActionCreator;
//    protected AppStore mAppStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mAppStore = AppStore.getInstance();
//        mAppActionCreator = ActionsCreator.getInstance();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    private void init() {
        rl_title_bar.setBackgroundColor(title_bg);
        setAllTextColor(title_text);
        setLeftIcon(ICON_BACK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected Dialog getLoadingDialog() {
        return new LoadingProgressDialog(this);
    }


    @Override
    protected int getCustomRootLayoutId() {
        return R.layout.base_activity_layout;
    }


    protected <T> void registerBus(T o) {
        MyBaseApplication.sDispatcher.register(o);
    }

    protected <T> void unregisterBus(T o) {
        MyBaseApplication.sDispatcher.unregister(o);
    }
}
