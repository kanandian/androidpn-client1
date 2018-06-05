// Generated code from Butter Knife. Do not modify!
package org.androidpn.demoapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FullImageActivity_ViewBinding implements Unbinder {
  private FullImageActivity target;

  private View view2131231025;

  @UiThread
  public FullImageActivity_ViewBinding(FullImageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FullImageActivity_ViewBinding(final FullImageActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.full_image, "method 'onClick'");
    view2131231025 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131231025.setOnClickListener(null);
    view2131231025 = null;
  }
}
