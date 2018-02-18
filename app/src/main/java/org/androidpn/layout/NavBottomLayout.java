package org.androidpn.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.androidpn.demoapp.R;

/**
 * Created by pro1 on 18/2/9.
 */

public class NavBottomLayout extends LinearLayout {

    private ImageView mainPage;
    private ImageView findPage;
    private ImageView minePage;

    public NavBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.nav_bottom, this);

        mainPage = (ImageView) findViewById(R.id.image_nav_main);
        findPage = (ImageView) findViewById(R.id.image_nav_find);
        minePage = (ImageView) findViewById(R.id.image_nav_mine);

        mainPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("nav_test", "main_page");

            }
        });
    }

    private void convertPage(ImageView page) {
        mainPage.setImageResource(R.drawable.nav_main_selected);
        findPage.setImageResource(R.drawable.nav_find);
        minePage.setImageResource(R.drawable.nav_mine);
    }


}
