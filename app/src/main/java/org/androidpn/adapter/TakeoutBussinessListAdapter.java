package org.androidpn.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.demoapp.R;
import org.androidpn.model.TakeoutOrder;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.MessageFormatUtil;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class TakeoutBussinessListAdapter extends ArrayAdapter<TakeoutOrder> {

    private int resource;

    public TakeoutBussinessListAdapter(Context context, int resource, List<TakeoutOrder> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TakeoutOrder takeoutOrder = getItem(position);
        LinearLayout item = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        vi.inflate(resource, item, true);

        TextView bussinessNameText = (TextView) item.findViewById(R.id.text_bussiness_name);
        TextView foodSummaryText = (TextView) item.findViewById(R.id.text_food_summary);
        TextView priceText = (TextView) item.findViewById(R.id.text_price);
        TextView createTimeText = (TextView) item.findViewById(R.id.text_createtime);
        TextView orderStatusText = (TextView) item.findViewById(R.id.text_order_status);
        Button nextButton = (Button) item.findViewById(R.id.btn_next);
        Button rejectButton = (Button) item.findViewById(R.id.btn_reject_order);

        bussinessNameText.setText(takeoutOrder.getBussinessName());
        foodSummaryText.setText(takeoutOrder.getFirstFoodName()+"等"+takeoutOrder.getItemCount()+"个商品");
        priceText.setText(String.valueOf("￥"+takeoutOrder.getTotalPrice()));
        createTimeText.setText(takeoutOrder.getCreateTime());

        final TakeoutOrder takeoutOrder1 = takeoutOrder;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUpdateOrderStatus(takeoutOrder1, 1);
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUpdateOrderStatus(takeoutOrder1, -1);
            }
        });

        if (takeoutOrder.getOrderStatus() == -1) {
            orderStatusText.setVisibility(View.VISIBLE);
            orderStatusText.setText("已拒单");
        } else if (takeoutOrder.getOrderStatus() == 0) {
            nextButton.setVisibility(View.VISIBLE);
            rejectButton.setVisibility(View.VISIBLE);

            nextButton.setText("接单");
        } else if (takeoutOrder.getOrderStatus() == 3) {
            orderStatusText.setVisibility(View.VISIBLE);
            orderStatusText.setText("已完成");
        } else {
            nextButton.setVisibility(View.VISIBLE);

            if (takeoutOrder.getOrderStatus() == 1) {
                nextButton.setText("配送");
            } else if (takeoutOrder.getOrderStatus() == 2) {
                nextButton.setText("送达");
            }
        }



        return item;
    }


    public void sendUpdateOrderStatus(TakeoutOrder takeoutOrder, int code) {
        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.SET);

        inquiryIQ.setTarget("orderstatus");
        inquiryIQ.setTitle(String.valueOf(takeoutOrder.getOrderId()));
        inquiryIQ.setUserName(String.valueOf(takeoutOrder.getBussinessId()));

        inquiryIQ.setContent(String.valueOf(code));

        Log.d("updateorderstatus", "getView: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);
    }


}
