// Generated code from Butter Knife. Do not modify!
package org.androidpn.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatEmotionFragment$$ViewBinder<T extends org.androidpn.fragment.ChatEmotionFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231031, "field 'fragmentChatVp'");
    target.fragmentChatVp = finder.castView(view, 2131231031, "field 'fragmentChatVp'");
    view = finder.findRequiredView(source, 2131231030, "field 'fragmentChatGroup'");
    target.fragmentChatGroup = finder.castView(view, 2131231030, "field 'fragmentChatGroup'");
  }

  @Override public void unbind(T target) {
    target.fragmentChatVp = null;
    target.fragmentChatGroup = null;
  }
}
