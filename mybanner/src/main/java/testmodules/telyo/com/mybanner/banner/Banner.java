package testmodules.telyo.com.mybanner.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author Administrator
 * date: 2017/12/26.
 * describe:
 */

public class Banner extends ViewGroup {
    //子View的个数、下标
    private int mChildCount;
    private int mCurrentIndex = 0;

    //控件的宽高
    private int childWidth;
    private int childHeight;
    private int mFirstTouchX;
    private int mMoveX;
    /**
     * 图片左右两边的空白间距
     */
    public static final int IMAGE_PADDING = 0;
    /**
     * 滚动到下一张图片的速度
     */
    private static final int SNAP_VELOCITY = 600;
    /**
     * 表示滚动到下一张图片这个动作
     */
    private static final int SCROLL_NEXT = 0;
    /**
     * 表示滚动到上一张图片这个动作
     */
    private static final int SCROLL_PREVIOUS = 1;
    /**
     * 表示滚动回原图片这个动作
     */
    private static final int SCROLL_BACK = 2;

    private ViewBannerListener listener;
    private PositionChangeListener positionChangeListener;
    private boolean isClick = true;
    private boolean isBannerUseful = true;
    private String TAG = "Banner";
    private boolean forceToRelayout;
    private ArrayList<Integer> mItems = new ArrayList<>();
    private boolean isFirstLayout;
    //传入的bitmap
    private List<Bitmap> bitmaps;
    public void setBitmaps(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    public interface ViewBannerListener {
        void clickViewIndex(int pos, View view);
    }

    public interface PositionChangeListener {
        void onPositionChang(int pos);
    }


    public void setListener(ViewBannerListener listener) {
        this.listener = listener;
    }

    public void setOnPositionChangeListener(PositionChangeListener listener) {
        this.positionChangeListener = listener;
    }

    private boolean isAuto = true;
    private Timer timer = new Timer();
    private TimerTask task;
    private Handler autoHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    scrollToNext();
                    break;
            }

        }
    };

    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initChildViewHolder();
        initObject();
    }

    private void initChildViewHolder() {
        for (int i = 0;i<3;i++) {
            ImageView imageView = new ImageView(getContext());
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(lp);
            addView(imageView);
        }
    }

    private void initObject() {
        mScroller = new Scroller(getContext());
        isFirstLayout = true;
        initTimer();
    }

    private void initTimer() {
        task = new TimerTask() {
            @Override
            public void run() {
                if (isAuto && isBannerUseful) {
                    autoHandler.sendEmptyMessage(0);
                }
                isBannerUseful = true;
            }
        };
        timer.schedule(task, 2000, 3000);
    }

    private void startAuto() {
        isAuto = true;
    }

    private void stopAuto() {
        isAuto = false;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mChildCount = getChildCount();
        if (mChildCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            View view = getChildAt(0);
            childHeight = view.getMeasuredHeight();
            childWidth = view.getMeasuredWidth();
            setMeasuredDimension(childWidth * mChildCount, childHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed || forceToRelayout) {
            if (mCurrentIndex >= 0 && mCurrentIndex < mChildCount) {
                mScroller.abortAnimation();
                setScrollX(0);
                int left = -childWidth;
                updateItems();
                // 通过循环为每张图片设定位置  
                for (int i = 0; i < mChildCount; i++) {
                    View childView =  getChildAt(mItems.get(i));

                    childView.layout(left + IMAGE_PADDING, 0, left
                            + childWidth - IMAGE_PADDING, childHeight);
                    left = left + childWidth;
                }
            }
            forceToRelayout = false;
            if (isFirstLayout) {
                isFirstLayout = false;
                if (positionChangeListener != null) {
                    positionChangeListener.onPositionChang(mCurrentIndex);
                }
            }
        }
    }

    private void updateItems() {
        // 分别获取每个位置上应该显示的图片下标  
        Log.e(TAG,"mCurrentIndex = " + mCurrentIndex  + " mChildCount = " + mChildCount);
        Log.e(TAG,"-----------------" );
        mItems.clear();
        for (int i = 1; i <= mChildCount; i++) {
            mItems.add(getIndexForItem(i));
            Log.e(TAG,"mItems["+i+"] = " +mItems.get(i-1));
        }
    }

    /**
     * 根据当前图片的下标和传入的item参数，来判断item位置上应该显示哪张图片。
     *
     * @param item 取值范围是1-5
     * @return 对应item位置上应该显示哪张图片。
     */
    private int getIndexForItem(int item) {
        int index = -1;
        index = mCurrentIndex + item - 2;
        while (index < 0) {
            index = index + mChildCount;
        }
        while (index > mChildCount - 1) {
            index = index - mChildCount;
        }
        return index;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopAuto();
                isClick = true;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mFirstTouchX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                isClick = false;

                mMoveX = (int) event.getX();
                int distance = (mMoveX - mFirstTouchX);
                scrollBy(-distance, 0);
                mFirstTouchX = mMoveX;
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                int velocityX = (int) mVelocityTracker.getXVelocity();

                if (!isClick) {
                    if (shouldScrollToNext(velocityX)) {
                        // 滚动到下一张图  
                        scrollToNext();
                    } else if (shouldScrollToPrevious(velocityX)) {
                        // 滚动到上一张图  
                        scrollToPrevious();
                    } else {
                        // 滚动回当前图片  
                        scrollBack();
                    }
                    isClick = true;
                    isBannerUseful = false;
                } else {
                    listener.clickViewIndex(mCurrentIndex, getChildAt(mCurrentIndex));
                }

                startAuto();
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            default:
                break;
        }
        return true;

    }


    /**
     * 判断是否应该滚动到下一张图片。
     */
    private boolean shouldScrollToNext(int velocityX) {
        return velocityX < -SNAP_VELOCITY || getScrollX() > childWidth / 2;
    }

    /**
     * 判断是否应该滚动到上一张图片。
     */
    private boolean shouldScrollToPrevious(int velocityX) {
        return velocityX > SNAP_VELOCITY || getScrollX() < -childWidth / 2;
    }

    /**
     * 滚动到下一张图片。
     * 每次requestLayout会重置getScrollX()，getScrollY()
     */
    public void scrollToNext() {
        if (mScroller.isFinished()) {
            Log.e(TAG,"getScrollX() = " + getScrollX());
            int disX = childWidth - getScrollX();
            checkImageSwitchBorder(SCROLL_NEXT);
            if (positionChangeListener != null) {
                positionChangeListener.onPositionChang(mCurrentIndex);
            }
            beginScroll(getScrollX(), 0, disX, 0, SCROLL_NEXT);
        }
    }

    /**
     * 滚动到上一张图片。
     */
    public void scrollToPrevious() {
        if (mScroller.isFinished()) {
            int disX = -childWidth - getScrollX();
            checkImageSwitchBorder(SCROLL_PREVIOUS);
            if (positionChangeListener != null) {
                positionChangeListener.onPositionChang(mCurrentIndex);
            }
            beginScroll(getScrollX(), 0, disX, 0, SCROLL_PREVIOUS);
        }
    }

    /**
     * 滚动回原图片。
     */
    public void scrollBack() {
        if (mScroller.isFinished()) {
            beginScroll(getScrollX(), 0, -getScrollX(), 0, SCROLL_BACK);
        }
    }

    /**
     * 检查图片的边界，防止图片的下标超出规定范围。
     */
    private void checkImageSwitchBorder(int action) {
        if (action == SCROLL_NEXT && ++mCurrentIndex >= mChildCount) {
            mCurrentIndex = 0;
        } else if (action == SCROLL_PREVIOUS && --mCurrentIndex < 0) {
            mCurrentIndex = mChildCount - 1;
        }
    }

    /**
     * 让控件中的所有图片开始滚动。
     */
    private void beginScroll(int startX, int startY, int dx, int dy,
                             final int action) {
        int duration = (int) (700f / childWidth * Math.abs(dx));
        mScroller.startScroll(startX, startY, dx, dy, duration);
        invalidate();
        autoHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (action == SCROLL_NEXT || action == SCROLL_PREVIOUS) {
                    forceToRelayout = true;
                    requestLayout();
                }
            }
        }, duration);
    }
}
