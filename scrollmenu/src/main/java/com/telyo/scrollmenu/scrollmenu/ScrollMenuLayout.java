package com.telyo.scrollmenu.scrollmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.telyo.scrollmenu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Administrator
 * date: 2018/2/8.
 * describe:
 */

public class ScrollMenuLayout extends RelativeLayout {

    private static final String TAG = "ScrollMenuLayout";
    private int menuLayoutId;
    private ScrollMenu scrollMenu;
    private ImageView bgView;
    private Context mContext;
    private AttributeSet mAttrs;
    private boolean isLayout;
    public ScrollMenuLayout(Context context) {
        this(context, null);
    }

    public ScrollMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mAttrs = attrs;
        isLayout = true;
        initScrollMenu();
        initBackground();
        Log.e(TAG,"getChildCount = " + getChildCount());
        for (int i =0;i<getChildCount();i++){
            Log.e(TAG,"child " + i +" = "+getChildAt(i).toString());
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (isLayout){
            isLayout=false;
            removeView(bgView);
            addView(bgView);
        }
        
    }

    private void initBackground() {
        bgView = new ImageView(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bgView.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
        bgView.setVisibility(GONE);
        bgView.setAlpha(0.7f);
        addView(bgView,-1, params);
        bgView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissMenu(scrollMenu.getCurrentChild());
                Toast.makeText(mContext, "消失", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initScrollMenu() {
        TypedArray a = mContext.obtainStyledAttributes(mAttrs, R.styleable.ScrollMenu);
        menuLayoutId = a.getResourceId(R.styleable.ScrollMenu_childDefaultLayoutId, 0);
        scrollMenu = new ScrollMenu(mContext);
        if (menuLayoutId != 0) {
            scrollMenu.addChild(menuLayoutId);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(scrollMenu,-1, params);
        a.recycle();
    }

    
    public void showMenu(int child) {
        scrollMenu.show(child);
        bgView.setVisibility(VISIBLE);
    }

    public void dismissMenu(int child) {
        if (scrollMenu.getCurrentChild() == child) {
            bgView.setVisibility(INVISIBLE);
        }
        scrollMenu.dismiss(child);
    }


    public void addMenuChild(int layoutId) {
        scrollMenu.addChild(layoutId);
    }

    public void addMenuChilds(int[] layoutIds) {
        scrollMenu.addChilds(layoutIds);
    }

    public ScrollMenu getScrollMenu() {
        return scrollMenu;
    }

    public boolean isMenuShowing() {
        return scrollMenu.isShowing();
    }
}
