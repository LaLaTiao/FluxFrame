package com.kusofan.demo.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.kusofan.demo.R;


public class LoadingProgressDialog extends Dialog {

    //    private AnimationDrawable animationDrawable;
//    private ImageView iv_loading;
    private ImageView rotate;
    private Activity context;

    public LoadingProgressDialog(Context context) {
        this(context, R.style.loading_dialog);
        this.context = (Activity) context;
    }

    public LoadingProgressDialog(Context context, int style) {
        super(context, style);
        this.context = (Activity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
//        iv_loading = (ImageView) findViewById(R.id.iv_loading);
        rotate = (ImageView) findViewById(R.id.iv_loading_rotate);

    }

    @Override
    public void show() {
        if (context.isFinishing()) {
            return;
        }
        if (!this.isShowing()) {
            super.show();
            showProgress();
        }
    }

    @Override
    public void dismiss() {
        if (this.isShowing()) {
            super.dismiss();
        }
    }

    public void showProgress() {
//        animationDrawable = (AnimationDrawable) iv_loading.getDrawable();
//        animationDrawable.start();

        Animation anim = AnimationUtils.loadAnimation(context, R.anim.base_loading_rotate);
        anim.setFillAfter(true);
        LinearInterpolator lir = new LinearInterpolator();
        anim.setInterpolator(lir);
        rotate.startAnimation(anim);


    }
}
