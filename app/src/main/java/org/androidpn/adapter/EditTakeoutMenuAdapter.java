package org.androidpn.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.androidpn.IQ.ManageTakeoutMenuItemIQ;
import org.androidpn.demoapp.R;
import org.androidpn.model.FoodMenuItem;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.SideslipListView;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

public class EditTakeoutMenuAdapter extends BaseAdapter {
    private List<FoodMenuItem> foodMenuItemList;
    private SideslipListView mSideslipListView;
    private Activity activity;

    public EditTakeoutMenuAdapter(Activity activity, SideslipListView mSideslipListView, List<FoodMenuItem> foodMenuItemList) {
        this.activity = activity;
        this.mSideslipListView = mSideslipListView;
        this.foodMenuItemList = foodMenuItemList;
    }


    @Override
    public int getCount() {
        return foodMenuItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodMenuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = View.inflate(activity, R.layout.item_food_menu, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.priceView = (TextView) convertView.findViewById(R.id.text_price);
            viewHolder.txtv_delete = (TextView) convertView.findViewById(R.id.txtv_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(foodMenuItemList.get(position).getFoodName());
        viewHolder.priceView.setText(String.valueOf(foodMenuItemList.get(position).getPrice()));
        final int pos = position;
        viewHolder.txtv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodMenuItem foodMenuItem = foodMenuItemList.get(pos);

                sendDeleteInquiry(foodMenuItem.getItemId(), foodMenuItem.getBussinessId());

//                foodMenuItemList.remove(pos);
//                notifyDataSetChanged();
//                mSideslipListView.turnNormal();
            }
        });
        return convertView;
    }

    private void sendDeleteInquiry(long itemId, long bussinessId) {
        ManageTakeoutMenuItemIQ deleteTakeoutMenuItemIQ = new ManageTakeoutMenuItemIQ();
        deleteTakeoutMenuItemIQ.setType(IQ.Type.SET);

        deleteTakeoutMenuItemIQ.setBussinessId(String.valueOf(bussinessId));
        deleteTakeoutMenuItemIQ.setItemId(String.valueOf(itemId));
        deleteTakeoutMenuItemIQ.setTarget("delete");

        Log.d("deletemenuitem", "sendDeleteInquiry: "+deleteTakeoutMenuItemIQ.toXML());

        ActivityHolder.getInstance().sendPacket(deleteTakeoutMenuItemIQ);
    }
}

class ViewHolder {
    public TextView textView;
    public TextView priceView;
    public TextView txtv_delete;
}

