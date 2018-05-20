package org.androidpn.utils;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class NetworkImageUtil {

    public static void requestImage(NetworkImageView imageView, String imageURL) {
        if (imageURL != null && !"".equals(imageURL)) {
            ImageLoader imageLoader = new ImageLoader(VolleyUtil.getInstance().getRequestQueue(), new ImageLoader.ImageCache() {
                @Override
                public Bitmap getBitmap(String url) {
                    return null;
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {

                }
            });
            imageView.setImageUrl(imageURL.replace("localhost", ActivityHolder.getInstance().getConnection().getHost()), imageLoader);
        }
    }

}
