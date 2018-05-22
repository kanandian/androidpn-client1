package org.androidpn.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import org.androidpn.model.Contact;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.DateFormatUtil;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private int resource;
    private RequestQueue requestQueue;
    ImageLoader imageLoader;

    public ContactAdapter(Context context, List<Contact> contactList) {
        super(context, R.layout.item_my_message, contactList);
        this.resource = R.layout.item_my_message;
        requestQueue = Volley.newRequestQueue(getContext());
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        LinearLayout item = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        vi.inflate(resource, item, true);

        NetworkImageView imageView = (NetworkImageView) item.findViewById(R.id.img_user);
        TextView userText = (TextView) item.findViewById(R.id.text_contact_name);
        TextView timeText = (TextView) item.findViewById(R.id.text_time);

        if (contact.getImageURL() != null) {
            imageView.setImageUrl(contact.getImageURL().replace("localhost", ActivityHolder.getInstance().getConnection().getHost()), imageLoader);
        }
        userText.setText(contact.getFromUserName());
        timeText.setText(DateFormatUtil.getFormatTime(contact.getCreateTime()));


        return item;
    }
}
