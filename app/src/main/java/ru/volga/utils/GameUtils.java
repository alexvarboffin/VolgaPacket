package ru.volga.utils;

import android.os.Environment;

import java.io.File;

public class GameUtils {
    public static boolean IsGameInstalled() {
        String CheckFile = Environment.getExternalStorageDirectory() + "/VolgaOnline/texdb/gta3.img";
        File file = new File(CheckFile);
        boolean bb = file.exists();
        if(bb){
            DLog.d("" + file.length());
        }
        return bb;
    }
}
