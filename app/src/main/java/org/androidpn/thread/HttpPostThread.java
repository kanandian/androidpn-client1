package org.androidpn.thread;

import android.os.Handler;
import android.os.Message;

import org.androidpn.net.MyPost;

/**
 * ����Post������߳�
 * * 
 * </BR> </BR> By����ɬ </BR> ��ϵ���ߣ�QQ 534429149
 * */

public class HttpPostThread implements Runnable {

	private Handler hand;
	private String url;
	private String mycode;
	private String value;
	private MyPost myGet = new MyPost();
	public HttpPostThread(Handler hand, String endParamerse, String mycode, String value){
		this.hand = hand;
		//ƴ�ӷ��ʷ����������ĵ�ַ
		url = endParamerse;
		this.mycode = mycode;
		this.value = value;
	}

	@Override
	public void run() {
		//��ȡ���ǻص���ui��message
		Message msg = hand.obtainMessage();
		String result = myGet.doPost(url, mycode, value);
		msg.what = 200;
		msg.obj = result;
		//����ui������Ϣ��������
		hand.sendMessage(msg);

	}

}
