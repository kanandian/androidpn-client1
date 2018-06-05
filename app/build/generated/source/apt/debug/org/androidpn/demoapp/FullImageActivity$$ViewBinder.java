// Generated code from Butter Knife. Do not modify!
package org.androidpn.demoapp;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FullImageActivity$$ViewBinder<T extends org.androidpn.demoapp.FullImageActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231032, "field 'fullImage' and method 'onClick'");
    target.fullImage = finder.castView(view, 2131231032, "field 'fullImage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
    view = finder.findRequiredView(source, 2131231033, "field 'fullLay'");
    target.fullLay = finder.castView(view, 2131231033, "field 'fullLay'");
  }

  @Override public void unbind(T target) {
    target.fullImage = null;
    target.fullLay = null;
  }
}
