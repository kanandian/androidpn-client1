package org.androidpn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidpn.demoapp.R;
import org.androidpn.demoapp.TakeOutActivity;
import org.androidpn.model.Comment;
import org.androidpn.model.FoodMenuItem;
import org.w3c.dom.Text;

import java.util.List;

public class FoodMenuAdapter extends ArrayAdapter<FoodMenuItem> {

    private TakeOutActivity takeOutActivity;
    private final int resource;

    public FoodMenuAdapter(Context context, int resource, List<FoodMenuItem> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.takeOutActivity = (TakeOutActivity) context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FoodMenuItem foodMenuItem = getItem(position);
        RelativeLayout item = new RelativeLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        vi.inflate(resource, item, true);

        TextView foodNameText = (TextView) item.findViewById(R.id.text_food_name);
        TextView foodPriceText = (TextView) item.findViewById(R.id.text_food_price);

        final TextView countText = (TextView) item.findViewById(R.id.item_count);

        ImageView addButton = (ImageView) item.findViewById(R.id.btn_add);
        final ImageView subButton = (ImageView) item.findViewById(R.id.btn_sub);

        foodNameText.setText(foodMenuItem.getFoodName());
        foodPriceText.setText(String.valueOf(foodMenuItem.getPrice()));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeOutActivity.addFood(position);

                int count = Integer.parseInt(String.valueOf(countText.getText()));
                countText.setText(String.valueOf(count+1));
                countText.setVisibility(View.VISIBLE);
                subButton.setVisibility(View.VISIBLE);
            }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeOutActivity.subFood(position);

                int count = Integer.parseInt(String.valueOf(countText.getText()));
                if (count <= 1) {
                    countText.setText("0");
                    countText.setVisibility(View.INVISIBLE);
                    subButton.setVisibility(View.INVISIBLE);
                } else {
                    countText.setText(String.valueOf(count-1));
                }
            }
        });


        return item;

    }

}
