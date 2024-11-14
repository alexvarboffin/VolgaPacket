package ru.volga.online.gui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.nvidia.devtech.NvEventQueueActivity;

import java.nio.charset.StandardCharsets;
import java.util.Formatter;

import ru.volga.online.R;
import ru.volga.online.gui.util.Utils;

public class Speedometer {
    public Activity activity;
    public TextView mCarHP;
    public FrameLayout mStrela, menuUP, menuDown, menuTwin, menuLock;
    public FrameLayout mStrela2, mPovorot, mPovorot2;
    public FrameLayout mFuelClick;
    public ImageView mEngine;
    public TextView mFuel;
    public LinearLayout mInputLayout;
    public ImageView mLight;
    public ImageView mLock;
    public TextView mMileage;
    public TextView mSpeed;
    public CircularProgressBar mSpeedLine;
    public ImageView povv, povv2;
    public int Pov, Pov2;

    native void sendClick(int clickId);

    public Speedometer(Activity activity){
        LinearLayout relativeLayout = activity.findViewById(R.id.speedometr_matrp_edgar);
        mInputLayout = relativeLayout;
        menuUP = activity.findViewById(R.id.turn_up);
        menuDown = activity.findViewById(R.id.turn_down);
        menuTwin = activity.findViewById(R.id.turn_twin);
        menuLock = activity.findViewById(R.id.turn_lock);
        mSpeed = activity.findViewById(R.id.speed_text);
        mStrela = activity.findViewById(R.id.turn_left);
        mStrela2 = activity.findViewById(R.id.turn_right);
        mPovorot = activity.findViewById(R.id.turn_left_pov);
        mPovorot2 = activity.findViewById(R.id.turn_right_pov);
        mFuelClick = activity.findViewById(R.id.fuel_layout);
        mFuel = activity.findViewById(R.id.fuel_text);
        mCarHP = activity.findViewById(R.id.hp_text);
        mMileage = activity.findViewById(R.id.mileage);
        mSpeedLine = activity.findViewById(R.id.speed_line);
        mEngine = activity.findViewById(R.id.in_engine);
        mLock = activity.findViewById(R.id.in_key);
        povv = activity.findViewById(R.id.in_left);
        povv2 = activity.findViewById(R.id.in_right);

        menuTwin.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/mode".getBytes(StandardCharsets.UTF_8));
        });
        menuLock.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/lock".getBytes(StandardCharsets.UTF_8));
        });
        menuUP.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
            Utils.HideLayout(menuUP, false);
            Utils.ShowLayout(menuLock, true);
            Utils.ShowLayout(menuTwin, true);
            Utils.ShowLayout(menuDown, true);
        });
        menuDown.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
            Utils.HideLayout(menuDown, false);
            Utils.HideLayout(menuLock, false);
            Utils.HideLayout(menuTwin, false);
            Utils.ShowLayout(menuUP, true);
        });
        mStrela.setOnClickListener( view -> {
            if (Pov == 0)
            {
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(0);
                //povv.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
            }else{
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(0);
                //povv.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
            }
        });
        mFuelClick.setOnClickListener( view ->
        {
            view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
            sendClick(1);
        });
        mStrela2.setOnClickListener( view -> {
            if (Pov2 == 0)
            {
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(4);
                //povv2.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
            }else{
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(4);
                //povv2.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
            }
        });
        mPovorot.setOnClickListener( view -> {
            /*if (Pov == 0)
            {
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(0);
                //povv.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
            }else{
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(0);
                //povv.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
            }*/
            NvEventQueueActivity.getInstance().sendCommand("/povorotleft".getBytes(StandardCharsets.UTF_8));
        });
        mPovorot2.setOnClickListener( view -> {
            /*if (Pov2 == 0)
            {
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(4);
                //povv2.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
            }else{
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(4);
                //povv2.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
            }*/
            NvEventQueueActivity.getInstance().sendCommand("/povorotright".getBytes(StandardCharsets.UTF_8));
        });
        Utils.HideLayout(relativeLayout, false);
    }

    public void UpdateSpeedInfo(int speed, int fuel, int hp, int mileage, int engine, int light, int belt, int lock){
        hp= (int) hp/10;
        mFuel.setText(new Formatter().format("%d", Integer.valueOf(fuel)).toString());
        mMileage.setText(new Formatter().format("%06d", Integer.valueOf(mileage)).toString());
        mCarHP.setText(new Formatter().format("%d%s", Integer.valueOf(hp), "%").toString());
        //mSpeedLine.setProgressMax(1000); )float) ((int) speed)
       // mSpeedLine.setProgress(prog);
        mSpeed.setText(String.valueOf(speed));
        /*if(speed == 0)
            mSpeed.setAlpha((float) 0.5);
            mSpeed.setText("000");
            //mSpeed.setText("pososi");
            mSpeed.setTextColor(activity.getResources().getColor(R.color.black));
        if(speed > 0)
            mSpeed.setAlpha((float) 1.0);
            mSpeed.setText(String.valueOf(speed));
            mSpeed.setTextColor(activity.getResources().getColor(R.color.white));*/
        if(engine == 1)
            mEngine.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
        else
            mEngine.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
        if(lock == 1)
            mLock.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
        else
            mLock.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
    }

    public void ShowSpeed() {
        Utils.ShowLayout(mInputLayout, false);
    }

    public void HideSpeed() {
        Utils.HideLayout(mInputLayout, false);
    }
}