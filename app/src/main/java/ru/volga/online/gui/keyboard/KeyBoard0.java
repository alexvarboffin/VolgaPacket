package ru.volga.online.gui.keyboard;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nvidia.devtech.CustomEditText;
import com.nvidia.devtech.NvEventQueueActivity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import ru.volga.online.R;
import ru.volga.online.databinding.KeyboardBinding;
import ru.volga.online.gui.chatedgar.ChatManager;
import ru.volga.utils.DLog;

public final class KeyBoard0 implements
        ViewTreeObserver.OnGlobalLayoutListener

        , View.OnClickListener
        , CustomEditText.OnBackListener {

    private static final boolean SHOW_SOFT_INPUT_ON_FOCUS = false;

    @SuppressLint("StaticFieldLeak")
    public static KeyBoard0 inst = null;
    public static int language = 1; //(1 - Русский / 2 - Англиский / 3 - Цифры / 4 - Символы / 5 - CMD)
     static boolean isDialog = false;
    public static int caps = 2; //(1 - мал / 2 - биг / 3 - супер биг)
    public int keys_numbers;
    public int clickkeys;
    public int mess = -1;
    public ArrayList<String> messange = new ArrayList<>();
    public NvEventQueueActivity nvEventQueueActivity;
    public CustomEditText customEditText;
    public ViewGroup viewGroup;
    public Keys[] keys;
    public RecyclerView hint_recycler;
    public TextView hint_params;
    public LinearLayout layoutHistory;
    public LinearLayout[] f2225s;

    public FrameLayout history_up, history_down, send_button;
    private KeyboardBinding binding;

    public KeyBoard0(NvEventQueueActivity nvEventQueueActivity) {
        super();
        inst = this;
        this.nvEventQueueActivity = nvEventQueueActivity;
        keys = new Keys[44];
        f2225s = new LinearLayout[4];
    }

    @Override
    public void onGlobalLayout() {

    }

    @Override
    public void onClick(View v) {
        int i10 = 0;
        if (v == history_up) {
            int i11 = mess + 1;
            mess = i11;
            if (i11 >= messange.size()) {
                mess--;
            }
            i10 = mess;
            if (i10 < 0) {
                return;
            }
        } else if (v == history_down) {
            int i12 = mess - 1;
            mess = i12;
            if (i12 < -1) {
                mess = -1;
            }
            i10 = mess;
            if (i10 < 0) {
                this.customEditText.setText("");
                history();
                return;
            }
        }
        customEditText.setText(messange.get(i10));
        customEditText.setSelection(customEditText.getText().length());
        history();
    }

    public static class Keys {

        public int tag;
        public FrameLayout frameLayout;
        public ImageView c;
        public View view;
        public int f2261e;

        public interface a {
        }
    }

    @SuppressLint("DiscouragedApi")
    public static View getIdRes(NvEventQueueActivity nvEventQueueActivity, Resources resources, String str, String str2, ViewGroup viewGroup) {
        return viewGroup.findViewById(resources.getIdentifier(str, str2, nvEventQueueActivity.getPackageName()));
    }

    public static String onePlusOne(String str, int i10) {
        return str + i10;
    }

    private static final Map<String, Integer> tagMap;
    private static final Map<Integer, String> languageMap;

    static {
        tagMap = new HashMap<>();
        tagMap.put("SYMBOL", 1);
        tagMap.put("SHIFT", 2);
        tagMap.put("BACKSPACE", 3);
        tagMap.put("SPACE", 4);
        tagMap.put("ENTER", 5);
        tagMap.put("SWITCH_NUM", 6);
        tagMap.put("SWITCH_NUM2", 7);
        tagMap.put("SWITCH_CMD", 8);
        tagMap.put("SWITCH_LANG", 9);

        languageMap = new HashMap<>();
        languageMap.put(1, "RUS");
        languageMap.put(2, "ENG");
        languageMap.put(3, "NUM1");
        languageMap.put(4, "NUM2");
        languageMap.put(5, "CMD");
    }

    public static int upDateTag(String str) {
        Objects.requireNonNull(str, "Name is null");
        Integer value = tagMap.get(str);
        if (value != null) {
            return value;
        } else {
            throw new IllegalArgumentException("No enum constant com.volga.core.ui.keyboard.KeyboardManager.ButtonType.".concat(str));
        }
    }

    public static String sgetLaunguage(int i10) {
        String language = languageMap.get(i10);
        if (language != null) {
            return language;
        } else {
            throw new IllegalArgumentException("TREW");
        }
    }

    public static class clicabilel implements View.OnTouchListener {

        public final AnimatorSet animatorSet;
        public final AnimatorSet fanimatorSet1;

        public clicabilel(Context context, View view) {
            view.setClickable(true);
            this.animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.reduce_size);
            this.fanimatorSet1 = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.regain_size);
        }

        @Override
        @SuppressLint({"ClickableViewAccessibility"})
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            AnimatorSet animatorSet;
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                if (this.fanimatorSet1.isRunning()) {
                    this.fanimatorSet1.end();
                }
                this.animatorSet.setTarget(view);
                animatorSet = this.animatorSet;
            } else if (action != 1 && action != 3) {
                return false;
            } else {
                if (this.animatorSet.isRunning()) {
                    this.animatorSet.end();
                }
                this.fanimatorSet1.setTarget(view);
                animatorSet = this.fanimatorSet1;
            }
            animatorSet.start();
            return false;
        }
    }// писал EDGAR 3.0

    public boolean vGrop() {
        return viewGroup != null;
    }

    public ViewPropertyAnimator animOpenKeyBoard() {
        this.viewGroup.setVisibility(View.VISIBLE);
        this.viewGroup.clearAnimation();
        this.viewGroup.setAlpha(0.0f);
        this.viewGroup.post(new AnimOpenKeyBoard());
        DLog.d("volga" + "Anim Open KeyBoard");
        return this.viewGroup.animate().setDuration(150L);
    }

    public class AnimOpenKeyBoard implements Runnable {
        public AnimOpenKeyBoard() {
        }

        @Override
        public final void run() {
            binding.getRoot().setTranslationY(binding.layoutMain.getHeight());
            KeyBoard0.this.viewGroup.animate().setDuration(150L).alpha(1.0f - (r() / 100.0f)).translationY(0.0f).start();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void openKeyBoard(CustomEditText editText) {
        if (vGrop()) {
            D(2);
            this.customEditText.a(new a());
            if (editText == null) {
                isDialog = false;
                isChatClose = false;
                editText = binding.input;
                editText.setText("");
                DLog.d("volga" + "chat ==" + editText);
            } else {
                this.customEditText = editText;
                isDialog = true;
                DLog.d("volga" + "dialogtext  ==" + editText);
            }
            u();
            animOpenKeyBoard().start();
            return;
        }

        binding = KeyboardBinding.inflate(LayoutInflater.from(NvEventQueueActivity.getInstance()));
        viewGroup = binding.getRoot();


        NvEventQueueActivity.getInstance().getFrontUILayout().addView(viewGroup, -1, -1);
        if (editText == null) {
            isDialog = false;
            isChatClose = false;
            DLog.d("volga" + "chat");
        } else/* if (editText != null)*/ {
            this.customEditText = editText;
            isDialog = true;
            DLog.d("volga" + "diakog" + editText);
        }

        for (int i10 = 0; i10 < 44; i10++) {
            Keys[] kVarArr = keys;
            kVarArr[i10] = new Keys();
            Keys kVar = kVarArr[i10];
            ViewGroup viewGroup = this.viewGroup;
            kVar.frameLayout = (FrameLayout) getIdRes(nvEventQueueActivity, nvEventQueueActivity.getResources(), onePlusOne("key_", i10), "id", viewGroup);
            Keys[] kVarArr2 = this.keys;
            kVarArr2[i10].tag = upDateTag(((String) kVarArr2[i10].frameLayout.getTag()).toUpperCase());
            Keys[] kVarArr3 = this.keys;
            kVarArr3[i10].c = (ImageView) kVarArr3[i10].frameLayout.getChildAt(0);
            if (keys[i10].frameLayout.getChildCount() > 1) {
                Keys[] kVarArr4 = this.keys;
                kVarArr4[i10].view = kVarArr4[i10].frameLayout.getChildAt(1);
            }
            keys[i10].f2261e = (keys_numbers = i10);
        }
        for (int i11 = 0; i11 < 4; i11++) {
            LinearLayout[] linearLayoutArr = this.f2225s;
            linearLayoutArr[i11] = (LinearLayout) getIdRes(this.nvEventQueueActivity, this.nvEventQueueActivity.getResources(), onePlusOne("row_", i11), "id", viewGroup);
        }
        binding.layoutKeys.setClickable(true);
        binding.layoutKeys.setOnTouchListener(new onClickKeys()); //TODO сделать надо
        this.layoutHistory = viewGroup.findViewById(R.id.layout_history);
        this.history_up = viewGroup.findViewById(R.id.history_up);
        this.history_down = viewGroup.findViewById(R.id.history_down);

        editText = binding.input;

        this.send_button = viewGroup.findViewById(R.id.send_button);
        this.hint_recycler = viewGroup.findViewById(R.id.hint_recycler);
        this.hint_params = viewGroup.findViewById(R.id.hint_params);
        this.history_up.setAlpha(0.0f);
        this.history_down.setAlpha(0.0f);
        editText.setText("");
        FrameLayout frameLayout = this.history_up;
        frameLayout.setOnTouchListener(new clicabilel(this.nvEventQueueActivity, frameLayout));
        FrameLayout frameLayout2 = this.history_down;
        frameLayout2.setOnTouchListener(new clicabilel(this.nvEventQueueActivity, frameLayout2));

        binding.clear.setOnClickListener(v -> {
            customEditText.setText("");
            customEditText.setSelection(customEditText.length());
            if (isDialog) {
                handleBackKey();
            } else {
                x();
            }
        });
        binding.clear.setOnTouchListener(new clicabilel(this.nvEventQueueActivity, binding.clear));
        FrameLayout frameLayout3 = this.send_button;
        frameLayout3.setOnTouchListener(new clicabilel(this.nvEventQueueActivity, frameLayout3));
        RecyclerView.ItemAnimator wVar = this.hint_recycler.getItemAnimator();
        if (wVar != null) {
            this.hint_recycler.setItemAnimator(wVar);
        }
        this.hint_recycler.setLayoutManager(new LinearLayoutManager(viewGroup.getContext(), RecyclerView.HORIZONTAL, false));
        binding.keyHint.setVisibility(View.GONE);
        this.hint_recycler.setVisibility(View.GONE);
        this.hint_params.setVisibility(View.GONE);
        this.send_button.setVisibility(View.GONE);
        D(2);
        u();
        animOpenKeyBoard().start();
    }

    public void history() {
        if (mess + 1 >= messange.size()) {
            history_up.clearAnimation();
            history_up.animate().alpha(0.0f).setDuration(300L).start();
            history_up.setOnClickListener(null);
            history_up.setClickable(false);
        } else {
            history_up.clearAnimation();
            history_up.animate().alpha(1.0f).setDuration(300L).start();
            history_up.setOnClickListener(this);
        }
        if (mess < 0) {
            history_down.clearAnimation();
            history_down.animate().alpha(0.0f).setDuration(300L).start();
            history_down.setOnClickListener(null);
            history_down.setClickable(false);
        } else {
            history_down.clearAnimation();
            history_down.animate().alpha(1.0f).setDuration(300L).start();
            history_down.setOnClickListener(this);
        }
        if (isDialog) {
            layoutHistory.setVisibility(View.GONE);
        } else {
            layoutHistory.setVisibility(View.VISIBLE);
        }
    }
    /*
    SYMBOL - 1
    SHIFT - 2
    BACKSPACE - 3
    SPACE - 4
    ENTER - 5
    SWITCH_NUM - 6
    SWITCH_NUM2 - 7
    SWITCH_CMD - 8
    SWITCH_LANG - 9

    */

    public void f() {
        if (!vGrop()) {
            return;
        }
        ViewPropertyAnimator m9 = m();
        m9.start();
    }

    public class onClickKeys implements View.OnTouchListener {

        public onClickKeys() {
        }

        boolean click;

        @SuppressLint("SetTextI18n")
        public final void clikcKeys(MotionEvent motionEvent) {
            int findPointerIndex;
            KeyBoard0 g0Var = KeyBoard0.this;
            Objects.requireNonNull(g0Var);
            int i10 = 0;
            while (true) {
                if (i10 >= 44) {
                    break;
                }
                Keys[] kVarArr = keys;
                if (kVarArr[i10].frameLayout != null && kVarArr[i10].frameLayout.getVisibility() == View.VISIBLE) {
                    int[] iArr = new int[2];
                    keys[i10].frameLayout.getLocationOnScreen(iArr);
                    int i11 = iArr[0];
                    int i12 = iArr[1];
                    int pointerCount = motionEvent.getPointerCount() - 1;
                    int x9 = (int) (motionEvent.getX(pointerCount) + (motionEvent.getRawX() - motionEvent.getX()));
                    int y = (int) (motionEvent.getY(pointerCount) + (motionEvent.getRawY() - motionEvent.getY()));
                    if ((motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 6 || motionEvent.getActionMasked() == 3) && (findPointerIndex = motionEvent.findPointerIndex(motionEvent.getPointerId(motionEvent.getActionIndex()))) != -1) {
                        x9 = (int) (motionEvent.getX(findPointerIndex) + (motionEvent.getRawX() - motionEvent.getX()));
                        y = (int) (motionEvent.getY(findPointerIndex) + (motionEvent.getRawY() - motionEvent.getY()));
                    }
                    if (x9 >= i11 && x9 <= g0Var.keys[i10].frameLayout.getWidth() + i11 && y >= i12 && y <= g0Var.keys[i10].frameLayout.getHeight() + i12) {
                        clickkeys = i10;
                        /*
                            SYMBOL - 1
                            SHIFT - 2
                            BACKSPACE - 3
                            SPACE - 4
                            ENTER - 5
                            SWITCH_NUM - 6
                            SWITCH_NUM2 - 7
                            SWITCH_CMD - 8
                            SWITCH_LANG - 9
                         */
                        if (keys[clickkeys].tag == 1) {
                            int[] iAArr = new int[2];
                            keys[clickkeys].frameLayout.getLocationInWindow(iAArr);
                            binding.keyHint.setVisibility(View.GONE);
                            String edgar = ((TextView) keys[clickkeys].view).getText().toString();
                            String pon = customEditText.getText().toString();
                            if (yderwal) {
                                yderwal = false;
                                timer.cancel();
                                String edgardop = Yderhwanie(clickkeys);
                                DLog.d(edgardop);
                                if (edgardop == null) {
                                    customEditText.setText(pon + edgar);
                                } else {
                                    customEditText.setText(pon + edgardop);
                                }
                            } else {
                                customEditText.setText(pon + edgar);
                                timer.cancel();
                            }
                            if (caps == 2) {
                                D(1);
                            }
                            customEditText.setSelection(customEditText.getText().length());
                        } else if (keys[clickkeys].tag == 2) {
                            if (click) {
                                // Двойное касание распознано
                                if (caps == 1) {
                                    D(3);
                                }
                                click = false;
                            } else {
                                if (caps == 2 || caps == 3) {
                                    D(1);
                                } else if (caps == 1) {
                                    D(2);
                                }
                            }
                            break;
                        } else if (keys[clickkeys].tag == 4) {
                            String pizda = customEditText.getText().toString();
                            String rezylt = pizda + " ";
                            customEditText.setText(rezylt);
                            customEditText.setSelection(customEditText.getText().length());
                            ((ImageView) keys[clickkeys].frameLayout.getChildAt(0)).setColorFilter(null);
                            Log.e("edgar", "click spase");
                            break;
                        } else if (keys[clickkeys].tag == 5) {
                            if (isDialog) {
                                handleBackKey();
                                break;
                            } else {
                                x();
                                break;
                            }
                        } else if (keys[clickkeys].tag == 6) {
                            SetLanguage(3);
                            break;
                        } else if (keys[clickkeys].tag == 7) {
                            SetLanguage(4);
                            break;
                        } else if (keys[clickkeys].tag == 9) {
                            if (language == 1) {
                                language = language + 1;
                                SetLanguage(language);
                                break;
                            } else if (language == 2) {
                                language = language - 1;
                                SetLanguage(language);
                                break;
                            }
                        }
                        break;
                    }
                }
                i10++;
            }
        }

        public final String Yderhwanie(int i) { //удержание действие
            if (keys[i].tag == 1) {
                if (((TextView) keys[i].view).getText().equals("е")) {
                    return "ё";
                }
                if (((TextView) keys[i].view).getText().equals("Е")) {
                    return "Ё";
                }
                if (((TextView) keys[i].view).getText().equals("ь")) {
                    return "ъ";
                }
                if (((TextView) keys[i].view).getText().equals("Ь")) {
                    return "Ъ";
                }
                return null;
            }
            return null;
        }

        private static final long DOUBLE_TAP_TIME_DELTA = 300;
        private long lastTapTime = 0;
        Timer timer;
        private boolean namp = false;

        @SuppressLint("ClickableViewAccessibility")
        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            //anim(motionEvent);
            if (MotionEvent.ACTION_DOWN == motionEvent.getAction()) {
                namp = true;
                anim(motionEvent);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        yderwal = true;
                    }
                }, 300L);
            }
            if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                namp = false;
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastTapTime < DOUBLE_TAP_TIME_DELTA) {
                    // Двойное касание распознано
                    click = true;
                    clikcKeys(motionEvent);
                    return true;
                } else {
                    clikcKeys(motionEvent);
                }
                lastTapTime = currentTime;
                timer.cancel();
            }
            return true;
        }

        public final void anim(MotionEvent motionEvent) {
            int findPointerIndex;
            int i10 = 0;
            while (true) {
                if (i10 >= 44) {
                    break;
                }
                Keys[] kVarArr = keys;
                if (kVarArr[i10].frameLayout != null && kVarArr[i10].frameLayout.getVisibility() == View.VISIBLE) {
                    int[] iArr = new int[2];
                    keys[i10].frameLayout.getLocationOnScreen(iArr);
                    int i11 = iArr[0];
                    int i12 = iArr[1];
                    int pointerCount = motionEvent.getPointerCount() - 1;
                    int x9 = (int) (motionEvent.getX(pointerCount) + (motionEvent.getRawX() - motionEvent.getX()));
                    int y = (int) (motionEvent.getY(pointerCount) + (motionEvent.getRawY() - motionEvent.getY()));
                    if ((motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 6 || motionEvent.getActionMasked() == 3) && (findPointerIndex = motionEvent.findPointerIndex(motionEvent.getPointerId(motionEvent.getActionIndex()))) != -1) {
                        x9 = (int) (motionEvent.getX(findPointerIndex) + (motionEvent.getRawX() - motionEvent.getX()));
                        y = (int) (motionEvent.getY(findPointerIndex) + (motionEvent.getRawY() - motionEvent.getY()));
                    }
                    if (x9 >= i11 && x9 <= keys[i10].frameLayout.getWidth() + i11 && y >= i12 && y <= keys[i10].frameLayout.getHeight() + i12) {
                        clickkeys = i10;
                        /*
                            SYMBOL - 1
                            SHIFT - 2
                            BACKSPACE - 3
                            SPACE - 4
                            ENTER - 5
                            SWITCH_NUM - 6
                            SWITCH_NUM2 - 7
                            SWITCH_CMD - 8
                            SWITCH_LANG - 9
                         */
                        if (keys[clickkeys].tag == 1) {
                            int[] iAArr = new int[2];
                            keys[clickkeys].frameLayout.getLocationInWindow(iAArr);
                            Rect rect = new Rect(iAArr[0], iAArr[1], keys[clickkeys].frameLayout.getWidth(), keys[clickkeys].frameLayout.getHeight());
                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) binding.keyHint.getLayoutParams();
                            int i16 = rect.left;
                            int i13 = rect.right;
                            layoutParams.leftMargin = i16;
                            int i14 = rect.top;
                            int i15 = rect.bottom;
                            layoutParams.topMargin = i14 - (i15);
                            layoutParams.width = i13;
                            layoutParams.height = i15 * 2;
                            binding.keyHint.setLayoutParams(layoutParams);
                            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) binding.keyHint.getChildAt(1).getLayoutParams();
                            layoutParams2.height = rect.bottom;
                            binding.keyHint.getChildAt(1).setLayoutParams(layoutParams2);
                            ((ImageView) binding.keyHint.getChildAt(0)).setColorFilter(-10990239, PorterDuff.Mode.SRC_IN);
                            binding.keyHint.getChildAt(0).setVisibility(View.VISIBLE);
                            ((ImageView) ((FrameLayout) binding.keyHint.getChildAt(1)).getChildAt(0)).setColorFilter(-10990239, PorterDuff.Mode.SRC_IN);
                            ((TextView) ((FrameLayout) binding.keyHint.getChildAt(1)).getChildAt(1)).setText(((TextView) keys[clickkeys].view).getText());
                            new Handler(Looper.getMainLooper()).postDelayed(new Bykba(), 400L);
                            binding.keyHint.setVisibility(View.VISIBLE);

                        } else if (keys[clickkeys].tag == 3) {
                            new Handler(Looper.getMainLooper()).postDelayed(new backspace(), 150L);
                            Log.e("volga", "click bakcspase");
                        } else if (keys[clickkeys].tag == 4) {
                            ColorFilter start = ((ImageView) keys[clickkeys].frameLayout.getChildAt(0)).getColorFilter();
                            DLog.d(String.valueOf(start));//null
                            Log.e("volga", "color = " + start);
                            ((ImageView) keys[clickkeys].frameLayout.getChildAt(0)).setColorFilter(-10990239, PorterDuff.Mode.SRC_IN);
                        }
                        break;
                    }
                }
                i10++;
            }
        }

        public class backspace implements Runnable {
            public backspace() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                int cursorPosition = customEditText.getSelectionStart();
                if (cursorPosition > 0) {
                    customEditText.setText(customEditText.getText().delete(cursorPosition - 1, cursorPosition));
                    customEditText.setSelection(cursorPosition - 1);
                }
                if (namp) {
                    new Handler((Looper.getMainLooper())).postDelayed(this, 5L);
                }
            }
        }

        public boolean yderwal = false;

        public class Bykba implements Runnable {
            public Bykba() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                String a10;
                if ((a10 = Yderhwanie(clickkeys)) == null) {
                    return;
                }
                ((ImageView) ((FrameLayout) binding.keyHint.getChildAt(1)).getChildAt(0)).setColorFilter(-7700589, PorterDuff.Mode.SRC_IN);
                ((TextView) ((FrameLayout) binding.keyHint.getChildAt(1)).getChildAt(1)).setText(a10);

            }
        }
    }

    public class a implements View.OnFocusChangeListener {

        public class RunnableC0028a implements Runnable {

            public final View f2231a;

            public final View.OnFocusChangeListener f2232b;

            public RunnableC0028a(View view, View.OnFocusChangeListener onFocusChangeListener) {
                this.f2231a = view;
                this.f2232b = onFocusChangeListener;
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (vGrop()) {
                    View view = this.f2231a;
                    if (view instanceof CustomEditText) {
                        if (((CustomEditText) view).focusChangeListeners.contains(this.f2232b)) {
                            KeyBoard0.this.handleBackKey();
                        }
                    }
                }
            }
        }

        public a() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z6) {
            if (z6) {
                return;
            }
            view.post(new KeyBoard0.a.RunnableC0028a(view, this));
        }
    }


    public void u() {
        if (!isDialog) {
            this.customEditText = binding.input;

            this.customEditText.setText(customEditText.getText());
            customEditText.requestFocus();
        }

        //new
        this.binding.input.setFocusable(isDialog);
        this.binding.layoutInput.setVisibility(isDialog ? View.GONE : View.VISIBLE);

        this.customEditText.setOnBackListener(this);
        customEditText.focusChangeListeners.add(new a());
        language = 1;
        SetLanguage(1);
        binding.layoutMain.getViewTreeObserver().addOnGlobalLayoutListener(this);
        //NvEventQueueActivity.getInstance().getHeightProvider().f9601b.add(this);
        binding.layoutKeyboard.setVisibility(View.VISIBLE);
        this.send_button.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.customEditText.setShowSoftInputOnFocus(SHOW_SOFT_INPUT_ON_FOCUS);
        }
        InputMethodManager inputMethodManager = (InputMethodManager) NvEventQueueActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && this.nvEventQueueActivity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(this.nvEventQueueActivity.getCurrentFocus().getWindowToken(), 0);
        }
        mess = -1;
        history();
        customEditText.setSelection(customEditText.getText().length());
        customEditText.setOnEditorActionListener(null);
    }

    public class h implements TextView.OnEditorActionListener {
        public h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i10, KeyEvent keyEvent) {
            if (KeyBoard0.this.vGrop()) {
                if (i10 == 6 || i10 == 5) {
                    KeyBoard0.this.x();
                }
                return false;
            }
            return false;
        }
    }

    public void w() {
        int i10 = caps;
        if (i10 != 2 && i10 != 1) {
            i10 = language;
        }
        SharedPreferences sharedPreferences = nvEventQueueActivity.getSharedPreferences("com.volga.game.keyboard", 0);
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("lang", sgetLaunguage(i10));
            edit.apply();
        }
    }

    public void handleBackKey() {

        if (vGrop()) {
            w();
            viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            //NvEventQueueActivity.getInstance().getHeightProvider().f9601b.remove(this);
            this.customEditText.setOnBackListener(null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.customEditText.setShowSoftInputOnFocus(SHOW_SOFT_INPUT_ON_FOCUS);
            }
            InputMethodManager inputMethodManager = (InputMethodManager) NvEventQueueActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && nvEventQueueActivity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(nvEventQueueActivity.getCurrentFocus().getWindowToken(), 0);
            }
            this.customEditText.a(new a());
            this.customEditText.clearFocus();
            DLog.d("volga" + "Q Close KeyBoard");
            f();
        }
    }


    public boolean isChatClose;

    public void x() {
        if (vGrop()) {
            w();
            if (messange.size() >= 20) {
                messange.remove(0);
            }
            messange.add(0, customEditText.getText().toString());
            if (!isChatClose) {
                String text = customEditText.getText().toString();
                ChatManager.getChatManager().ChatClose();
                try {
                    ChatManager.getChatManager().sendChatMessages(text.getBytes("windows-1251"));
                } catch (UnsupportedEncodingException e) {
                    Log.e("volga", "SLYZILCZ PIZDEC");
                    throw new RuntimeException(e);
                }
                isChatClose = true;
            }
            this.viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            //NvEventQueueActivity.getInstance().getHeightProvider().f9601b.remove(this);

            this.customEditText.setOnBackListener(null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.customEditText.setShowSoftInputOnFocus(SHOW_SOFT_INPUT_ON_FOCUS);
            }
            InputMethodManager inputMethodManager = (InputMethodManager) NvEventQueueActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && nvEventQueueActivity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(nvEventQueueActivity.getCurrentFocus().getWindowToken(), 0);
            }
            this.customEditText.a(new a());
            this.customEditText.clearFocus();
            DLog.d("volga" + "X Close KeyBoard");
            f();
        }
    }


    public int r() {
        SharedPreferences sharedPreferences = this.nvEventQueueActivity.getSharedPreferences("com.volga.game.keyboard", 0);
        if (sharedPreferences == null || !sharedPreferences.contains("keyboardAlpha")) {
            return 0;
        }
        return sharedPreferences.getInt("keyboardAlpha", 0);
    }

    public ViewPropertyAnimator m() {
        this.viewGroup.setClickable(false);
        this.viewGroup.clearAnimation();
        this.viewGroup.setAlpha(1.0f - (r() / 100.0f));
        this.viewGroup.setTranslationY(0.0f);
        DLog.d("volga" + "Anim Close KeyBoard");
        return this.viewGroup.animate().setDuration(150L).alpha(0.0f).translationY(binding.layoutMain.getHeight());
    }

    public void SetLanguage(int i10) {
        if (i10 == 5) {
            /*e0 e0Var = new e0(this.f10236a);
            this.y = e0Var;
            e0Var.f2198f = new h0(this);
            this.f2221o.setVisibility(8);
            this.y.l();*/
            return;
        }
        /*e0 e0Var2 = this.y;
        if (e0Var2 != null) {
            e0Var2.f();
            this.y = null;
            this.f2221o.setVisibility(0);
        }*/
        if (i10 == 1) {
            keys[10].frameLayout.setVisibility(View.VISIBLE);
            keys[20].frameLayout.setVisibility(View.VISIBLE);
            keys[21].frameLayout.setVisibility(View.VISIBLE);
            keys[32].frameLayout.setVisibility(View.VISIBLE);
            keys[33].frameLayout.setVisibility(View.VISIBLE);
            keys[22].frameLayout.setVisibility(View.VISIBLE);
            keys[23].frameLayout.setVisibility(View.GONE);
            keys[24].frameLayout.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f2225s[1].getLayoutParams();
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
            this.f2225s[1].setLayoutParams(layoutParams);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f2225s[2].getLayoutParams();
            layoutParams2.leftMargin = 0;
            layoutParams2.rightMargin = 0;
            this.f2225s[2].setLayoutParams(layoutParams2);
            int i12 = 0;
            for (int i13 = 0; i13 < 44; i13++) {
                if (keys[i13].tag == 1 && keys[i13].frameLayout.getVisibility() == View.VISIBLE) {
                    if (i12 >= 31) {
                        break;
                    }
                    ((TextView) keys[i13].view).setText(String.valueOf("йцукенгшщзхфывапролджэячсмитьбю".charAt(i12)));
                    i12++;
                }
            }
        } else if (i10 == 2) {
            keys[10].frameLayout.setVisibility(View.GONE);
            keys[20].frameLayout.setVisibility(View.GONE);
            keys[21].frameLayout.setVisibility(View.GONE);
            keys[32].frameLayout.setVisibility(View.GONE);
            keys[33].frameLayout.setVisibility(View.GONE);
            keys[22].frameLayout.setVisibility(View.VISIBLE);
            keys[23].frameLayout.setVisibility(View.GONE);
            keys[24].frameLayout.setVisibility(View.GONE);
            Point point = new Point();
            nvEventQueueActivity.getWindowManager().getDefaultDisplay().getSize(point);
            int dimensionPixelSize = point.x - (nvEventQueueActivity.getResources().getDimensionPixelSize(R.dimen._2sdp) * 2);
            //point.x = dimensionPixelSize;
            int i14 = dimensionPixelSize / 10;
            //point.x = i14;
            point.x = i14 / 2;
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.f2225s[1].getLayoutParams();
            int i15 = point.x;
            layoutParams3.leftMargin = i15;
            layoutParams3.rightMargin = i15;
            this.f2225s[1].setLayoutParams(layoutParams3);
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.f2225s[2].getLayoutParams();
            int i16 = point.x;
            layoutParams4.leftMargin = i16;
            layoutParams4.rightMargin = i16;
            this.f2225s[2].setLayoutParams(layoutParams4);
            int i17 = 0;
            for (int i18 = 0; i18 < 44; i18++) {
                if (keys[i18].tag == 1 && keys[i18].frameLayout.getVisibility() == View.VISIBLE) {
                    if (i17 >= 26) {
                        break;
                    }
                    ((TextView) keys[i18].view).setText(String.valueOf("qwertyuiopasdfghjklzxcvbnm".charAt(i17)));
                    i17++;
                }
            }
        } else if (i10 == 3) {
            keys[10].frameLayout.setVisibility(View.GONE);
            keys[20].frameLayout.setVisibility(View.VISIBLE);
            keys[21].frameLayout.setVisibility(View.GONE);
            keys[32].frameLayout.setVisibility(View.VISIBLE);
            keys[33].frameLayout.setVisibility(View.GONE);
            keys[22].frameLayout.setVisibility(View.GONE);
            keys[23].frameLayout.setVisibility(View.GONE);
            keys[24].frameLayout.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) this.f2225s[1].getLayoutParams();
            layoutParams5.leftMargin = 0;
            layoutParams5.rightMargin = 0;
            this.f2225s[1].setLayoutParams(layoutParams5);
            LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) this.f2225s[2].getLayoutParams();
            layoutParams6.leftMargin = 0;
            layoutParams6.rightMargin = 0;
            this.f2225s[2].setLayoutParams(layoutParams6);
            int i19 = 0;
            for (int i20 = 0; i20 < 44; i20++) {
                if (keys[i20].tag == 1 && keys[i20].frameLayout.getVisibility() == View.VISIBLE) {
                    if (i19 >= 28) {
                        break;
                    }
                    ((TextView) keys[i20].view).setText(String.valueOf("1234567890@#₽_&-+()\\\"*:;!$%.".charAt(i19)));
                    i19++;
                }
            }
        } else if (i10 == 4) {
            keys[10].frameLayout.setVisibility(View.GONE);
            keys[20].frameLayout.setVisibility(View.VISIBLE);
            keys[21].frameLayout.setVisibility(View.GONE);
            keys[32].frameLayout.setVisibility(View.VISIBLE);
            keys[33].frameLayout.setVisibility(View.GONE);
            keys[22].frameLayout.setVisibility(View.GONE);
            keys[23].frameLayout.setVisibility(View.VISIBLE);
            keys[24].frameLayout.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams7 = (LinearLayout.LayoutParams) this.f2225s[1].getLayoutParams();
            layoutParams7.leftMargin = 0;
            layoutParams7.rightMargin = 0;
            this.f2225s[1].setLayoutParams(layoutParams7);
            LinearLayout.LayoutParams layoutParams8 = (LinearLayout.LayoutParams) this.f2225s[2].getLayoutParams();
            layoutParams8.leftMargin = 0;
            layoutParams8.rightMargin = 0;
            this.f2225s[2].setLayoutParams(layoutParams8);
            int i21 = 0;
            for (int i22 = 0; i22 < 44; i22++) {
                if (keys[i22].tag == 1 && keys[i22].frameLayout.getVisibility() == View.VISIBLE) {
                    if (i21 >= 29) {
                        break;
                    }
                    ((TextView) keys[i22].view).setText(String.valueOf("~`|•‰π÷×§ツ£€‘¢^°={}⋅≠©®™✓[]<>".charAt(i21)));
                    i21++;
                }
            }
        }
        int i23 = 0;
        while (true) {
            DLog.d(String.valueOf(i23));
            if (i23 >= 44) {
                break;
            }
            if (keys[i23].tag == 9) {
                TextView textView = (TextView) keys[i23].view;
                textView.setText((language == 1) ? "eng" : "рус");
                i23++;
            } else {
                i23++;
            }
        }
        keys[36].frameLayout.setVisibility(View.GONE);
        keys[37].frameLayout.setVisibility(View.GONE);
        keys[43].frameLayout.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams9 = (LinearLayout.LayoutParams) binding.symbolsLayout.getLayoutParams();
        layoutParams9.weight = 20.0f;
        binding.symbolsLayout.setLayoutParams(layoutParams9);
        int i25 = caps;
        D(i25);
    }

    public void D(int i10) {
        TextView textView;
        String upperCase;
        caps = i10;
        for (int i13 = 0; i13 < 44; i13++) {
            if (keys[i13].tag == 1) {
                if (i10 != 2 && i10 != 3) {
                    textView = (TextView) keys[i13].view;
                    upperCase = ((TextView) keys[i13].view).getText().toString().toLowerCase();
                } else {
                    textView = (TextView) keys[i13].view;
                    upperCase = ((TextView) keys[i13].view).getText().toString().toUpperCase();
                }
                textView.setText(upperCase);
            } else if (keys[i13].tag == 2) {
                ((ImageView) keys[i13].view).setImageResource(i10 == 1 ? R.drawable.ic_shift_off : i10 == 2 ? R.drawable.ic_shift_single : R.drawable.ic_shift_always);
            }
        }
    }

    public static KeyBoard0 getKeyBoard() {
        return inst;
    }
}
