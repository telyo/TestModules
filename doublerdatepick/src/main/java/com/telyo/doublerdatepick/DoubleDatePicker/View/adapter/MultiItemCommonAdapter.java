package com.telyo.doublerdatepick.DoubleDatePicker.View.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.telyo.doublerdatepick.DoubleDatePicker.View.adapter.interfaces.MultiItemTypeSupport;

import java.util.List;


/**
 * Author Administrator
 * date: 2018/2/2.
 * describe:
 */

public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    
    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public CommViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        CommViewHolder holder = CommViewHolder.get(mContext, parent, layoutId);
        return holder;
    }
  
}
