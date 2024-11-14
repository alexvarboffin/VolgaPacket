package ru.volga.online.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.wardrumstudios.utils.WarMedia;

import java.util.Arrays;

import ru.volga.launcher.activity.MainActivity;
import ru.volga.online.R;
import ru.volga.utils.DLog;

public class GTASA extends WarMedia {

    public GTASA gtasaSelf = null;
    static String vmVersion;
    private boolean once = false;

    public GTASA() {
//        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
//            DLog.d("UncaughtException Uncaught exception in thread " + thread.getName() + " " + throwable);
//        });
    }

    //java.lang.UnsatisfiedLinkError: dlopen failed: library "lib[GTASA].so" not found


    static {
        vmVersion = null;
        DLog.d("**** Loading SO's");
        try {
            vmVersion = System.getProperty("java.vm.version");
            DLog.d("vmVersion " + vmVersion);
            System.loadLibrary("ImmEmulatorJ");
        } catch (ExceptionInInitializerError | UnsatisfiedLinkError e) {
            DLog.handleException(e);
        }
        //adb shell getprop ro.product.cpu.abi
        String abi = android.os.Build.SUPPORTED_ABIS[0];
        DLog.d("Device ABI Supported ABI: " + abi);
        DLog.d("DeviceABI Supported ABIs: " + Arrays.toString(Build.SUPPORTED_ABIS));

        //[x86_64, x86, arm64-v8a, armeabi-v7a, armeabi]
        //[armeabi-v7a, armeabi]
        try {
            System.loadLibrary("GTASA");

        } catch (java.lang.UnsatisfiedLinkError e) {
            DLog.handleException(e);
        }

        try {
            System.loadLibrary("samp");
            DLog.d("LibraryLoad samp library loaded successfully.");
        } catch (UnsatisfiedLinkError e) {
            DLog.e("LibraryLoad Failed to load samp library." + e);
        } catch (Exception e) {
            DLog.e("LibraryLoad Unexpected error occurred while loading samp library." + e);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        if (!once) {
            once = true;
        }

        DLog.d("[GTASA] onCreate");
        gtasaSelf = this;
        wantsMultitouch = true;
        wantsAccelerometer = true;
        super.onCreate(bundle);

        View serverLayout = findViewById(R.id.edgar_loagin_kranin);
        serverLayout.setVisibility(View.GONE);
    }


//    public static void staticEnterSocialClub() {
//        gtasaSelf.EnterSocialClub();
//    }
//
//    public static void staticExitSocialClub() {
//        gtasaSelf.ExitSocialClub();
//    }

    public void AfterDownloadFunction() {

    }

    public void EnterSocialClub() {

    }

    public void ExitSocialClub() {

    }

    public boolean ServiceAppCommand(String str, String str2) {
        return false;
    }

    public int ServiceAppCommandValue(String str, String str2) {
        return 0;
    }

    public native void main();

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }


    private void startAll() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onDestroy() {
        DLog.d("[GTASA] onDestroy");
        Toast.makeText(getApplicationContext(), "Начните игру заново", Toast.LENGTH_SHORT).show();
        startAll();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public void onPause() {
        DLog.d("[GTASA] onPause");
        super.onPause();
    }

    public void onRestart() {
        DLog.d("[GTASA] onRestart");
        super.onRestart();
    }

    public void onResume() {
        DLog.d("[GTASA] onResume");
        super.onResume();
    }

    public void onStart() {
        DLog.d("[GTASA] onStart");
        super.onStart();
    }

    public void onStop() {
        DLog.d("[GTASA] onStop");
        super.onStop();
    }

    public native void setCurrentScreenSize(int i, int i2);
}