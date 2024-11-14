package com.nvidia.devtech;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.ArrayList;

import ru.volga.online.gui.keyboard.KeyBoard0;


public class CustomEditText extends AppCompatEditText implements View.OnFocusChangeListener {

    private static final boolean SHOW_SOFT_INPUT_ON_FOCUS = true;

    public OnBackListener onBackListener;
    public OnKeyboardOpenListener onKeyboardOpenListener;


    public ArrayList<OnFocusChangeListener> focusChangeListeners;

    public CustomEditText(@NonNull Context context) {
        super(context);
        initialize0();
    }


    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize0();
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize0();
    }

    private void initialize0() {
        this.onBackListener = null;
        this.onKeyboardOpenListener = null;
        this.focusChangeListeners = new ArrayList<>();
        setOnFocusChangeListener(this);
        setShowSoftInputOnFocus(SHOW_SOFT_INPUT_ON_FOCUS);
        if (getTag() == null || !getTag().equals("keyboard_input")) {
            this.focusChangeListeners.add((view, z6) -> {
                if (z6) {
                    OnKeyboardOpenListener listener = CustomEditText.this.onKeyboardOpenListener;
                    if (listener != null) {
                        listener.onKeyboardOpen();
                    } else {
                        if(!SHOW_SOFT_INPUT_ON_FOCUS){
                            KeyBoard0.getKeyBoard().openKeyBoard(CustomEditText.this);
                            //Toast.makeText(getContext(), "@ww@", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }


    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && onBackListener != null) {
            KeyBoard0.getKeyBoard().handleBackKey();
            return true;
        }
        return false;
    }

    @Override
    public final void onFocusChange(View view, boolean hasFocus) {
        //Toast.makeText(getContext(), "@@@", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < this.focusChangeListeners.size(); i++) {
            this.focusChangeListeners.get(i).onFocusChange(view, hasFocus);
        }
    }


    public interface OnBackListener {
    }

    public interface OnKeyboardOpenListener {
        void onKeyboardOpen();
    }


    public final void a(OnFocusChangeListener onFocusChangeListener) {
        this.focusChangeListeners.remove(onFocusChangeListener);
    }


    public void setOnBackListener(OnBackListener onBackListener7) {
        this.onBackListener = onBackListener7;
    }

    public void setOnKeyboardOpenListener(OnKeyboardOpenListener dVar) {
        this.onKeyboardOpenListener = dVar;
    }
}
