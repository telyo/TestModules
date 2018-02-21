package com.telyo.doublerdatepick.DoubleDatePicker.View.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.telyo.doublerdatepick.R;

/**
 * Created by yijunchao on 2018/2/14.
 */

public class CommDialog extends Dialog {

    public CommDialog(@NonNull Context context, int layoutId,int gravity) {
        this(context, R.style.custom_dialog, layoutId, R.anim.enter_anim, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,gravity);
    }

    public CommDialog(@NonNull Context context, int themeResId, int layoutId, int animId, int width, int height,int gravity) {
        super(context, themeResId);
        Window window = getWindow();
        setContentView(layoutId);
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = height;
        params.width = width;
        params.gravity = gravity;
        window.setAttributes(params);
        window.setWindowAnimations(animId);
    }
}
