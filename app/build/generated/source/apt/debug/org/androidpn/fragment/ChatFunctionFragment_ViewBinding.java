// Generated code from Butter Knife. Do not modify!
package org.androidpn.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.androidpn.demoapp.R;

public class ChatFunctionFragment_ViewBinding implements Unbinder {
  private ChatFunctionFragment target;

  private View view2131230955;

  private View view2131230956;

  @UiThread
  public ChatFunctionFragment_ViewBinding(final ChatFunctionFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.chat_function_photo, "method 'onClick'");
    view2131230955 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.chat_function_photograph, "method 'onClick'");
    view2131230956 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230955.setOnClickListener(null);
    view2131230955 = null;
    view2131230956.setOnClickListener(null);
    view2131230956 = null;
  }
}
