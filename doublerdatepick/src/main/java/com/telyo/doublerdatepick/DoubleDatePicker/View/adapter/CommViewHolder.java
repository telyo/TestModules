package com.telyo.doublerdatepick.DoubleDatePicker.View.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Author Administrator
 * date: 2018/2/2.
 * describe:
 */

public class CommViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public CommViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static CommViewHolder get(Context context, ViewGroup parent, int layoutId) {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        CommViewHolder holder = new CommViewHolder(context, itemView, parent);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void updatePosition(int position) {

    }

    public CommViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public CommViewHolder setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

   

    public CommViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public CommViewHolder setViewBackground(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(ContextCompat.getColor(mContext,colorId));
        return this;
    }
    public CommViewHolder setTextCorlor(int viewId, int colorId) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(mContext,colorId));
        return this;
    }
    public CommViewHolder setBackground(int colorId) {
        itemView.setBackgroundColor(ContextCompat.getColor(mContext,colorId));
        return this;
    }

    public CommViewHolder setOnItemClickListener(View.OnClickListener listener) {
        mConvertView.setOnClickListener(listener);
        return this;
    }
    
    public void viewDoAnim(int viewId){
        View view = getView(viewId);
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,12);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.addAnimation(translateAnimation);
        set.setDuration(200);
        view.startAnimation(set);
    }
}
