// Generated code from Butter Knife. Do not modify!
package org.androidpn.demoapp;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ForgotPasswordActivity$$ViewBinder<T extends org.androidpn.demoapp.ForgotPasswordActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131231002, "field 'mobileEdit'");
    target.mobileEdit = finder.castView(view, 2131231002, "field 'mobileEdit'");
    view = finder.findRequiredView(source, 2131231012, "field 'vcodeEdit'");
    target.vcodeEdit = finder.castView(view, 2131231012, "field 'vcodeEdit'");
    view = finder.findRequiredView(source, 2131231005, "field 'npasswordEdit'");
    target.npasswordEdit = finder.castView(view, 2131231005, "field 'npasswordEdit'");
    view = finder.findRequiredView(source, 2131230997, "field 'cpasswordEdit'");
    target.cpasswordEdit = finder.castView(view, 2131230997, "field 'cpasswordEdit'");
    view = finder.findRequiredView(source, 2131230930, "field 'getVCodeBtn'");
    target.getVCodeBtn = finder.castView(view, 2131230930, "field 'getVCodeBtn'");
    view = finder.findRequiredView(source, 2131230946, "field 'udpateButton'");
    target.udpateButton = finder.castView(view, 2131230946, "field 'udpateButton'");
  }

  @Override public void unbind(T target) {
    target.mobileEdit = null;
    target.vcodeEdit = null;
    target.npasswordEdit = null;
    target.cpasswordEdit = null;
    target.getVCodeBtn = null;
    target.udpateButton = null;
  }
}
