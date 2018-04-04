package org.androidpn.IQ;

import org.androidpn.model.Comment;
import org.jivesoftware.smack.packet.IQ;

import java.util.List;

/**
 * Created by macpro on 2018/4/4.
 */

public class CommentsIQ extends IQ {

    private List<Comment> commentList;

    @Override
    public String getChildElementXML() {
        return null;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
