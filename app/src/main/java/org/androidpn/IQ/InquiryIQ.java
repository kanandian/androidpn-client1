package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

import java.io.Serializable;

public class InquiryIQ extends IQ implements Serializable {
	
	private String target = null;
	private String title = null;
	private String content = null;
	private String userName = null;
	private String location = null;
	private int status = -1;

	public InquiryIQ(){
		
	}

	@Override
	public String getChildElementXML() {
		StringBuilder buf = new StringBuilder();
        buf.append("<").append("inquiry").append(" xmlns=\"").append(
                "androidpn:iq:inquiry").append("\">");
        if(target != null){
        	buf.append("<target>").append(target).append("</target>");
        }
        if(title != null){
        	buf.append("<title>").append(title).append("</title>");
        }
        if (content != null) {
			buf.append("<content>").append(content).append("</content>");
		}
		if (userName != null) {
			buf.append("<username>").append(userName).append("</username>");
		}
		if (location != null) {
			buf.append("<location>").append(location).append("</location>");
		}
		if (status != -1) {
			buf.append("<status>").append(status).append("</status>");
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
