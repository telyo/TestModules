package com.telyo.doublerdatepick.DoubleDatePicker.bean;

import java.util.Date;
import java.util.List;

/**
 * Author Administrator
 * date: 2018/2/5.
 * describe:
 */

public class DateInfo {

    public static final int IN_DAY = 5;
    public static final int OUT_DAY = 1;
    public static final int IN_DAYS = 2;
    public static final int OUT_DAYS = 3;
    public static final int UN_ENABLE_DAY = 4;

    private List<DayOfMonth> dayForMonths;
    private String mouth;
    int startDayOfWeek;

    public int getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public void setStartDayOfWeek(int startDayOfWeek) {
        this.startDayOfWeek = startDayOfWeek;
    }

    public String getMouth() {
        return mouth;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    public List<DayOfMonth> getDayForMonths() {
        return dayForMonths;
    }

    public void setDayForMonths(List<DayOfMonth> dayForMonths) {
        this.dayForMonths = dayForMonths;
    }

    public static class DayOfMonth {
        
        private Date date;
        private String feast;//节日
        private int state;//入住、离店、已入住、未入住、不可用
        
        
        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getFeast() {
            return feast;
        }

        public void setFeast(String feast) {
            this.feast = feast;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

}
