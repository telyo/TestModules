package com.telyo.doublerdatepick.DoubleDatePicker.View;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.telyo.doublerdatepick.R;

/**
 * Author Administrator
 * date: 2018/2/6.
 * describe:
 */

public class PositionControlDialog extends Dialog {
   
    public PositionControlDialog(@NonNull Context context, int themeResId, int animId, int layoutId, int x, int y) {
        super(context, themeResId);
        Window window = getWindow();
        setContentView(layoutId);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.x = x;
        params.y = y;
        window.setAttributes(params);
        window.setWindowAnimations(animId);
    }

    public PositionControlDialog( Context context, int layoutId, int x, int y){
        this(context, R.style.custom_dialog,R.style.custom_dialog_anim,layoutId,x,y);
    }

}
