package ru.volga.online.gui.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.nvidia.devtech.NvEventQueueActivity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ru.volga.online.databinding.DialogOldBinding;
import ru.volga.online.gui.util.Utils;
import ru.volga.utils.DLog;


public class Dialog {

    private static final boolean SHOW_SOFT_INPUT_ON_FOCUS = false;

    private static final int DIALOG_LEFT_BTN_ID = 1;
    private static final int DIALOG_RIGHT_BTN_ID = 0;
    private static final int DIALOG_STYLE_INPUT = 1;
    private static final int DIALOG_STYLE_LIST = 2;
    private static final int DIALOG_STYLE_MSGBOX = 0;
    private static final int DIALOG_STYLE_PASSWORD = 3;
    private static final int DIALOG_STYLE_TABLIST = 4;
    private static final int DIALOG_STYLE_TABLIST_HEADER = 5;
    private final DialogOldBinding binding;

    private int currentDialogId = -1;
    private int currentDialogTypeId = -1;
    private String currentInputText = "";
    private int currentListItem = -1;
    private ArrayList<String> rowsList;

    private final ArrayList<TextView> headersList = new ArrayList<>();


    public Dialog(DialogOldBinding binding, Activity activity) {
        this.binding = binding;
        rowsList = new ArrayList<>();
        binding.dialogInput.setShowSoftInputOnFocus(SHOW_SOFT_INPUT_ON_FOCUS);

        InputMethodManager imm = (InputMethodManager) NvEventQueueActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && activity.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }

        for (int i = 0; i < binding.dialogTablistRow.getChildCount(); i++) {
            headersList.add((TextView) binding.dialogTablistRow.getChildAt(i));
        }

        binding.dialogInput.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if ((actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) && binding.dialogInput.getText() != null) {
                currentInputText = binding.dialogInput.getText().toString();
            }
            return false;
        });

        binding.dialogInput.setOnClickListener(view -> {
            binding.dialogInput.requestFocus();
            imm.showSoftInput(binding.dialogInput, InputMethodManager.SHOW_IMPLICIT);
        });

        Utils.HideLayout(binding.dialog, false);
        binding.buttonPositive.setOnClickListener(view -> sendDialogResponse(DIALOG_LEFT_BTN_ID));
        binding.buttonNegative.setOnClickListener(view -> sendDialogResponse(DIALOG_RIGHT_BTN_ID));
    }

    public void show(int dialogId, int dialogTypeId, String caption, String content, String leftBtnText, String rightBtnText) {
        clearDialogData();
        this.currentDialogId = dialogId;
        this.currentDialogTypeId = dialogTypeId;

        if (dialogTypeId == DIALOG_STYLE_MSGBOX) {
            binding.dialogInputLayout.setVisibility(View.GONE);
            binding.dialogListLayout.setVisibility(View.GONE);
            binding.dialogTextLayout.setVisibility(View.VISIBLE);
        } else if (dialogTypeId == DIALOG_STYLE_INPUT || dialogTypeId == DIALOG_STYLE_PASSWORD) {
            binding.dialogInputLayout.setVisibility(View.VISIBLE);
            binding.dialogTextLayout.setVisibility(View.VISIBLE);
            binding.dialogListLayout.setVisibility(View.GONE);
        } else {
            binding.dialogInputLayout.setVisibility(View.GONE);
            binding.dialogTextLayout.setVisibility(View.GONE);
            binding.dialogListLayout.setVisibility(View.VISIBLE);
            loadTabList(content);

            ArrayList<String> fixedFields = Utils.fixFieldsForDialog(rowsList);
            rowsList = fixedFields;
            DialogAdapter adapter = new DialogAdapter(fixedFields, headersList);
            adapter.setOnClickListener((i, str) -> {
                currentListItem = i;
                currentInputText = str;
            });
            adapter.setOnDoubleClickListener(() -> sendDialogResponse(DIALOG_LEFT_BTN_ID));

            binding.dialogListRecycler.setLayoutManager(new LinearLayoutManager(NvEventQueueActivity.getInstance()));
            binding.dialogListRecycler.setAdapter(adapter);
            if (dialogTypeId != DIALOG_STYLE_LIST) {
                binding.dialogListRecycler.post(adapter::updateSizes);
            }
        }

        binding.dialogCaption.setText(Utils.transfromColors(caption));
        binding.dialogText.setText(Utils.transfromColors(content));
        binding.buttonPositiveText.setText(Utils.transfromColors(leftBtnText));
        binding.buttonNegativeText.setText(Utils.transfromColors(rightBtnText));
        binding.buttonNegative.setVisibility(rightBtnText.isEmpty() ? View.GONE : View.VISIBLE);

        Utils.ShowLayout(binding.dialog, true);
    }

    public void sendDialogResponse(int btnId) {
        if (!currentInputText.equals(binding.dialogInput.getText().toString())) {
            currentInputText = binding.dialogInput.getText().toString();
        }
        ((InputMethodManager) NvEventQueueActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(binding.dialogInput.getWindowToken(), 0);
        try {
            NvEventQueueActivity.getInstance().sendDialogResponse(btnId, currentDialogId, currentListItem, currentInputText.getBytes("windows-1251"));
            Utils.HideLayout(binding.dialog, true);
        } catch (UnsupportedEncodingException e) {
            DLog.handleException(e);
        }
    }

    private void loadTabList(String content) {
        String[] rows = content.split("\n");
        for (int i = 0; i < rows.length; i++) {
            if (currentDialogTypeId == DIALOG_STYLE_TABLIST_HEADER && i == 0) {
                String[] headers = rows[i].split("\t");
                for (int j = 0; j < headers.length; j++) {
                    headersList.get(j).setText(Utils.transfromColors(headers[j]));
                    headersList.get(j).setVisibility(View.VISIBLE);
                }
            } else {
                rowsList.add(rows[i]);
            }
        }
    }

    public void hideWithoutReset() {
        Utils.HideLayout(binding.getRoot(), false);
    }

    public void showWithOldContent() {
        Utils.ShowLayout(binding.getRoot(), false);
    }

    private void clearDialogData() {
        binding.dialogInput.setText("");
        currentDialogId = -1;
        currentDialogTypeId = -1;
        currentListItem = -1;
        rowsList.clear();
        for (int i = 0; i < this.headersList.size(); i++) {
            this.headersList.get(i).setVisibility(View.GONE);
        }
    }


    public void onHeightChanged(int height) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) binding.dialog.getLayoutParams();
        params.setMargins(0, 0, 0, height);
        binding.dialog.setLayoutParams(params);
    }
}
