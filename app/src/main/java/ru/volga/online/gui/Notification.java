package ru.volga.online.gui;

import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.nvidia.devtech.NvEventQueueActivity;

import java.io.UnsupportedEncodingException;

import ru.volga.online.R;
import ru.volga.online.gui.util.Utils;
import ru.volga.utils.DLog;

public class Notification {
    public Activity aactivity;

    public ConstraintLayout constraintLayout;

    public View view;

    public LinearLayout main;

    public FrameLayout button;
    public FrameLayout button2;

    public ProgressBar mProgressBar;

    public ImageView ruble, noty_image, noty_image2;

    public TextView noty_btn_text_1;
    public TextView text_notif;

    public static int type, duration;

    public static String text, actionforBtn, textBtn, actionforBtn2;

    public CountDownTimer countDownTimer;

    public Notification (Activity activity) {
        aactivity = activity;
        constraintLayout = activity.findViewById(R.id.constt);
        button = activity.findViewById(R.id.noty_btn_1);
        button2 = activity.findViewById(R.id.noty_btn_2);
        noty_image = activity.findViewById(R.id.noty_image);
        noty_image2 = activity.findViewById(R.id.noty_image2);
        ruble = activity.findViewById(R.id.noty_bg_image);
        text_notif = activity.findViewById(R.id.noty_text);
        noty_btn_text_1 = activity.findViewById(R.id.noty_btn_text_2);
        mProgressBar = activity.findViewById(R.id.noty_progress);

        button.setOnClickListener( view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            HideNotification();
        });
        button2.setOnClickListener( view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            HideNotification();
        });
        Utils.HideLayout(constraintLayout, false);
    }

    public void ShowNotification (int type, String text, int duration, String actionforBtn, String textBtn, String actionforBtn2) {
        Utils.HideLayout(constraintLayout, false);
        clearData();

        this.type = type;
        this.text = text;
        this.duration = duration;
        this.actionforBtn = actionforBtn;
        this.actionforBtn2 = actionforBtn2;
        this.textBtn = textBtn;

        this.text_notif.setText(this.text);

        this.mProgressBar.setMax(this.duration * 1000);
        this.mProgressBar.setProgress(this.duration * 1000);

        //mProgressBar.setProgressDrawable(ContextCompat.getDrawable(aactivity, R.drawable.background_br_notification_orange));
        ruble.setColorFilter(Color.parseColor("#6A00FF"));

        switch (this.type) {
            case 0:
                button.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                noty_image.setVisibility(View.VISIBLE);
                noty_image2.setVisibility(View.GONE);
                break;
            case 1:
                button.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                noty_image.setVisibility(View.VISIBLE);
                noty_image2.setVisibility(View.GONE);
                break;
            case 2:
                button.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                noty_image.setVisibility(View.VISIBLE);
                noty_image2.setVisibility(View.GONE);
                break;
            case 3:
                button.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                noty_image.setVisibility(View.VISIBLE);
                noty_image2.setVisibility(View.GONE);
                break;
            case 4:
                button.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
                noty_image.setVisibility(View.VISIBLE);
                noty_image2.setVisibility(View.GONE);
                break;
            case 5:
                button.setVisibility(View.VISIBLE);
                button2.setVisibility(View.GONE);
                noty_image.setVisibility(View.VISIBLE);
                noty_image2.setVisibility(View.GONE);
                break;
            case 6:
                button.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                noty_image.setVisibility(View.GONE);
                noty_image2.setVisibility(View.VISIBLE);
                break;
        }

        if (this.type == 6 || this.type == 5 || this.type == 4) {
            noty_btn_text_1.setText(textBtn);
            button.setOnClickListener(view -> {
                view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
                try {
                    NvEventQueueActivity.getInstance().sendCommand((Notification.actionforBtn).getBytes("windows-1251"));
                } catch (UnsupportedEncodingException e) {
                    DLog.handleException(e);
                }
                HideNotification();
            });
            button2.setOnClickListener(view -> {
                view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
                try {
                    NvEventQueueActivity.getInstance().sendCommand((Notification.actionforBtn2).getBytes("windows-1251"));
                } catch (UnsupportedEncodingException e) {
                    DLog.handleException(e);
                }
                HideNotification();
            });
        }
        startCountdown();
        Utils.ShowLayout(constraintLayout, true);
    }

    private void clearData() {
        this.text = "";
        this.type = -1;
        this.duration = -1;
        this.actionforBtn = "";
        this.actionforBtn2 = "";
        this.textBtn = "";
    }

    public void startCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        countDownTimer = new CountDownTimer(mProgressBar.getProgress(), 1) {
            @Override
            public void onTick(long j) {
                mProgressBar.setProgress((int) j);
            }
            @Override
            public void onFinish() {
                HideNotification();
            }
        }.start();
    }
    public void HideNotification () {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        constraintLayout.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.popup_hide_notification));
        constraintLayout.setVisibility(View.GONE);
    }
}
