package com.wardrumstudios.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import com.nvidia.devtech.NvUtil;

import java.io.File;

import ru.volga.utils.DLog;

public class WarMedia extends WarGamepad
{
    private String baseDirectory;
    private String baseDirectoryRoot;

    public String GetGameBaseDirectory() {
        if (Environment.getExternalStorageState().equals("mounted"))
        {
            try
            {
                File externalFilesDir = getExternalFilesDir(null);
                String absolutePath = externalFilesDir.getAbsolutePath();
                this.baseDirectoryRoot = absolutePath.substring(0, absolutePath.indexOf("/Android"));
                return externalFilesDir.getAbsolutePath() + "/";
            } catch (Exception e)
            {
            }
        }
        return "";
    }

    public void onCreate(Bundle bundle)
    {
        this.baseDirectory = GetGameBaseDirectory();

        NvUtil.getInstance().setActivity(this);
        NvUtil.getInstance().setAppLocalValue("STORAGE_ROOT", this.baseDirectory);
        NvUtil.getInstance().setAppLocalValue("STORAGE_ROOT_BASE", this.baseDirectoryRoot);

        super.onCreate(bundle);
    }
    public void ShowKeyboard(int i)
    {
        DLog.d("**** ShowKeyboard");
    }

    public boolean IsKeyboardShown()
    {
        DLog.d("**** IsKeyboardShown");
        return false;
    }

    public void PlayMovie(String str, float f)
    {
        DLog.d("**** PlayMovie");
    }

    public void PlayMovieInFile(String str, float f, int i, int i2)
    {
        DLog.d("**** PlayMovieInFile");
    }

    public void PlayMovieInWindow(String str, int i, int i2, int i3, int i4, float f, int i5, int i6, int i7) {
        DLog.d("**** PlayMovieInWindow");
    }

    public void StopMovie() {
        DLog.d("**** StopMovie");
    }

    public void MovieSetSkippable(boolean z)
    {
        DLog.d("**** MovieSetSkippable");
    }

    public int IsMoviePlaying()
    {
        DLog.d("**** IsMoviePlaying");
        return 0;
    }

    public boolean DeleteFile(String str)
    {
        DLog.d("**** DeleteFile");
        return true;
    }

    public boolean FileRename(String str, String str2, int i)
    {
        DLog.d("**** FileRename");
        return true;
    }
    public int GetDeviceLocale() {
        DLog.d("**** GetDeviceLocale");
        return 0;
    }

    public boolean IsPhone() // TODO: implement this
    {
        DLog.d("**** IsPhone");
        return true;
    }

    public int GetDeviceType() // TODO: implement this
    {

        int i = 0;
        DLog.d("Build info version device  " + Build.DEVICE);
        DLog.d("Build MANUFACTURER  " + Build.MANUFACTURER);
        DLog.d("Build BOARD  " + Build.BOARD);
        DLog.d("Build DISPLAY  " + Build.DISPLAY);
        DLog.d("Build CPU_ABI  " + Build.CPU_ABI);
        DLog.d("Build CPU_ABI2  " + Build.CPU_ABI2);
        DLog.d("Build HARDWARE  " + Build.HARDWARE);
        DLog.d("Build MODEL  " + Build.MODEL);
        DLog.d("Build PRODUCT  " + Build.PRODUCT);
        int i2 = 0;
        int numberOfProcessors = 1 * 4;
        int i3 = 8 * 64;
        if (IsPhone())
        {
            i = 1;
        }
        return i + i2 + numberOfProcessors + i3;
    }

    public int GetDeviceInfo(int i) // TODO: implement this
    {
        DLog.d("**** GetDeviceInfo");
        if (i == 0) {
            return 1; // num of cpu
        } else if (i == 1) {
            DLog.d("Return for touchsreen 1");
            return 1; // touch screen
        }
        return -1;
    }

    public String GetAndroidBuildinfo(int i) {
        DLog.d("**** GetAndroidBuildinfo");
        if (i == 0) {
            return Build.MANUFACTURER;
        } else if (i == 1) {
            return Build.PRODUCT;
        } else if (i == 2) {
            return Build.MODEL;
        }
        return "UNKNOWN";
    }

    public String OBFU_GetDeviceID() {
        DLog.d("**** OBFU_GetDeviceID");
        return "no id";
    }

    public String FileGetArchiveName(int i) {
        DLog.d("**** FileGetArchiveName");
        switch (i) {
            case 0:
                return ""; // apkFileName
            case 1:
                return ""; // expansionFileName
            case 2:
                return ""; // this.patchFileName;
            default:
                return "";
        }
    }

    public boolean IsAppInstalled(String str) {
        DLog.d("**** IsAppInstalled");
        return false;
    }

    public void OpenLink(String str)
    {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        DLog.d("**** OpenLink");
    }

    public void LoadAllGamesFromCloud() {
        DLog.d("**** LoadAllGamesFromCloud");
    }

    public String LoadGameFromCloud(int i, byte[] bArr) {
        DLog.d("**** LoadGameFromCloud");
        return "";
    }

    public void SaveGameToCloud(int i, byte[] bArr, int i2) {
        DLog.d("**** SaveGameToCloud");
    }

    public boolean IsCloudAvailable() {
        DLog.d("**** IsCloudAvailable");
        return false;
    }

    public boolean NewCloudSaveAvailable(int i) {
        DLog.d("**** NewCloudSaveAvailable");
        return false;
    }

    public void MovieKeepAspectRatio(boolean z) {
        DLog.d("**** MovieKeepAspectRatio");
    }

    public void MovieSetText(String str, boolean z, boolean z2) {
        DLog.d("**** MovieSetText");
    }

    public void MovieDisplayText(boolean z) {
        DLog.d("**** MovieDisplayText");
    }

    public void MovieClearText(boolean z) {
        DLog.d("**** MovieClearText");
    }

    public void MovieSetTextScale(int i) {
        DLog.d("**** MovieSetTextScale");
    }

    public int GetSpecialBuildType() {
        DLog.d("**** GetSpecialBuildType");
        return 0;
    }

    public void SendStatEvent(String str) {
        DLog.d("**** SendStatEvent");
    }

    public void SendStatEvent(String str, String str2, String str3) {
        DLog.d("**** SendStatEvent1");
    }

    public int GetTotalMemory() {
        DLog.d("**** GetTotalMemory");
        return 0;
    }

    public int GetLowThreshhold() {
        DLog.d("**** GetLowThreshhold");
        return 0;
    }

    public int GetAvailableMemory() {
        DLog.d("**** GetAvailableMemory");
        return 0;
    }

    public float GetScreenWidthInches() {
        DLog.d("**** GetScreenWidthInches");
        return 0.0f;
    }

    public String GetAppId()
    {
        DLog.d("**** GetAppId");
       return "";
    }

    public void ScreenSetWakeLock(boolean z) {
        DLog.d("**** ScreenSetWakeLock");
    }

    public boolean ServiceAppCommand(String str, String str2) {
        DLog.d("**** ServiceAppCommand " + str + " " + str2);
        return false;
    }

    public int ServiceAppCommandValue(String str, String str2) {
        DLog.d("**** ServiceAppCommandValue " + str + " " + str2);
        return 0;
    }
}
