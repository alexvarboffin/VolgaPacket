// Generated by view binder compiler. Do not edit!
package ru.volga.online.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ru.volga.online.R;

public final class DialogSettingsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatButton dialogSettingsButtonClose;

  @NonNull
  public final AppCompatButton dialogSettingsButtonReset;

  @NonNull
  public final LinearLayout llSettingsRoot;

  @NonNull
  public final ViewPager masterViewPager;

  @NonNull
  public final TabLayout tabLayout;

  private DialogSettingsBinding(@NonNull LinearLayout rootView,
      @NonNull AppCompatButton dialogSettingsButtonClose,
      @NonNull AppCompatButton dialogSettingsButtonReset, @NonNull LinearLayout llSettingsRoot,
      @NonNull ViewPager masterViewPager, @NonNull TabLayout tabLayout) {
    this.rootView = rootView;
    this.dialogSettingsButtonClose = dialogSettingsButtonClose;
    this.dialogSettingsButtonReset = dialogSettingsButtonReset;
    this.llSettingsRoot = llSettingsRoot;
    this.masterViewPager = masterViewPager;
    this.tabLayout = tabLayout;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogSettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogSettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogSettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dialog_settings_button_close;
      AppCompatButton dialogSettingsButtonClose = ViewBindings.findChildViewById(rootView, id);
      if (dialogSettingsButtonClose == null) {
        break missingId;
      }

      id = R.id.dialog_settings_button_reset;
      AppCompatButton dialogSettingsButtonReset = ViewBindings.findChildViewById(rootView, id);
      if (dialogSettingsButtonReset == null) {
        break missingId;
      }

      LinearLayout llSettingsRoot = (LinearLayout) rootView;

      id = R.id.masterViewPager;
      ViewPager masterViewPager = ViewBindings.findChildViewById(rootView, id);
      if (masterViewPager == null) {
        break missingId;
      }

      id = R.id.tabLayout;
      TabLayout tabLayout = ViewBindings.findChildViewById(rootView, id);
      if (tabLayout == null) {
        break missingId;
      }

      return new DialogSettingsBinding((LinearLayout) rootView, dialogSettingsButtonClose,
          dialogSettingsButtonReset, llSettingsRoot, masterViewPager, tabLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
