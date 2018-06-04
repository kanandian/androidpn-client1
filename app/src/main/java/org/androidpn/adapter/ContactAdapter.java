package org.androidpn.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.simple.bubbleviewlibrary.BubbleView;

import org.androidpn.demoapp.R;
import org.androidpn.model.Bussiness;
import org.androidpn.model.Contact;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.DateFormatUtil;
import org.litepal.crud.DataSupport;

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
        final Contact contact = getItem(position);
        final LinearLayout item = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        vi.inflate(resource, item, true);

        NetworkImageView imageView = (NetworkImageView) item.findViewById(R.id.img_user);
        TextView userText = (TextView) item.findViewById(R.id.text_contact_name);
        TextView contentText = (TextView) item.findViewById(R.id.text_contact_content);
        TextView timeText = (TextView) item.findViewById(R.id.text_time);
        BubbleView bubbleView = (BubbleView) item.findViewById(R.id.bubbleView);


        if (contact.getImageURL() != null) {
            imageView.setImageUrl(contact.getImageURL().replace("localhost", ActivityHolder.getInstance().getConnection().getHost()), imageLoader);
        }
        userText.setText(contact.getFromUserName());
        contentText.setText(contact.getLastUnRead());
        timeText.setText(DateFormatUtil.getFormatTime(contact.getCreateTime()));

        bubbleView.setText(String.valueOf(contact.getUnReadCount()));
        bubbleView.setTextColor(Color.WHITE);
        bubbleView.setCircleColor(Color.RED);

        if (contact.getUnReadCount() == 0) {
            bubbleView.setVisibility(View.GONE);
        }

        bubbleView.setOnAnimationEndListener(new BubbleView.OnAnimationEndListener() {
            @Override
            public void onEnd(BubbleView bubbleView) {
//                Toast.makeText(ActivityHolder.getInstance().getCurrentActivity(), contact.getUnReadCount(), Toast.LENGTH_LONG).show();
//                Contact updateContact = new Contact();
//                updateContact.setUnReadCount(0);
//                updateContact.update(contact.getId());
                ContentValues contentValues = new ContentValues();
                contentValues.put("unreadcount", 0);
                DataSupport.update(Contact.class, contentValues, contact.getId());
                bubbleView.setVisibility(View.GONE);
            }
        });


        return item;
    }
}
