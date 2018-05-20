package org.androidpn.IQ;

import org.jivesoftware.smack.packet.IQ;

public class CommentIQ extends IQ {

    private String bussinessId;
    private String fromUserName;
    private String content;
    private String star;
    private String amount;
    private String imageURL;

    public CommentIQ() {
    }

    @Override
    public String getChildElementXML() {
        StringBuilder buf = new StringBuilder();
        buf.append("<").append("comment").append(" xmlns=\"").append(
                "androidpn:comment:admin").append("\">");
        if(bussinessId != null){
            buf.append("<bussinessid>").append(bussinessId).append("</bussinessid>");
        }
        if(fromUserName != null){
            buf.append("<fromusername>").append(fromUserName).append("</fromusername>");
        }
        if (content != null) {
            buf.append("<content>").append(content).append("</content>");
        }
        if (star != null) {
            buf.append("<star>").append(star).append("</star>");
        }
        if (amount != null) {
            buf.append("<amount>").append(amount).append("</amount>");
        }
        if (imageURL != null) {
            buf.append("<imageurl>").append(imageURL).append("</imageurl>");
        }
        buf.append("</").append("comment").append("> ");
        return buf.toString();
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
