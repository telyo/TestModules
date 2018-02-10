package com.telyo.doublerdatepick.DoubleDatePicker.View.adapter.interfaces;

/**
 * Author Administrator
 * date: 2018/2/2.
 * describe:
 */

public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
