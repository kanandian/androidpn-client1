package org.androidpn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidpn.demoapp.R;
import org.androidpn.model.TakeoutOrder;

import java.util.List;

public class TakeoutListAdapter extends ArrayAdapter<TakeoutOrder> {

    private int resource;

    public TakeoutListAdapter(Context context, int resource, List<TakeoutOrder> objects) {
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

        bussinessNameText.setText(takeoutOrder.getBussinessName());
        foodSummaryText.setText(takeoutOrder.getFirstFoodName()+"等"+takeoutOrder.getItemCount()+"个商品");
        priceText.setText(String.valueOf("￥"+takeoutOrder.getTotalPrice()));


        return item;
    }
}
