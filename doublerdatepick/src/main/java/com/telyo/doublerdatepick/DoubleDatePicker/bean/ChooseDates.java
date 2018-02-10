package com.telyo.doublerdatepick.DoubleDatePicker.bean;

import java.util.Date;

/**
 * Author Administrator
 * date: 2018/2/5.
 * describe:
 */

public class ChooseDates {
    
    private Date inDate;
    private Date outDate;
    private int daysCount;

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }
}
