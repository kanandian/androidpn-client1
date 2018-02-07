package org.androidpn.client;

import org.jivesoftware.smack.packet.IQ;

public class InquiryIQ extends IQ {
	
	private String target = null;
	private String title = null;

	public InquiryIQ(){
		
	}

	@Override
	public String getChildElementXML() {
		// TODO Auto-generated method stub
		StringBuilder buf = new StringBuilder();
        buf.append("<").append("inquiry").append(" xmlns=\"").append(
                "androidpn:iq:inquiry").append("\">");
        if(target != null){
        	buf.append("<target>").append(target).append("</target>");
        }
        if(title != null){
        	buf.append("<title>").append(title).append("</title>");
        }
        buf.append("</").append("inquiry").append("> ");
        return buf.toString();
	}


	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
