package com.telyo.doublerdatepick.DoubleDatePicker.View;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;

import com.telyo.doublerdatepick.DoubleDatePicker.Utils.DpUtils;
import com.telyo.doublerdatepick.DoubleDatePicker.View.dialog.CommDialog;
import com.telyo.doublerdatepick.R;

/**
 * Created by telyo on 2018/2/25 0025.
 */

public class PickerFactory {

    private PickerFactory(){
    }
    public static PickerFactory createFactory(){
        return new PickerFactory();
    }
    public void createDatePickerDialog(Context context, DoubleDatePicker.OnDatesSelectListener listener){
        Dialog dialog = new CommDialog(context, R.layout.date_picker
                , ViewGroup.LayoutParams.MATCH_PARENT
                , DpUtils.getScreenHight(context)-200
                , Gravity.BOTTOM);
        DoubleDatePicker picker = dialog.findViewById(R.id.double_date_picker);
        picker.bindDialog(dialog);
        picker.setOnDatesSelectListener(listener);
        dialog.show();
    };
}
