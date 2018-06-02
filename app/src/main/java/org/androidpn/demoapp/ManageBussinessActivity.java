package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.androidpn.IQ.DeleteBussinessIQ;
import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.info.ShopInfo;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

import java.util.Set;

public class ManageBussinessActivity extends BaseActivity {

    private TextView updateImageButton;
    private TextView updateInfoButton;
    private TextView updateMenuButton;
    private TextView ordersButton;
    private TextView deleteButton;

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
        deleteButton = (TextView) findViewById(R.id.btn_delete);

        if ("外卖".equals(classification)) {
            updateMenuButton.setVisibility(View.VISIBLE);
            ordersButton.setVisibility(View.VISIBLE);
        }

        updateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageBussinessActivity.this, UploadImageActivity.class);
                intent.putExtra("imageName", shopInfo.getSid());
                intent.putExtra("target", "bussiness");
                startActivity(intent);
            }
        });

        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageBussinessActivity.this, UpdateBussinessInfoActivity.class);
                intent.putExtra("shopInfo", shopInfo);
                startActivity(intent);
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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteBussinessIQ deleteBussinessIQ = new DeleteBussinessIQ();
                deleteBussinessIQ.setType(IQ.Type.SET);

                deleteBussinessIQ.setBussinessId(shopInfo.getSid());

                Log.d("qzf", "onClick: "+deleteBussinessIQ.toXML());

                ActivityHolder.getInstance().sendPacket(deleteBussinessIQ);

            }
        });

    }

    @Override
    public void updateForResponse(ResultModelIQ resultModelIQ) {
        super.updateForResponse(resultModelIQ);

        if (resultModelIQ.getErrCode() == 0) {
            ManageBussinessActivity.this.finish();
        }

    }
}
