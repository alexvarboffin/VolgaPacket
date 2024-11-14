package com.wardrumstudios.utils;

import android.view.ViewParent;

import ru.volga.utils.DLog;

public class WarGamepad extends WarBilling {
    public int GetGamepadType()
    {
        return -1;
    }
    public int GetGamepadButtons()
    {
        DLog.d("**** GetGamepadButtons()");
        return 0;
    }

    public int GetGamepadTrack(int i, int i2)
    {
        DLog.d("**** GetGamepadTrack()");
        return 0;
    }
    public float GetGamepadAxis(int i)
    {
        DLog.d("**** GetGamepadAxis()");
        return 0.0f;
    }

    public native boolean processTouchpadAsPointer(ViewParent viewParent, boolean z);
}
