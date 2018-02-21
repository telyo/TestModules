package com.telyo.doublerdatepick.DoubleDatePicker.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.telyo.doublerdatepick.DoubleDatePicker.View.adapter.CommViewHolder;
import com.telyo.doublerdatepick.DoubleDatePicker.View.adapter.CommonAdapter;
import com.telyo.doublerdatepick.DoubleDatePicker.View.adapter.DoubleTimePickAdapter;
import com.telyo.doublerdatepick.DoubleDatePicker.bean.ChooseDates;
import com.telyo.doublerdatepick.DoubleDatePicker.bean.DateInfo;
import com.telyo.doublerdatepick.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.telyo.doublerdatepick.DoubleDatePicker.Utils.DateFormatUtils.betweenDate;
import static com.telyo.doublerdatepick.DoubleDatePicker.Utils.DateFormatUtils.day;
import static com.telyo.doublerdatepick.DoubleDatePicker.Utils.DateFormatUtils.formatDate;
import static com.telyo.doublerdatepick.DoubleDatePicker.Utils.DateFormatUtils.getFirstDayForNextMouth;
import static com.telyo.doublerdatepick.DoubleDatePicker.bean.DateInfo.IN_DAY;
import static com.telyo.doublerdatepick.DoubleDatePicker.bean.DateInfo.IN_DAYS;
import static com.telyo.doublerdatepick.DoubleDatePicker.bean.DateInfo.OUT_DAY;
import static com.telyo.doublerdatepick.DoubleDatePicker.bean.DateInfo.OUT_DAYS;
import static com.telyo.doublerdatepick.DoubleDatePicker.bean.DateInfo.UN_ENABLE_DAY;


/**
 * Author Administrator
 * date: 2018/2/5.
 * describe:
 */

public class DoubleDatePicker extends RecyclerView {

    /**
     * 日期数据容器
     */
    private List<DateInfo> dateInfos = new ArrayList<>();
    /**
     * 日期选择，总共有多少个月
     */
    private static int MONTH_COUNT = 12;

    /**
     * 选择一次
     */
    private static final int SELECT_ONCE = 2;
    /**
     * 选择两次
     */
    private static final int SELECT_SECOND = 1;
    /**
     * 默认选择
     */
    private static final int SELECT_DEFAULT = 3;
    /**
     * 用于选择次数计数
     */
    private static int selectState = SELECT_DEFAULT;
    /**
     * 选择的日期
     */
    private ChooseDates chooseDates;

    /**
     * 选择的第一天所在的月
     */
    int currentPosition;

    /**
     * 选择接口回调
     */
    private OnDatesSelectListener onDatesSelectListener;
    private CommonAdapter<DateInfo> adapter;
    private int liveInDays;

    /**
     * @param chooseDates 默认选择日期范围
     */
    public void setChooseDates(ChooseDates chooseDates) {
        this.chooseDates = chooseDates;
    }

    /**
     * @param onDatesSelectListener 监听回调
     */
    public void setOnDatesSelectListener(OnDatesSelectListener onDatesSelectListener) {
        this.onDatesSelectListener = onDatesSelectListener;
    }

    /**
     * @param monthCount 控件支持有多长的月数
     */
    public static void setMonthCount(int monthCount) {
        MONTH_COUNT = monthCount;
    }

    private Context mContext;

    public DoubleDatePicker(Context context) {
        this(context, null);
    }

    public DoubleDatePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleDatePicker(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initDate();
        initBaseRecycler();

    }

