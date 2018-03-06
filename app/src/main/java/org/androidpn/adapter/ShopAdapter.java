package org.androidpn.adapter;

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

import org.androidpn.demoapp.R;
import org.androidpn.info.ShopInfo;
import org.androidpn.model.Model;
import org.androidpn.utils.LoadImg;

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
			hold.mImage = (ImageView) view.findViewById(R.id.ShopItemImage);
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
		hold.mImage.setImageResource(R.drawable.shop_photo_frame);
		// �����ȡͼƬ
		Bitmap bit = loadImg.loadImage(hold.mImage, Model.SHOPLISTIMGURL
				+ list.get(position).getIname(), new LoadImg.ImageDownloadCallBack() {
			@Override
			public void onImageDownload(ImageView imageView, Bitmap bitmap) {
				// ���罻��ʱ�ص�������ֹ��λ
				if (hold.mImage.getTag().equals(
						Model.SHOPLISTIMGURL + list.get(position).getIname())) {
					// �����������ػ���ͼƬ��ʾ
					hold.mImage.setImageBitmap(bitmap);
				}
			}
		});
		// �ӱ��ػ�ȡ��
		if (bit != null) {
			// ���û���ͼƬ��ʾ
			hold.mImage.setImageBitmap(bit);
		}

		return view;
	}

	static class Holder {
		TextView mTitle, mMoney, mAddress, mStytle, mDistance;
		ImageView mImage, mStar, mTuan, mQuan, mDing, mCard;
	}

}
