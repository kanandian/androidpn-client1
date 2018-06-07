// Generated code from Butter Knife. Do not modify!
package org.androidpn.demoapp;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatActivity$$ViewBinder<T extends org.androidpn.demoapp.ChatActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230971, "field 'chatList'");
    target.chatList = finder.castView(view, 2131230971, "field 'chatList'");
    view = finder.findRequiredView(source, 2131231010, "field 'editText'");
    target.editText = finder.castView(view, 2131231010, "field 'editText'");
    view = finder.findRequiredView(source, 2131230940, "field 'sendButton'");
    target.sendButton = finder.castView(view, 2131230940, "field 'sendButton'");
  }

  @Override public void unbind(T target) {
    target.chatList = null;
    target.editText = null;
    target.sendButton = null;
  }
}
