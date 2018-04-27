package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderSuccessActivity extends BaseActivity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        initData();
    }

    private void initData() {
        backButton = (Button) findViewById(R.id.btn_go_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSuccessActivity.this, FrameActivity.class);
                startActivity(intent);
            }
        });
    }
}
