// Generated code from Butter Knife. Do not modify!
package org.androidpn.demoapp;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UpdateBussinessInfoActivity$$ViewBinder<T extends org.androidpn.demoapp.UpdateBussinessInfoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230948, "field 'bussinessFeature'");
    target.bussinessFeature = finder.castView(view, 2131230948, "field 'bussinessFeature'");
    view = finder.findRequiredView(source, 2131231204, "field 'startTimeText'");
    target.startTimeText = finder.castView(view, 2131231204, "field 'startTimeText'");
    view = finder.findRequiredView(source, 2131231019, "field 'endTimeText'");
    target.endTimeText = finder.castView(view, 2131231019, "field 'endTimeText'");
    view = finder.findRequiredView(source, 2131231000, "field 'mobileEdit'");
    target.mobileEdit = finder.castView(view, 2131231000, "field 'mobileEdit'");
    view = finder.findRequiredView(source, 2131230996, "field 'desEdit'");
    target.desEdit = finder.castView(view, 2131230996, "field 'desEdit'");
    view = finder.findRequiredView(source, 2131230942, "field 'updateButton'");
    target.updateButton = finder.castView(view, 2131230942, "field 'updateButton'");
  }

  @Override public void unbind(T target) {
    target.bussinessFeature = null;
    target.startTimeText = null;
    target.endTimeText = null;
    target.mobileEdit = null;
    target.desEdit = null;
    target.updateButton = null;
  }
}