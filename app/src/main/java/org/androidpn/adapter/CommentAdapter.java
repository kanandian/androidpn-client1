package org.androidpn.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.androidpn.demoapp.R;
import org.androidpn.model.Bussiness;
import org.androidpn.model.Comment;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.MessageFormatUtil;
import org.androidpn.utils.NetworkImageUtil;
import org.androidpn.utils.VolleyUtil;

import java.util.List;

/**
 * Created by macpro on 2018/4/8.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {

    private int resource;


    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = getItem(position);
        LinearLayout item = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        vi.inflate(resource, item, true);

        NetworkImageView personImg = (NetworkImageView) item.findViewById(R.id.img_user);
        TextView content = (TextView) item.findViewById(R.id.item_comment_content);
        TextView username = (TextView) item.findViewById(R.id.text_username);
        TextView createtime = (TextView) item.findViewById(R.id.text_createtime);
        ImageView starImg = (ImageView) item.findViewById(R.id.img_star);


        String imageURL = comment.getImageURL();
        NetworkImageUtil.requestImage(personImg, imageURL);
        starImg.setImageResource(MessageFormatUtil.getStarImage(comment.getStar()));


        content.setText("   "+comment.getContent());
        username.setText(comment.getUserName());
        createtime.setText(comment.getCreateTime());

        return item;

    }
}
