package com.telyo.scrollmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        scrollMenuLayout.addMenuChild(R.layout.menu3);
        scrollMenuLayout.addMenuChild(R.layout.menu4);
        scrollMenuLayout.addMenuChild(R.layout.menu5);

        Button button = findViewById(R.id.btn_of_1);
        Button btn_of_2 = findViewById(R.id.btn_of_2);
        Button btn_of_3 = findViewById(R.id.btn_of_3);
        Button btn_of_4 = findViewById(R.id.btn_of_4);
        Button btn_of_5 = findViewById(R.id.btn_of_5);

        ImageView iv = findViewById(R.id.iv);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!scrollMenuLayout.isMenuShowing()) {
                    scrollMenuLayout.showMenu(0);
                } else {
                    scrollMenuLayout.dismissMenu(0);
                }
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

        btn_of_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!scrollMenuLayout.isMenuShowing()) {
                    scrollMenuLayout.showMenu(2);
                } else {
                    scrollMenuLayout.dismissMenu(2);
                }
            }
        });

        btn_of_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!scrollMenuLayout.isMenuShowing()) {
                    scrollMenuLayout.showMenu(3);
                } else {
                    scrollMenuLayout.dismissMenu(3);
                }
            }
        });

        btn_of_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!scrollMenuLayout.isMenuShowing()) {
                    scrollMenuLayout.showMenu(4);
                } else {
                    scrollMenuLayout.dismissMenu(4);
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
