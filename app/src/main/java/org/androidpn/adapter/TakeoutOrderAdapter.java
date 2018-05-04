package org.androidpn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidpn.demoapp.R;
import org.androidpn.demoapp.TakeOutActivity;
import org.androidpn.model.FoodMenuItem;
import org.androidpn.model.FoodOrderItem;

import java.util.List;

public class TakeoutOrderAdapter extends ArrayAdapter<FoodOrderItem> {

    private final int resource;

    public TakeoutOrderAdapter(Context context, int resource, List<FoodOrderItem> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodOrderItem foodOrderItem = getItem(position);
        LinearLayout item = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        vi.inflate(resource, item, true);

        TextView foodNameText = (TextView) item.findViewById(R.id.text_food_name);
        TextView countText = (TextView) item.findViewById(R.id.text_count);
        TextView priceText = (TextView) item.findViewById(R.id.text_price);

        foodNameText.setText(foodOrderItem.getFoodName());
        countText.setText(String.valueOf(foodOrderItem.getCount()));
        priceText.setText(String.valueOf(foodOrderItem.getPrice()*foodOrderItem.getCount()));

        return item;
    }
}
