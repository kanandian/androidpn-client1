package org.androidpn.adapter;

import java.math.BigDecimal;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.androidpn.demoapp.R;
import org.androidpn.info.ShopInfo;
import org.androidpn.model.Model;
import org.androidpn.utils.LoadImg;
import org.androidpn.utils.Location;
import org.androidpn.utils.LocationHolder;
import org.androidpn.utils.VolleyUtil;

/**
 * �����б��������
 * @author ��ɬ
 *
 */

public class ShopAdapter extends BaseAdapter {

	private List<ShopInfo> list;
	private Context ctx;
	private LoadImg loadImg;

	public ShopAdapter(List<ShopInfo> list, Context ctx) {
		this.list = list;
		this.ctx = ctx;
		// ʵ������ȡͼƬ����
		loadImg = new LoadImg(ctx);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup viewGroup) {
		final Holder hold;
		if (view == null) {
			hold = new Holder();
			view = View.inflate(ctx, R.layout.item_shop, null);
			hold.mTitle = (TextView) view.findViewById(R.id.ShopItemTextView);
			hold.mImage = (NetworkImageView) view.findViewById(R.id.ShopItemImage);
			hold.mMoney = (TextView) view.findViewById(R.id.ShopItemMoney);
			hold.mAddress = (TextView) view.findViewById(R.id.ShopItemAddress);
			hold.mStytle = (TextView) view.findViewById(R.id.ShopItemStytle);
			hold.mStar = (ImageView) view.findViewById(R.id.ShopItemStar);
			hold.mDistance = (TextView) view.findViewById(R.id.ShopItemJuli);

			hold.mTuan = (ImageView) view.findViewById(R.id.ShopItemTuan);
			hold.mQuan = (ImageView) view.findViewById(R.id.ShopItemQuan);
			hold.mDing = (ImageView) view.findViewById(R.id.ShopItemDing);
			hold.mCard = (ImageView) view.findViewById(R.id.ShopItemCard);

			view.setTag(hold);
		} else {
			hold = (Holder) view.getTag();
		}

		ShopInfo shopInfo = list.get(position);

		hold.mTitle.setText(shopInfo.getSname());
		hold.mImage.setTag(Model.SHOPLISTIMGURL + shopInfo.getIname());
		hold.mMoney.setText(shopInfo.getSmoney());
		hold.mAddress.setText(shopInfo.getSaddress());
		hold.mStytle.setText(shopInfo.getStype());

		Location location = LocationHolder.getInstance().getLocation();
		if (location != null) {
			LatLng latLng1 = new LatLng(location.getLatitude(), location.getLongitude());
			LatLng latLng2 = new LatLng(shopInfo.getLatitude(), shopInfo.getLongitude());
			float distance = AMapUtils.calculateLineDistance(latLng1,latLng2);
			String distanceStr = "";
			if (distance < 1000) {
				distanceStr = ((int) distance)+"米";
			} else {

				int   scale  =   1;//设置位数
				int   roundingMode  =  4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
				BigDecimal bd  =   new  BigDecimal((double)distance/1000);
				bd   =  bd.setScale(scale,roundingMode);
				distance   =  bd.floatValue();
				distanceStr = distance+"千米";

			}
			hold.mDistance.setText(distanceStr);

		}

		hold.mStar.setImageResource(shopInfo.getStarImageResource());

		hold.mTuan.setVisibility(View.GONE);
		hold.mQuan.setVisibility(View.GONE);
		hold.mDing.setVisibility(View.GONE);
		hold.mCard.setVisibility(View.GONE);

//		if (list.get(arg0).getSflag_tuan().equals("1")) {
//			hold.mTuan.setVisibility(View.VISIBLE);
//		}
//		if (list.get(arg0).getSflag_quan().equals("1")) {
//			hold.mQuan.setVisibility(View.VISIBLE);
//		}
//		if (list.get(arg0).getSflag_ding().equals("1")) {
//			hold.mDing.setVisibility(View.VISIBLE);
//		}
//		if (list.get(arg0).getSflag_ka().equals("1")) {
//			hold.mCard.setVisibility(View.VISIBLE);
//		}

//		int slevel = Integer.valueOf(list.get(arg0).getSlevel());
//		switch (slevel) {
//		case 0:
//			hold.mStar.setImageResource(R.drawable.star0);
//			break;
//		case 1:
//			hold.mStar.setImageResource(R.drawable.star1);
//			break;
//		case 2:
//			hold.mStar.setImageResource(R.drawable.star2);
//			break;
//		case 3:
//			hold.mStar.setImageResource(R.drawable.star3);
//			break;
//		case 4:
//			hold.mStar.setImageResource(R.drawable.star4);
//			break;
//		case 5:
//			hold.mStar.setImageResource(R.drawable.star5);
//			break;
//		}

		// ����Ĭ����ʾ��ͼƬ
//		hold.mImage.setImageResource(R.drawable.shop_photo_frame);
		// �����ȡͼƬ
//		Bitmap bit = loadImg.loadImage(hold.mImage, Model.SHOPLISTIMGURL
//				+ list.get(position).getIname(), new LoadImg.ImageDownloadCallBack() {
//			@Override
//			public void onImageDownload(ImageView imageView, Bitmap bitmap) {
//				if (hold.mImage.getTag().equals(
//						Model.SHOPLISTIMGURL + list.get(position).getIname())) {
//					hold.mImage.setImageBitmap(bitmap);
//				}
//			}
//		});
//		if (bit != null) {
//			hold.mImage.setImageBitmap(bit);
//		}


		ImageLoader imageLoader = new ImageLoader(VolleyUtil.getInstance().getRequestQueue(), new ImageLoader.ImageCache() {
			@Override
			public Bitmap getBitmap(String url) {
				return null;
			}

			@Override
			public void putBitmap(String url, Bitmap bitmap) {

			}
		});
		hold.mImage.setImageUrl(shopInfo.getIname(), imageLoader);

		return view;
	}

	static class Holder {
		TextView mTitle, mMoney, mAddress, mStytle, mDistance;
		ImageView mStar, mTuan, mQuan, mDing, mCard;
		NetworkImageView mImage;
	}

}
