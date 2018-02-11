package org.androidpn.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.androidpn.demoapp.R;
import org.androidpn.model.Bussiness;

import java.util.List;

/**
 * Created by pro1 on 18/2/11.
 */

public class BussinessAdapter extends ArrayAdapter<Bussiness> {
    private int resource;
    private RequestQueue requestQueue;
    ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
        @Override
        public void putBitmap(String url, Bitmap bitmap) {
        }

        @Override
        public Bitmap getBitmap(String url) {
            return null;
        }
    });

    public BussinessAdapter(Context context, int resource, List<Bussiness> objects) {
        super(context, resource, objects);
        this.resource = resource;
        requestQueue = Volley.newRequestQueue(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bussiness bussiness = getItem(position);
        LinearLayout item = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        vi.inflate(resource, item, true);

        NetworkImageView bussinessImage = (NetworkImageView) item.findViewById(R.id.image_bussiness);
        TextView bussinessName = (TextView) item.findViewById(R.id.text_bussiness_name);
        TextView tags = (TextView) item.findViewById(R.id.text_tags);
        TextView distance = (TextView) item.findViewById(R.id.text_distance);

        bussinessImage.setImageUrl(bussiness.getImageURL(), imageLoader);
        bussinessName.setText(bussiness.getBussinessName());
        tags.setText(bussiness.getTag());
        distance.setText(bussiness.getLocation().toString());

        return item;
    }
}
