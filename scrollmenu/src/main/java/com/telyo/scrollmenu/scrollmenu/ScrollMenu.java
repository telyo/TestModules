package com.telyo.scrollmenu.scrollmenu;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.telyo.scrollmenu.R;

/**
 * Author Administrator
 * date: 2018/2/8.
 * describe:
 */

public class ScrollMenu extends ViewGroup {
    String TAG = "ScrollMenu";
    private Context mContext;
    private int childLayoutId;
    private int height;
    private int width;
    private boolean isShowing;
    private int duration = 200;
    private int childCount;

    public int getCurrentChild() {
        return currentChild;
    }

    private int currentChild;

    private Handler handler = new Handler();
    private LayoutInflater mLayoutInflater;

    public ScrollMenu(Context context) {
        this(context, null);
    }

    public ScrollMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        addChild(attrs);
        setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.holo_blue_light));
        setClickable(true);
    }


    private void addChild(AttributeSet attrs) {
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.ScrollMenu);
        childLayoutId = a.getResourceId(R.styleable.ScrollMenu_childDefaultLayoutId, 0);
        if (childLayoutId != 0) {
            addChild(childLayoutId);
        }
        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        childCount = getChildCount();
        for (int j = 0; j < childCount; j++) {
            View child = getChildAt(j);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            if (height < child.getMeasuredHeight()) {
                height = child.getMeasuredHeight();
            }
            width = child.getMeasuredWidth();
        }

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        childCount = getChildCount();

        for (int j = 0; j < childCount; j++) {
            getChildAt(j).layout(0, 0, width, getChildAt(j).getMeasuredHeight());
        }
        isToShowMenu(this, 0, -height * 100, 0);


    }

    public void show(int child) {
        if (!isShowing) {
            for (int j = 0; j < childCount; j++) {
                getChildAt(j).setVisibility(GONE);
            }
            currentChild = child;
            View childView = getChildAt(child);
            childView.setVisibility(VISIBLE);
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            childView.measure(w, h);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = childView.getMeasuredHeight();
            ViewGroup parent = (ViewGroup) getParent();
            parent.removeView(this);
            parent.addView(this, params);
            isToShowMenu(this, -height, 0, duration);
            alphaAnim(1, 0, duration);
            isShowing = true;
        }
    }

    public void dismiss(final int child) {
        if (isShowing) {
            isToShowMenu(this, 0, -height, duration);
            alphaAnim(0, 1, duration);
            if (child != currentChild) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        show(child);
                    }
                }, duration - 100);
            }
            isShowing = false;

        }
    }

    private void alphaAnim(int to, int from, int duration) {
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(this, "alpha", from, to);
        animatorAlpha.setDuration(duration);
        animatorAlpha.setInterpolator(new LinearInterpolator());
        animatorAlpha.start();
    }

    public boolean isShowing() {
        return isShowing;
    }

    private void isToShowMenu(View view, int from, int to, int duration) {
        ObjectAnimator animatorTranY = ObjectAnimator.ofFloat(view, "translationY", from, to);
        animatorTranY.setDuration(duration);
        animatorTranY.setInterpolator(new LinearInterpolator());
        animatorTranY.start();
    }

    public void addChild(int layoutId) {
        View view = mLayoutInflater.inflate(layoutId, null, false);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(view, params);
    }

    public void addChilds(int[] layoutIds) {
        for (int layoutId : layoutIds) {
            View view = mLayoutInflater.inflate(layoutId, null, false);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            addView(view, params);
        }
    }
}
