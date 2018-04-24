package org.androidpn.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        return super.getView(position, convertView, parent);
    }
}
