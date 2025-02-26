// Generated by view binder compiler. Do not edit!
package ru.volga.online.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ru.volga.online.R;

public final class DialogSettingsWeaponsBinding implements ViewBinding {
  @NonNull
  private final LinearLayoutCompat rootView;

  @NonNull
  public final SeekBar hudElementPosX12;

  @NonNull
  public final SeekBar hudElementPosX13;

  @NonNull
  public final SeekBar hudElementPosY12;

  @NonNull
  public final SeekBar hudElementPosY13;

  @NonNull
  public final SeekBar hudElementScaleY12;

  @NonNull
  public final SeekBar hudElementScaleY13;

  @NonNull
  public final LinearLayoutCompat settingsRootLayout;

  private DialogSettingsWeaponsBinding(@NonNull LinearLayoutCompat rootView,
      @NonNull SeekBar hudElementPosX12, @NonNull SeekBar hudElementPosX13,
      @NonNull SeekBar hudElementPosY12, @NonNull SeekBar hudElementPosY13,
      @NonNull SeekBar hudElementScaleY12, @NonNull SeekBar hudElementScaleY13,
      @NonNull LinearLayoutCompat settingsRootLayout) {
    this.rootView = rootView;
    this.hudElementPosX12 = hudElementPosX12;
    this.hudElementPosX13 = hudElementPosX13;
    this.hudElementPosY12 = hudElementPosY12;
    this.hudElementPosY13 = hudElementPosY13;
    this.hudElementScaleY12 = hudElementScaleY12;
    this.hudElementScaleY13 = hudElementScaleY13;
    this.settingsRootLayout = settingsRootLayout;
  }

  @Override
  @NonNull
  public LinearLayoutCompat getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogSettingsWeaponsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogSettingsWeaponsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_settings_weapons, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogSettingsWeaponsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.hud_element_pos_x_12;
      SeekBar hudElementPosX12 = ViewBindings.findChildViewById(rootView, id);
      if (hudElementPosX12 == null) {
        break missingId;
      }

      id = R.id.hud_element_pos_x_13;
      SeekBar hudElementPosX13 = ViewBindings.findChildViewById(rootView, id);
      if (hudElementPosX13 == null) {
        break missingId;
      }

      id = R.id.hud_element_pos_y_12;
      SeekBar hudElementPosY12 = ViewBindings.findChildViewById(rootView, id);
      if (hudElementPosY12 == null) {
        break missingId;
      }

      id = R.id.hud_element_pos_y_13;
      SeekBar hudElementPosY13 = ViewBindings.findChildViewById(rootView, id);
      if (hudElementPosY13 == null) {
        break missingId;
      }

      id = R.id.hud_element_scale_y_12;
      SeekBar hudElementScaleY12 = ViewBindings.findChildViewById(rootView, id);
      if (hudElementScaleY12 == null) {
        break missingId;
      }

      id = R.id.hud_element_scale_y_13;
      SeekBar hudElementScaleY13 = ViewBindings.findChildViewById(rootView, id);
      if (hudElementScaleY13 == null) {
        break missingId;
      }

      LinearLayoutCompat settingsRootLayout = (LinearLayoutCompat) rootView;

      return new DialogSettingsWeaponsBinding((LinearLayoutCompat) rootView, hudElementPosX12,
          hudElementPosX13, hudElementPosY12, hudElementPosY13, hudElementScaleY12,
          hudElementScaleY13, settingsRootLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
