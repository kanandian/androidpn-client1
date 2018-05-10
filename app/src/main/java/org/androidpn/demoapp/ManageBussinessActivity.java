package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.androidpn.info.ShopInfo;

public class ManageBussinessActivity extends BaseActivity {

    private TextView updateImageButton;
    private TextView updateInfoButton;
    private TextView updateMenuButton;
    private TextView ordersButton;

    private ShopInfo shopInfo;
    private String classification;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_bussiness);

        initData();
    }

    public void initData() {
        Intent intent = getIntent();

        shopInfo = (ShopInfo) intent.getSerializableExtra("shopInfo");
        classification = shopInfo.getStype();

        updateImageButton = (TextView) findViewById(R.id.btn_update_image);
        updateInfoButton = (TextView) findViewById(R.id.btn_update_info);
        updateMenuButton = (TextView) findViewById(R.id.btn_menu_manage);
        ordersButton = (TextView) findViewById(R.id.btn_orders);

        if ("外卖".equals(classification)) {
            updateMenuButton.setVisibility(View.VISIBLE);
            ordersButton.setVisibility(View.VISIBLE);
        }

        updateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageBussinessActivity.this, UploadImageActivity.class);
                intent.putExtra("bussinessid", shopInfo.getSid());
                startActivity(intent);
            }
        });

        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        updateMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageBussinessActivity.this, EditTakeoutMenuActivity.class);
                intent.putExtra("bussinessId", shopInfo.getSid());
                startActivity(intent);
            }
        });

        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageBussinessActivity.this, TakeoutOrderActivity.class);
                intent.putExtra("bussinessId", shopInfo.getSid());
                startActivity(intent);
            }
        });

    }
}
