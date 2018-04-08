package org.androidpn.IQprovider;

import android.util.Log;

import org.androidpn.IQ.CommentsIQ;
import org.androidpn.model.Bussiness;
import org.androidpn.model.Comment;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macpro on 2018/4/4.
 */

public class CommentsIQProvider implements IQProvider {
    @Override
    public IQ parseIQ(XmlPullParser parser) throws Exception {
        CommentsIQ commentsIQ = new CommentsIQ();

        List<Comment> commentList = new ArrayList<Comment>();
        for (boolean done = false; !done; ) {
            int eventType = parser.next();
            if (eventType == 2) {
                Comment comment = new Comment();
                for(int i=0; i<parser.getAttributeCount(); i++) {
                    if("id".equals(parser.getAttributeName(i))) {
                        comment.setCommentId(Long.parseLong(parser.getAttributeValue(i)));
                    }
                    if("username".equals(parser.getAttributeName(i))) {
                        comment.setUserName(parser.getAttributeValue(i));
                    }
                    if("content".equals(parser.getAttributeName(i))) {
                        comment.setContent(parser.getAttributeValue(i));
                    }
                }
                commentList.add(comment);
            } else if (eventType == 3
                    && "comment".equals(parser.getName())) {
                done = true;
            }
        }

        commentsIQ.setCommentList(commentList);

        return commentsIQ;
    }
}
