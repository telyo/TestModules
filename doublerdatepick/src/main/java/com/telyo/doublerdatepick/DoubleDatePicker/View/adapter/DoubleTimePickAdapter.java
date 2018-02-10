package com.telyo.doublerdatepick.DoubleDatePicker.View.adapter;

import android.content.Context;

import java.util.List;

/**
 * Author Administrator
 * date: 2018/2/5.
 * describe:
 */

public abstract class DoubleTimePickAdapter<T> extends CommonAdapter<T> {
    private int startDayOfWeek;
    public DoubleTimePickAdapter(Context context, int layoutId, List<T> datas,int startDayOfWeek) {
        super(context, layoutId, datas);
        this.startDayOfWeek = startDayOfWeek -1;
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + startDayOfWeek;
    }

    @Override
    public void onBindViewHolder(CommViewHolder holder, int position) {
        if (position > startDayOfWeek-1){
            convert(holder, mDatas.get(position  - startDayOfWeek));
        }
    }
}
