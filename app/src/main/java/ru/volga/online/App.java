package ru.volga.online;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import org.ini4j.Wini;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.volga.utils.DLog;

public class App extends Application {

    public App() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Wini w = new Wini(new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/local.ini"));
            String host = w.get("server", "volga_host");

        } catch (Exception e) {
            DLog.handleException(e);
        }

        //[x86_64, arm64-v8a]
        DLog.d("----------" + _o(this));
        String abi = android.os.Build.SUPPORTED_ABIS[0];
        DLog.d("Device ABI Supported ABI: " + abi);
        DLog.d("DeviceABI Supported ABIs: " + Arrays.toString(Build.SUPPORTED_ABIS));
    }


    private static String _o(Context context) {
        ApplicationInfo appInfo;
        int minSdk = -1;
        int targetSdk = -1;
        List<String> nativeLibraries = new ArrayList<>();
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            File libDir = new File(appInfo.nativeLibraryDir);
            if (libDir.exists() && libDir.isDirectory()) {
                File[] files = libDir.listFiles((dir, name) -> name.endsWith(".so"));
                if (files != null) {
                    for (File file : files) {
                        nativeLibraries.add(libDir+" | "+file.getName());
                    }
                }}
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                minSdk = appInfo.minSdkVersion;
            }
            targetSdk = appInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            DLog.handleException(e);
        }


        String _o = "";//""[+]gp->" + isFromGooglePlay(context);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            _o = _o + ", category->" + context.getApplicationInfo().category;
        }
        _o = _o + ", "
                + "Device SDK: " + Build.VERSION.SDK_INT + "\n" +
                "minSdk: " + minSdk + "\n" +
                "targetSdk: " + targetSdk + "\n" +
                "compileSdk: Not available at runtime (compile-time value)\n"
                + Arrays.toString(nativeLibraries.toArray());
        return _o;
    }
}
