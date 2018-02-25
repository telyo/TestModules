package com.telyo.doublerdatepick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.telyo.doublerdatepick.DoubleDatePicker.Utils.DateFormatUtils;
import com.telyo.doublerdatepick.DoubleDatePicker.View.DoubleDatePicker;
import com.telyo.doublerdatepick.DoubleDatePicker.View.PickerFactory;
import com.telyo.doublerdatepick.DoubleDatePicker.bean.ChooseDates;

public class MainActivity extends AppCompatActivity {

    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = findViewById(R.id.tv_choose);
    }

    public void datePiker(View view) {
        PickerFactory.createFactory().createDatePickerDialog(this, new DoubleDatePicker.OnDatesSelectListener() {
            @Override
            public void onSelect(ChooseDates chooseDates) {
                date.setText("开始 " + DateFormatUtils.formatDate(chooseDates.getInDate(), "yyyy-MM-dd") + "\n"
                        + "结束 " + DateFormatUtils.formatDate(chooseDates.getOutDate(), "yyyy-MM-dd") + "\n"
                        +"共 " + chooseDates.getDaysCount() + " 晚");
            }
        });
    }
}
