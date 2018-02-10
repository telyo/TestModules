package com.telyo.scrollmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.telyo.scrollmenu.scrollmenu.ScrollMenuLayout;

public class MainActivity extends AppCompatActivity {

    private ScrollMenuLayout scrollMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollMenuLayout = findViewById(R.id.scrollMenuLayout);
        scrollMenuLayout.addMenuChild(R.layout.menu2);
        Button button = findViewById(R.id.btn_of);
        ImageView iv = findViewById(R.id.iv);
        Button btn_of_2 = findViewById(R.id.btn_of_2);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        button.measure(w, h);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("scroll", "before 状态  :  " + scrollMenuLayout.isMenuShowing());

                if (!scrollMenuLayout.isMenuShowing()) {
                    scrollMenuLayout.showMenu(0);
                } else {
                    scrollMenuLayout.dismissMenu(0);
                }
                Log.e("scroll", "after 状态  :  " + scrollMenuLayout.isMenuShowing());

                Log.e("scroll", "-------=========-------");

            }
        });
        btn_of_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!scrollMenuLayout.isMenuShowing()) {
                    scrollMenuLayout.showMenu(1);
                } else {
                    scrollMenuLayout.dismissMenu(1);
                }
            }
        });
        
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "啦啦啦", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
