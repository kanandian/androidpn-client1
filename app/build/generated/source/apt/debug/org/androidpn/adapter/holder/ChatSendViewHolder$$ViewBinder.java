// Generated code from Butter Knife. Do not modify!
package org.androidpn.adapter.holder;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatSendViewHolder$$ViewBinder<T extends org.androidpn.adapter.holder.ChatSendViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230962, "field 'chatItemDate'");
    target.chatItemDate = finder.castView(view, 2131230962, "field 'chatItemDate'");
    view = finder.findRequiredView(source, 2131230964, "field 'chatItemHeader'");
    target.chatItemHeader = finder.castView(view, 2131230964, "field 'chatItemHeader'");
    view = finder.findRequiredView(source, 2131230961, "field 'chatItemContentText'");
    target.chatItemContentText = finder.castView(view, 2131230961, "field 'chatItemContentText'");
    view = finder.findRequiredView(source, 2131230960, "field 'chatItemContentImage'");
    target.chatItemContentImage = finder.castView(view, 2131230960, "field 'chatItemContentImage'");
    view = finder.findRequiredView(source, 2131230963, "field 'chatItemFail'");
    target.chatItemFail = finder.castView(view, 2131230963, "field 'chatItemFail'");
    view = finder.findRequiredView(source, 2131230967, "field 'chatItemProgress'");
    target.chatItemProgress = finder.castView(view, 2131230967, "field 'chatItemProgress'");
    view = finder.findRequiredView(source, 2131230968, "field 'chatItemVoice'");
    target.chatItemVoice = finder.castView(view, 2131230968, "field 'chatItemVoice'");
    view = finder.findRequiredView(source, 2131230966, "field 'chatItemLayoutContent'");
    target.chatItemLayoutContent = finder.castView(view, 2131230966, "field 'chatItemLayoutContent'");
    view = finder.findRequiredView(source, 2131230969, "field 'chatItemVoiceTime'");
    target.chatItemVoiceTime = finder.castView(view, 2131230969, "field 'chatItemVoiceTime'");
  }

  @Override public void unbind(T target) {
    target.chatItemDate = null;
    target.chatItemHeader = null;
    target.chatItemContentText = null;
    target.chatItemContentImage = null;
    target.chatItemFail = null;
    target.chatItemProgress = null;
    target.chatItemVoice = null;
    target.chatItemLayoutContent = null;
    target.chatItemVoiceTime = null;
  }
}
