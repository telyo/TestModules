package com.drawabletest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv_drawable_test;
    private ImageView mIv_drawable_test;
    private ImageView iv_show_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv_drawable_test = findViewById(R.id.tv_drawable_test);
        mIv_drawable_test = findViewById(R.id.iv_drawable_test);
        iv_show_message = findViewById(R.id.iv_show_message);
        iv_show_message.setOnClickListener(new View.OnClickListener() {
            boolean isShow = false;
            @Override
            public void onClick(View view) {
                if (!isShow){
                    iv_show_message.setImageLevel(1);
                }else {
                    iv_show_message.setImageLevel(0);
                }
                isShow = !isShow;
            }
        });
    }
}
