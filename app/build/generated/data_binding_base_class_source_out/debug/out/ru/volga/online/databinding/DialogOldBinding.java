// Generated by view binder compiler. Do not edit!
package ru.volga.online.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.nvidia.devtech.CustomEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ru.volga.online.R;
import ru.volga.online.gui.util.CustomRecyclerView;

public final class DialogOldBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FrameLayout buttonNegative;

  @NonNull
  public final TextView buttonNegativeText;

  @NonNull
  public final FrameLayout buttonPositive;

  @NonNull
  public final TextView buttonPositiveText;

  @NonNull
  public final FrameLayout dialog;

  @NonNull
  public final LinearLayout dialogBody;

  @NonNull
  public final LinearLayout dialogButtons;

  @NonNull
  public final TextView dialogCaption;

  @NonNull
  public final TextView dialogField1;

  @NonNull
  public final TextView dialogField2;

  @NonNull
  public final TextView dialogField3;

  @NonNull
  public final TextView dialogField4;

  @NonNull
  public final CustomEditText dialogInput;

  @NonNull
  public final FrameLayout dialogInputLayout;

  @NonNull
  public final FrameLayout dialogList;

  @NonNull
  public final LinearLayout dialogListLayout;

  @NonNull
  public final CustomRecyclerView dialogListRecycler;

  @NonNull
  public final LinearLayout dialogTablistRow;

  @NonNull
  public final TextView dialogText;

  @NonNull
  public final ConstraintLayout dialogTextLayout;

  private DialogOldBinding(@NonNull FrameLayout rootView, @NonNull FrameLayout buttonNegative,
      @NonNull TextView buttonNegativeText, @NonNull FrameLayout buttonPositive,
      @NonNull TextView buttonPositiveText, @NonNull FrameLayout dialog,
      @NonNull LinearLayout dialogBody, @NonNull LinearLayout dialogButtons,
      @NonNull TextView dialogCaption, @NonNull TextView dialogField1,
      @NonNull TextView dialogField2, @NonNull TextView dialogField3,
      @NonNull TextView dialogField4, @NonNull CustomEditText dialogInput,
      @NonNull FrameLayout dialogInputLayout, @NonNull FrameLayout dialogList,
      @NonNull LinearLayout dialogListLayout, @NonNull CustomRecyclerView dialogListRecycler,
      @NonNull LinearLayout dialogTablistRow, @NonNull TextView dialogText,
      @NonNull ConstraintLayout dialogTextLayout) {
    this.rootView = rootView;
    this.buttonNegative = buttonNegative;
    this.buttonNegativeText = buttonNegativeText;
    this.buttonPositive = buttonPositive;
    this.buttonPositiveText = buttonPositiveText;
    this.dialog = dialog;
    this.dialogBody = dialogBody;
    this.dialogButtons = dialogButtons;
    this.dialogCaption = dialogCaption;
    this.dialogField1 = dialogField1;
    this.dialogField2 = dialogField2;
    this.dialogField3 = dialogField3;
    this.dialogField4 = dialogField4;
    this.dialogInput = dialogInput;
    this.dialogInputLayout = dialogInputLayout;
    this.dialogList = dialogList;
    this.dialogListLayout = dialogListLayout;
    this.dialogListRecycler = dialogListRecycler;
    this.dialogTablistRow = dialogTablistRow;
    this.dialogText = dialogText;
    this.dialogTextLayout = dialogTextLayout;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogOldBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogOldBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_old, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogOldBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_negative;
      FrameLayout buttonNegative = ViewBindings.findChildViewById(rootView, id);
      if (buttonNegative == null) {
        break missingId;
      }

      id = R.id.button_negative_text;
      TextView buttonNegativeText = ViewBindings.findChildViewById(rootView, id);
      if (buttonNegativeText == null) {
        break missingId;
      }

      id = R.id.button_positive;
      FrameLayout buttonPositive = ViewBindings.findChildViewById(rootView, id);
      if (buttonPositive == null) {
        break missingId;
      }

      id = R.id.button_positive_text;
      TextView buttonPositiveText = ViewBindings.findChildViewById(rootView, id);
      if (buttonPositiveText == null) {
        break missingId;
      }

      FrameLayout dialog = (FrameLayout) rootView;

      id = R.id.dialog_body;
      LinearLayout dialogBody = ViewBindings.findChildViewById(rootView, id);
      if (dialogBody == null) {
        break missingId;
      }

      id = R.id.dialog_buttons;
      LinearLayout dialogButtons = ViewBindings.findChildViewById(rootView, id);
      if (dialogButtons == null) {
        break missingId;
      }

      id = R.id.dialog_caption;
      TextView dialogCaption = ViewBindings.findChildViewById(rootView, id);
      if (dialogCaption == null) {
        break missingId;
      }

      id = R.id.dialog_field1;
      TextView dialogField1 = ViewBindings.findChildViewById(rootView, id);
      if (dialogField1 == null) {
        break missingId;
      }

      id = R.id.dialog_field2;
      TextView dialogField2 = ViewBindings.findChildViewById(rootView, id);
      if (dialogField2 == null) {
        break missingId;
      }

      id = R.id.dialog_field3;
      TextView dialogField3 = ViewBindings.findChildViewById(rootView, id);
      if (dialogField3 == null) {
        break missingId;
      }

      id = R.id.dialog_field4;
      TextView dialogField4 = ViewBindings.findChildViewById(rootView, id);
      if (dialogField4 == null) {
        break missingId;
      }

      id = R.id.dialog_input;
      CustomEditText dialogInput = ViewBindings.findChildViewById(rootView, id);
      if (dialogInput == null) {
        break missingId;
      }

      id = R.id.dialog_input_layout;
      FrameLayout dialogInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (dialogInputLayout == null) {
        break missingId;
      }

      id = R.id.dialog_list;
      FrameLayout dialogList = ViewBindings.findChildViewById(rootView, id);
      if (dialogList == null) {
        break missingId;
      }

      id = R.id.dialog_list_layout;
      LinearLayout dialogListLayout = ViewBindings.findChildViewById(rootView, id);
      if (dialogListLayout == null) {
        break missingId;
      }

      id = R.id.dialog_list_recycler;
      CustomRecyclerView dialogListRecycler = ViewBindings.findChildViewById(rootView, id);
      if (dialogListRecycler == null) {
        break missingId;
      }

      id = R.id.dialog_tablist_row;
      LinearLayout dialogTablistRow = ViewBindings.findChildViewById(rootView, id);
      if (dialogTablistRow == null) {
        break missingId;
      }

      id = R.id.dialog_text;
      TextView dialogText = ViewBindings.findChildViewById(rootView, id);
      if (dialogText == null) {
        break missingId;
      }

      id = R.id.dialog_text_layout;
      ConstraintLayout dialogTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (dialogTextLayout == null) {
        break missingId;
      }

      return new DialogOldBinding((FrameLayout) rootView, buttonNegative, buttonNegativeText,
          buttonPositive, buttonPositiveText, dialog, dialogBody, dialogButtons, dialogCaption,
          dialogField1, dialogField2, dialogField3, dialogField4, dialogInput, dialogInputLayout,
          dialogList, dialogListLayout, dialogListRecycler, dialogTablistRow, dialogText,
          dialogTextLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
