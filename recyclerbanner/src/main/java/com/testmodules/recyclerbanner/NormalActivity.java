package com.testmodules.recyclerbanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.testmodules.recyclerbanner.banner.RecyclerViewBannerBase;
import com.testmodules.recyclerbanner.banner.RecyclerViewBannerNormal;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Administrator
 * date: 2018/1/8.
 * describe:
 */

public class NormalActivity extends AppCompatActivity{
    RecyclerViewBannerNormal banner, banner2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        banner = findViewById(R.id.banner);
        banner2 = findViewById(R.id.banner2);
        List<String> list = new ArrayList<>();
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/69427561.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/23738150.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/30127126.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/36125492.jpg");
        banner.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(NormalActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner2.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(NormalActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
