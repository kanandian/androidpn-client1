package org.androidpn.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.androidpn.demoapp.MainActivity;
import org.androidpn.demoapp.PersonalActivity;
import org.androidpn.demoapp.R;
import org.androidpn.demoapp.RegisterActivity;
import org.androidpn.utils.ActivityHolder;

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
                mainPage.setImageResource(R.drawable.nav_main_selected);
                findPage.setImageResource(R.drawable.nav_find);
                minePage.setImageResource(R.drawable.nav_mine);

                convertActivity(MainActivity.class);
            }
        });

        findPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPage.setImageResource(R.drawable.nav_main);
                findPage.setImageResource(R.drawable.nav_find_selected);
                minePage.setImageResource(R.drawable.nav_mine);

            }
        });

        minePage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPage.setImageResource(R.drawable.nav_main);
                findPage.setImageResource(R.drawable.nav_find);
                minePage.setImageResource(R.drawable.nav_mine_selected);

                convertActivity(RegisterActivity.class);
            }
        });
    }

    private void convertActivity(Class<?> activityclass) {
        Activity currentActivity = ActivityHolder.getInstance().getCurrentActivity();
        if(currentActivity.getClass() != activityclass) {
            Intent intent = new Intent(currentActivity, activityclass);
            currentActivity.startActivity(intent);
        }
    }


}
