package com.telyo.doublerdatepick;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.telyo.doublerdatepick.DoubleDatePicker.View.dialog.CommDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void datePiker(){
        Dialog dialog = new CommDialog(this,R.layout.date_picker, Gravity.BOTTOM); 
    }
}