    /**
     * 在这里利用Calender初始化所有的日期
     */
    private void initDate() {
        for (int i = 0; i < MONTH_COUNT; i++) {
            DateInfo dateInfo = new DateInfo();
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setTime(getFirstDayForNextMouth(i));
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//获得当前日期所在月份有多少天（或者说day的最大值)，用于后面的计算
            Calendar calendarClone = (Calendar) calendar.clone();
            calendarClone.set(Calendar.DAY_OF_MONTH, 1);
            int startDayOfWeek = calendarClone.get(Calendar.DAY_OF_WEEK);//获得当前日期所在月份的第一天是星期几

            calendar.set(Calendar.DAY_OF_MONTH, 1); //由于是获取当月日期信息，所以直接操作当月Calendar即可。将日期调为当月第一天
            List<DateInfo.DayOfMonth> dayOfMonths = new ArrayList<>();
            dateInfo.setMouth(formatDate(calendar.getTime(), "yyyy年MM月"));
            for (int j = 0; j < maxDay; j++) {
                Date date = calendar.getTime();
                DateInfo.DayOfMonth dayOfMonth = new DateInfo.DayOfMonth();
                dayOfMonth.setState(getDayState(date, chooseDates == null ? null : chooseDates.getInDate(), chooseDates == null ? 4 : chooseDates.getDaysCount()));
                dayOfMonth.setDate(date);
                dayOfMonths.add(dayOfMonth);
                calendar.add(Calendar.DAY_OF_MONTH, 1); //向后推移一天
            }
            dateInfo.setDayForMonths(dayOfMonths);
            dateInfo.setStartDayOfWeek(startDayOfWeek);
            dateInfos.add(dateInfo);
        }


    }

