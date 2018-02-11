package org.androidpn.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.androidpn.demoapp.R;

/**
 * Created by pro1 on 18/2/9.
 */

public class NavBottomLayout extends LinearLayout {

    private LinearLayout indexPage;

    public NavBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.nav_bottom, this);

        indexPage = (LinearLayout) findViewById(R.id.layout_page_index);
        indexPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
