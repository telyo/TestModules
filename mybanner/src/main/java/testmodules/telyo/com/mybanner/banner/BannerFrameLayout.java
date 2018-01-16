package testmodules.telyo.com.mybanner.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import testmodules.telyo.com.mybanner.R;

/**
 * Author Administrator
 * date: 2017/12/26.
 * describe:
 */

public class BannerFrameLayout extends FrameLayout implements Banner.ViewBannerListener, Banner.PositionChangeListener {

    private Banner mBanner;
    private LinearLayout mBottomPointLl;

    private PositionClickListener listener;


    public interface PositionClickListener {
        void onPositionViewClick(int position);
    }

    public void setListener(PositionClickListener listener) {
        this.listener = listener;
    }

    public BannerFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public BannerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBanner();
        initPointView();
    }

    private void initBanner() {
        mBanner = new Banner(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mBanner.setLayoutParams(params);
        mBanner.setListener(this);
        mBanner.setOnPositionChangeListener(this);
        addView(mBanner);
    }

    private void initPointView() {
        mBottomPointLl = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40);
        params.gravity = Gravity.BOTTOM;
        mBottomPointLl.setLayoutParams(params);
        mBottomPointLl.setOrientation(LinearLayout.HORIZONTAL);
        mBottomPointLl.setGravity(Gravity.CENTER);
        mBottomPointLl.setBackgroundColor(Color.BLACK);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mBottomPointLl.setAlpha(0.5f);
        } else {
            mBottomPointLl.getBackground().setAlpha(100);
        }
        addView(mBottomPointLl);
    }

    @Override
    public void clickViewIndex(int pos, View view) {
        if (listener != null){
            listener.onPositionViewClick(pos);
            Log.e("onPositionViewClick","viewId = " + view.getId());
        }
    }

    @Override
    public void onPositionChang(int pos) {
        //Log.e("onPositionChange:" ,"position = "+pos);
        for (int i = 0; i < mBottomPointLl.getChildCount(); i++) {
            ImageView iv = (ImageView) mBottomPointLl.getChildAt(i);
            if (i == pos) {
                iv.setImageLevel(0);
            } else {
                iv.setImageLevel(1);
            }
        }
    }

    public void addList(List<Bitmap> bitmaps) {
        mBanner.setBitmaps(bitmaps);
        for (Bitmap bitmap : bitmaps) {
            addPoint();
        }
    }
    
    private void addPoint() {
        ImageView ivPoint = new ImageView(getContext());
        LinearLayout.LayoutParams ll_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll_params.setMargins(10, 10, 10, 10);
        ivPoint.setImageResource(R.drawable.normal_point);
        ivPoint.setImageLevel(1);
        ivPoint.setLayoutParams(ll_params);
        mBottomPointLl.addView(ivPoint);
    }
    
    
}
