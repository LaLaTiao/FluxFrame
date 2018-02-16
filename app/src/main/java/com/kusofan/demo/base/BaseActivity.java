package com.kusofan.demo.base;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kusofan.demo.R;
import com.kusofan.demo.base.net.RequestStateListener;
import com.kusofan.demo.base.net.SimpleCall;
import com.kusofan.demo.base.net.SimpleCallBack;
import com.kusofan.demo.utils.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;



/**
 * Created by heming on 2017/12/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Dialog mLoadingDialog;
    private static List<SimpleCall> sCancelableCallList = new ArrayList();
    private static List<SimpleCall> sUncancelableCallList = new ArrayList();
    private int mCustomRootLayout = -1;
    private View mRoot;
    protected FrameLayout fl_container;
    protected RelativeLayout rl_root;
    protected RelativeLayout rl_title_bar;
    protected RelativeLayout view_common_error;
    protected TextView tv_title;
    protected TextView tv_left_text;
    protected TextView tv_left_icon;
    protected TextView tv_right_icon;
    protected TextView tv_right_text;
    protected TextView tv_error;
    protected View view_line_bar;
    private Typeface tf;

    public BaseActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initInternal();
    }

    public void setContentView(int layoutResID) {
        this.mRoot = LayoutInflater.from(this).inflate(this.mCustomRootLayout == -1 ? R.layout.base_activity_layout : this.mCustomRootLayout, (ViewGroup) null);
        super.setContentView(this.mRoot);
        this.initView();
        this.addViewToContent(layoutResID);
        ButterKnife.bind(this);
    }

    public void setContentView(View view) {
        this.mRoot = LayoutInflater.from(this).inflate(this.mCustomRootLayout == -1 ? R.layout.base_activity_layout : this.mCustomRootLayout, (ViewGroup) null);
        super.setContentView(this.mRoot);
        this.initView();
        this.addViewToContent(view);
        ButterKnife.bind(this);
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
        this.mRoot = LayoutInflater.from(this).inflate(this.mCustomRootLayout == -1 ? R.layout.base_activity_layout : this.mCustomRootLayout, (ViewGroup) null);
        super.setContentView(this.mRoot);
        this.initView();
        this.addViewToContent(view, params);
        ButterKnife.bind(this);
    }

    private void initView() {
        this.rl_root = (RelativeLayout) this.mRoot.findViewWithTag("rl_root");
        this.rl_title_bar = (RelativeLayout) this.mRoot.findViewWithTag("rl_title_bar");
        this.view_line_bar = this.mRoot.findViewWithTag("view_line_bar");
        this.fl_container = (FrameLayout) this.mRoot.findViewWithTag("fl_container");
        this.tv_title = (TextView) this.mRoot.findViewWithTag("tv_title");
        this.tv_left_icon = (TextView) this.mRoot.findViewWithTag("tv_left_icon");
        this.tv_right_icon = (TextView) this.mRoot.findViewWithTag("tv_right_icon");
        this.tv_left_text = (TextView) this.mRoot.findViewWithTag("tv_left_text");
        this.tv_right_text = (TextView) this.mRoot.findViewWithTag("tv_right_text");
        this.tf = Typeface.createFromAsset(this.getAssets(), "iconfont.ttf");
        this.tv_left_icon.setTypeface(this.tf);
        this.tv_right_icon.setTypeface(this.tf);
        this.view_common_error = (RelativeLayout) this.mRoot.findViewWithTag("view_common_error");
        this.tv_error = (TextView) this.mRoot.findViewWithTag("tv_error");
        this.view_common_error.setVisibility(View.GONE);
        this.setTitleBarDefault();
    }

    protected void showError(String error) {
        this.fl_container.setVisibility(View.GONE);
        this.view_common_error.setVisibility(View.VISIBLE);
        this.tv_error.setText(error);
    }

    protected void hideError() {
        this.fl_container.setVisibility(View.VISIBLE);
        this.view_common_error.setVisibility(View.GONE);
    }

    private void addViewToContent(View view, ViewGroup.LayoutParams params) {
        this.fl_container.addView(view, params);
    }

    private void addViewToContent(int layoutResID) {
        this.addViewToContent(LayoutInflater.from(this).inflate(layoutResID, (ViewGroup) null));
    }

    private void addViewToContent(View view) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
        this.addViewToContent(view, params);
    }

    protected void setTitleString(String title) {
        this.tv_title.setText(title);
    }

    protected void setTitleId(int id) {
        this.setTitleString(this.getString(id));
    }

    private Spanned getHtmlText(String which) {
        return Html.fromHtml(which);
    }

    protected void setAllTextColor(int color) {
        this.tv_title.setTextColor(color);
        this.tv_left_text.setTextColor(color);
        this.tv_left_icon.setTextColor(color);
        this.tv_right_text.setTextColor(color);
        this.tv_right_icon.setTextColor(color);
    }

    protected TextView setLeftText(String text) {
        this.tv_left_text.setText(text);
        this.tv_left_text.setVisibility(StringUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        return this.tv_left_text;
    }

    protected TextView setRightText(String text) {
        this.tv_right_text.setText(text);
        this.tv_right_text.setVisibility(StringUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        return this.tv_right_text;
    }

    protected TextView setLeftIcon(String which) {
        this.tv_left_icon.setText(this.getHtmlText(which));
        this.tv_left_icon.setVisibility(StringUtils.isEmpty(which) ? View.GONE : View.VISIBLE);
        return this.tv_left_icon;
    }

    protected TextView setRightIcon(String which) {
        this.tv_right_icon.setText(this.getHtmlText(which));
        this.tv_right_icon.setVisibility(StringUtils.isEmpty(which) ? View.GONE : View.VISIBLE);
        return this.tv_right_icon;
    }

    protected void setLeftDefault() {
        this.setLeftIcon("").setOnClickListener((View.OnClickListener) null);
        this.setLeftText("").setOnClickListener((View.OnClickListener) null);
    }

    protected void setRightDefault() {
        this.setRightIcon("").setOnClickListener((View.OnClickListener) null);
        this.setRightText("").setOnClickListener((View.OnClickListener) null);
    }

    protected void setTitleBarDefault() {
        this.setLeftDefault();
        this.setRightDefault();
        this.showTitleBar();
    }

    protected void showTitleBar() {
        this.rl_title_bar.setVisibility(View.VISIBLE);
        this.view_line_bar.setVisibility(View.VISIBLE);
    }

    protected void hideTitleBar() {
        this.rl_title_bar.setVisibility(View.GONE);
        this.view_line_bar.setVisibility(View.GONE);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return this.mLoadingDialog.isShowing() && View.INVISIBLE == keyCode ? true : super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mLoadingDialog.isShowing() && View.INVISIBLE == keyCode) {
            this.cancelRequest();
            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }


    private void initInternal() {
        this.mCustomRootLayout = this.getCustomRootLayoutId();
        this.mLoadingDialog = this.getLoadingDialog();
        this.mLoadingDialog.setCancelable(true);
    }

    public <R> R getService(Class<R> c) {
        return BaseApplication.sRequestManager.getService(c);
    }

    public <R> R getService(String endPoint, Class<R> c) {
        return BaseApplication.sRequestManager.getService(endPoint, c);
    }

    public void addRequest(SimpleCall call) {
        this.addRequest(call, (SimpleCallBack) null);
    }

    public void addRequest(SimpleCall call, SimpleCallBack callBack) {
        this.addRequest(call, callBack, true, false);
    }

    public void addRequest(final SimpleCall call, SimpleCallBack callBack, final boolean isForeground, final boolean cancelable) {
        BaseApplication.sRequestManager.addRequest(call, callBack, new RequestStateListener() {
            public void onStart() {
                if (isForeground) {
                    if (cancelable) {
                        BaseActivity.sCancelableCallList.add(call);
                    } else {
                        BaseActivity.sUncancelableCallList.add(call);
                    }

                    BaseActivity.this.refreshDialogState();
                }

            }

            public void onFinish() {
                if (BaseActivity.sCancelableCallList.contains(call)) {
                    BaseActivity.sCancelableCallList.remove(call);
                    BaseActivity.this.refreshDialogState();
                } else if (BaseActivity.sUncancelableCallList.contains(call)) {
                    BaseActivity.sUncancelableCallList.remove(call);
                    BaseActivity.this.refreshDialogState();
                }

            }
        });
    }

    public void cancelRequest() {
        Iterator var1 = sCancelableCallList.iterator();

        while (var1.hasNext()) {
            SimpleCall call = (SimpleCall) var1.next();
            call.cancel();
        }

    }

    public void forceCancelRequest() {
        this.cancelRequest();
        Iterator var1 = sUncancelableCallList.iterator();

        while (var1.hasNext()) {
           SimpleCall call = (SimpleCall) var1.next();
            call.cancel();
        }

    }

    private void refreshDialogState() {
        boolean needShow = sCancelableCallList.size() > 0 || sUncancelableCallList.size() > 0;
        if (!this.mLoadingDialog.isShowing() && needShow) {
            this.mLoadingDialog.show();
        } else {
            this.mLoadingDialog.dismiss();
        }

    }

    protected abstract Dialog getLoadingDialog();


    protected abstract int getCustomRootLayoutId();

    public Typeface getTypeface() {
        return this.tf;
    }
}


