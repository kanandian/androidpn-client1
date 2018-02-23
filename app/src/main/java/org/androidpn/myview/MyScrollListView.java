package org.androidpn.myview;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * ǩ��ǽ���Զ���ScrollView��������ʾ����listView��listview�����ý���
 * ��˳�����listview�е���������ʾ����
 * @author ��ɬ
 *</BR> </BR> By����ɬ </BR> ��ϵ���ߣ�QQ 534429149
 */

public class MyScrollListView extends ScrollView {


	private ListView left;
	private ListView right;
	private LinearLayout innerLayout;

	public MyScrollListView(Context context,
			ListView left,
			ListView right) {
		super(context);
		this.left = left;
		this.right = right;
		initView(context);
	}

	private void initView(Context context){
		innerLayout = new LinearLayout(context);
		innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		innerLayout.setOrientation(LinearLayout.HORIZONTAL);
		setListViewHeightBasedOnChildren(left);
		setListViewHeightBasedOnChildren(right);
		innerLayout.addView(left);
		innerLayout.addView(right);
		addView(innerLayout);
	}

	//��ȡlistview�߶�(��һ��item�߶��ۼ�)
	public static void setListViewHeightBasedOnChildren(ListView listView)
	{
		//��ȡlistview��������
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
		{
			return;
		}

		int totalHeight = 0;//listview����ĸ߶�
		//��ȡ�������е�itemview
		for (int i = 0, len = listAdapter.getCount(); i < len; i++)
		{
			//i:�ڼ���itemview
			//null�Ƿ���Ҫ����һ���µ�view
			//listview�ĸ�viewgroup���е�
			View listItem = listAdapter.getView(i, null, listView);
			//������Լ��
			listItem.measure(0, 0);
			//getHeight()xml�ڻ��Ƶ������ʹ�û�ȡ�߶�(��ƫ����ڸ�����Լ��)
			//getMeasuredHeight() ��ȡxmlʵ�ʸ߶�(����xml����ǰ�ɵõ�)
			totalHeight += listItem.getMeasuredHeight();
		}
		//��viewͨ��LayoutParams�����߸�����������Ҫ�Ŀ��
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		//������view��Ȩ��
		params.weight = 1;
		//������listview���зָ��ߵĸ߶������ȥ
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + listView.getPaddingTop() + listView.getPaddingBottom();
		//���ü���õ�listview�ĸ߶�
		listView.setLayoutParams(params);
	}
}
