package org.androidpn.demoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ChooseSeatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = createView();
        setContentView(rootView);

    }

    private View createView() {
        LinearLayout linearLayout = new LinearLayout(this);

        return linearLayout;
    }
}
