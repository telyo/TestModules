package testmodules.telyo.com.carouselview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BannerFrameLayout.PositionClickListener {

    private BannerFrameLayout mVbf_carouse;

    private List<Bitmap> bitmaps = new ArrayList<>();
    private int[] resIds = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVbf_carouse = findViewById(R.id.vbf_carouse);

        ImageView iv_1 = findViewById(R.id.iv_1);
        ImageView iv_2 = findViewById(R.id.iv_2);
        ImageView iv_3 = findViewById(R.id.iv_3);
        ImageView iv_4 = findViewById(R.id.iv_4);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), resIds[0]);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), resIds[1]);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), resIds[2]);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), resIds[3]);
        bitmaps.add(bitmap1);
        bitmaps.add(bitmap2);
        bitmaps.add(bitmap3);
        bitmaps.add(bitmap4);
        iv_1.setImageBitmap(bitmaps.get(0));
        iv_2.setImageBitmap(bitmaps.get(1));
        iv_3.setImageBitmap(bitmaps.get(2));
        iv_4.setImageBitmap(bitmaps.get(3));


        mVbf_carouse.addList(bitmaps);
        mVbf_carouse.setListener(this);
    }

    @Override
    public void onPositionViewClick(int position) {
        Toast.makeText(this, "position=" + position, Toast.LENGTH_SHORT).show();
    }
}
