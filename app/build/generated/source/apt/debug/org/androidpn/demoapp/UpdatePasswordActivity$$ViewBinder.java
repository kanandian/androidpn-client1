// Generated code from Butter Knife. Do not modify!
package org.androidpn.demoapp;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UpdatePasswordActivity$$ViewBinder<T extends org.androidpn.demoapp.UpdatePasswordActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231091, "field 'opasswordEdit'");
    target.opasswordEdit = finder.castView(view, 2131231091, "field 'opasswordEdit'");
    view = finder.findRequiredView(source, 2131231088, "field 'npasswordEdit'");
    target.npasswordEdit = finder.castView(view, 2131231088, "field 'npasswordEdit'");
    view = finder.findRequiredView(source, 2131230972, "field 'cpasswordEdit'");
    target.cpasswordEdit = finder.castView(view, 2131230972, "field 'cpasswordEdit'");
    view = finder.findRequiredView(source, 2131230946, "field 'updateBtn'");
    target.updateBtn = finder.castView(view, 2131230946, "field 'updateBtn'");
  }

  @Override public void unbind(T target) {
    target.opasswordEdit = null;
    target.npasswordEdit = null;
    target.cpasswordEdit = null;
    target.updateBtn = null;
  }
}