    /**
     * 双Recycler布局
     */
    private void initBaseRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        setLayoutManager(manager);
        if (chooseDates == null) {
            chooseDates = new ChooseDates();
        }
        adapter = new CommonAdapter<DateInfo>(mContext, R.layout.perent, dateInfos) {
            @Override
            public void convert(CommViewHolder holder, DateInfo dateInfo) {
                childDate(holder, dateInfo);
                holder.setText(R.id.tv_mouth, dateInfo.getMouth());
            }

        };
        setAdapter(adapter);
        addOnItemTouchListener(new OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void childDate(CommViewHolder holder, DateInfo dateInfo) {
        RecyclerView recyclerView = holder.getView(R.id.recyclerView);
        RecyclerView.LayoutManager m = new GridLayoutManager(mContext, 7, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(m);
        int startDayOfWeek = dateInfo.getStartDayOfWeek();
        List<DateInfo.DayOfMonth> dayForMonths = dateInfo.getDayForMonths();
        DoubleTimePickAdapter<DateInfo.DayOfMonth> childAdapter = new DoubleTimePickAdapter<DateInfo.DayOfMonth>(mContext
                , R.layout.child, dayForMonths, startDayOfWeek) {
            @Override
            public void convert(CommViewHolder holder, final DateInfo.DayOfMonth dayOfMonth) {
                if (dayOfMonth.getDate() != null) {
                    holder.setText(R.id.tv_day, formatDate(dayOfMonth.getDate(), "dd"));
                }
                holder.setText(R.id.tv_state, "");
                holder.setText(R.id.tv_feast, "");
                holder.setTextCorlor(R.id.tv_feast, R.color.colorAccent);
                holder.setTextCorlor(R.id.tv_day, R.color.color_333);
                holder.setTextCorlor(R.id.tv_state, R.color.color_333);
                holder.setBackground(R.color.white);
                holder.setVisibility(R.id.tv_msg, GONE);
                holder.itemView.setEnabled(true);
                switch (dayOfMonth.getState()) {
                    case IN_DAY://入住当天
                        holder.setText(R.id.tv_state, "入住");
                        holder.setBackground(R.color.colorPrimary);
                        holder.setTextCorlor(R.id.tv_day, R.color.white);
                        holder.setTextCorlor(R.id.tv_feast, R.color.white);
                        holder.setTextCorlor(R.id.tv_state, R.color.white);
                        if (selectState == SELECT_SECOND) {
                            holder.setText(R.id.tv_msg, "请选择离店日期");
                            holder.setVisibility(R.id.tv_msg, VISIBLE);
                            holder.viewDoAnim(R.id.tv_msg);
                        }
                        break;
                    case OUT_DAY://离店当天
                        holder.setText(R.id.tv_state, "离店");
                        holder.setBackground(R.color.colorPrimary);
                        holder.setTextCorlor(R.id.tv_day, R.color.white);
                        holder.setTextCorlor(R.id.tv_feast, R.color.white);
                        holder.setTextCorlor(R.id.tv_state, R.color.white);
                        if (selectState == SELECT_ONCE) {
                            holder.setText(R.id.tv_msg, liveInDays + "晚");
                            holder.setVisibility(R.id.tv_msg, VISIBLE);
                            holder.viewDoAnim(R.id.tv_msg);
                        }
                        break;
                    case IN_DAYS://入住期间
                        holder.setText(R.id.tv_state, "");
                        holder.setBackground(R.color.color_in_days);
                        break;
                    case OUT_DAYS://没有入住期间
                        holder.setText(R.id.tv_state, "");
                        break;
                    case UN_ENABLE_DAY://不可入住的日期
                        holder.setTextCorlor(R.id.tv_state, R.color.color_999);
                        holder.setTextCorlor(R.id.tv_feast, R.color.color_999);
                        holder.setTextCorlor(R.id.tv_day, R.color.color_999);
                        holder.itemView.setEnabled(false);
                        break;
                    default:
                        holder.itemView.setVisibility(INVISIBLE);
                        break;
                }
                holder.setOnItemClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (selectState) {
                            case SELECT_ONCE:
                            case SELECT_DEFAULT:
                                chooseDates.setInDate(dayOfMonth.getDate());
                                selectState = SELECT_SECOND;
                                updateState(dayOfMonth.getDate(), 0);
                                break;
                            case SELECT_SECOND:
                                liveInDays = betweenDate(chooseDates.getInDate(), dayOfMonth.getDate());
                                if (liveInDays <= 0) {
                                    chooseDates.setInDate(dayOfMonth.getDate());
                                    updateState(dayOfMonth.getDate(), 0);
                                } else {
                                    chooseDates.setOutDate(dayOfMonth.getDate());
                                    Toast.makeText(mContext, liveInDays + "晚", Toast.LENGTH_SHORT).show();
                                    chooseDates.setDaysCount(liveInDays);
                                    selectState = SELECT_ONCE;
                                    updateState(chooseDates.getInDate(), liveInDays);
                                    if (onDatesSelectListener == null) break;
                                    onDatesSelectListener.onSelect(chooseDates);
                                }
                                break;
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };
        recyclerView.setAdapter(childAdapter);
    }



    private void updateState(Date date, int days) {
        int size = dateInfos.size();
        for (int i = 0; i < size; i++) {
            DateInfo dateInfo = dateInfos.get(i);
            List<DateInfo.DayOfMonth> dayOfMonths = dateInfo.getDayForMonths();
            for (DateInfo.DayOfMonth dayOfMonth : dayOfMonths) {
                int state = getDayState(dayOfMonth.getDate(), date, days);
                if (state == IN_DAY) currentPosition = i;
                dayOfMonth.setState(state);
            }
        }
    }


    public interface OnDatesSelectListener {
        void onSelect(ChooseDates chooseDates);
    }

    public void setChooseDays(ChooseDates chooseDays) {
        this.chooseDates = chooseDays;
        updateState(chooseDays.getInDate(), chooseDays.getDaysCount());
        scrollToPosition(currentPosition);
    }
    
    /**
     * @param date   需要判断状态的日期
     * @param inDate 入住日期 默认是实时日期
     * @param days   入住多少天
     * @return 日期状态
     */
    public static int getDayState(Date date, Date inDate, int days) {
        long countBaseInDate;
        long countBaseCurrentDate = System.currentTimeMillis() / day;
        if (inDate == null) {
            countBaseInDate = countBaseCurrentDate;
        } else {
            countBaseInDate = inDate.getTime() / day;
        }
        int baseIn = (int) ((date.getTime() / day - countBaseInDate));
        int baseCurrent = (int) ((date.getTime() / day - countBaseCurrentDate));
        if (baseIn == 0) {
            return IN_DAY;
        } else if (baseIn > 0 && baseIn < days) {
            return IN_DAYS;
        } else if (baseIn == days) {
            return OUT_DAY;
        } else if (baseCurrent < 0) {
            return UN_ENABLE_DAY;
        } else if (baseIn > 30 && selectState == SELECT_SECOND) {
            return UN_ENABLE_DAY;
        }

        return OUT_DAYS;
    }

}
