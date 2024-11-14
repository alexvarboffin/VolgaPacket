package ru.volga.launcher.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import ru.volga.launcher.fragment.DownloadFragment;
import ru.volga.launcher.fragment.MainFragment;
import ru.volga.launcher.fragment.ServerSelectFragment;
import ru.volga.launcher.fragment.SettingsFragment;
import ru.volga.launcher.fragment.SplashFragment;
import ru.volga.launcher.services.api.ApiService;
import ru.volga.online.BuildConfig;
import ru.volga.online.R;
import ru.volga.online.activity.GTASA;
import ru.volga.utils.DLog;
import ru.volga.utils.GameUtils;

public class MainActivity extends AppCompatActivity implements OnServerSelectListener, YourPresenter.PermissionView {



    public ServerSelectFragment serverSelectFragment;
    public SettingsFragment settingsFragment;
    public MainFragment mainFragment;
    public DownloadFragment downloadFragment;
    public FrameLayout front_ui_layout;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public int Auth = 28540117;
    public int AuthCheck = 7;
    private String courseName;
    private FirebaseFirestore db;
    public String text3, text4, text6;
    Context pone;
    Activity activity;
    Timer t;
    File file = new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/local.ini");
    private YourPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);presenter = new YourPresenter(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "MyApp::MyWakeLockTag");
        wakeLock.acquire(10*60*1000L /*10 minutes*/);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_click);

        front_ui_layout = findViewById(R.id.front_ui_layout);

        SplashFragment splashFragment = new SplashFragment();
        serverSelectFragment = new ServerSelectFragment();
        settingsFragment = new SettingsFragment();
        mainFragment = new MainFragment();
        downloadFragment = new DownloadFragment();
        t = new Timer();

        ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        });
        launcher.launch(Manifest.permission.ACCESS_NOTIFICATION_POLICY);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED  //|| checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
                startTimer();
                mHandler.postDelayed(new AnimClose(), 7800);
                Toast.makeText(getApplicationContext(), "Загрузка!", Toast.LENGTH_SHORT).show();
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NOTIFICATION_POLICY, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO/*Manifest.permission.READ_CONTACTS*/}, 1000);
            } else {
                startTimerSpeed();
                mHandler.postDelayed(new AnimClose(), 1800);
            }
        } else {
            startTimerSpeed();
            mHandler.postDelayed(new AnimClose(), 1800);
        }
        if (presenter.hasPermissions(this)) {
            try {
                //startTimer(); mHandler.postDelayed(new AnimClose(), 7800);
                overridePendingTransition(0, 0);
            } catch (Exception e) {

            }
        } else if (!presenter.hasPermissions(this)) {
            presenter.requestPermissions(this, 101);
        }

        //startTimer();

        replaceFragment(splashFragment);
    }

    @Override
    public void requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }


    public static class AnimClose implements Runnable {
        public AnimClose() {
        }

        public final void run() {
            SplashFragment sp = SplashFragment.getSplash();
            if (sp != null) {
                sp.j();
            }
        }
    }


    public void onClickPlay() {
        if (GameUtils.IsGameInstalled()) {
            if (BuildConfig.VERSION_CODE < ApiService.getInstance().version_launcher) {
                Toast.makeText(getApplicationContext(), "У Вас старая версия игры " + BuildConfig.VERSION_CODE + ". Обновите игру до " + ApiService.getInstance().version_launcher + " версии!", Toast.LENGTH_SHORT).show();
                replaceSettings();
                return;
            }
            if (IsUpdateVersionFiles()) {
                t.cancel();
                startActivity(new Intent(getApplicationContext(), GTASA.class));
            } else {
                replaceFragment(downloadFragment);
                Toast.makeText(getApplicationContext(), "Обновление игры!", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (DownloadFragment.nick == null) {
                Toast.makeText(getApplicationContext(), "Установите никнейм!", Toast.LENGTH_SHORT).show();
                replaceSettings();
            } else {
                replaceFragment(downloadFragment);
            }
        }
    }

    public void replaceFragment(Fragment fragment) {
        if (!getSupportFragmentManager().isStateSaved()) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.setCustomAnimations(R.anim.grow_fade_in_from_bottom, R.anim.mtrl_bottom_sheet_slide_out);
            beginTransaction.replace(R.id.front_ui_layout, fragment);
            beginTransaction.commit();
        }
    }

    public void replaceServers() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.fade_out);
        beginTransaction.replace(R.id.front_ui_layout, serverSelectFragment);
        beginTransaction.commit();
    }

    public void replaceSettings() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.fade_out);
        beginTransaction.replace(R.id.front_ui_layout, settingsFragment);
        beginTransaction.commit();
    }

    @Override
    public void closeServers() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.fade_in, R.anim.slide_out_bottom);
        beginTransaction.replace(R.id.front_ui_layout, mainFragment);
        beginTransaction.commit();
    }

    public boolean isRecordAudioPermissionGranted() {
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.RECORD_AUDIO") == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 2);
        return false;
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        return false;
    }

    public void onRequestPermissionsResultBr(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            replaceFragment(mainFragment);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101 && Build.VERSION.SDK_INT >= 30 && !presenter.hasPermissions(this)) {
            Toast.makeText(getApplicationContext(), "Дайте разрешение!", Toast.LENGTH_SHORT).show();
        } else replaceFragment(mainFragment);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private boolean IsUpdateVersionFiles() {
        String CheckFile = Environment.getExternalStorageDirectory() + "/VolgaOnline/texdb/" + ApiService.getInstance().version_modpack;
        File file = new File(CheckFile);
        return file.exists();
    }

    private void startTimer() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    File theDir = new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/");
                    if (!theDir.exists()) {
                        boolean b = theDir.mkdirs();
                    }
                    if (file.exists()) {
                        //file.delete();
                    } else {
                        boolean b = file.createNewFile();
                        makeConfigFile(file);
                        DLog.d("File is created!");
                    }
                } catch (IOException e) {
                    DLog.handleException(e);
                }
                replaceFragment(mainFragment);
            }
        }, 8000L);
    }

    private void startTimerSpeed() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    File theDir = new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/");
                    if (!theDir.exists()) {
                        theDir.mkdirs();
                    }
                    if (file.exists()) {
                        //file.delete();
                    } else {
                        file.createNewFile();
                        makeConfigFile(file);
                        DLog.d("File is created!");
                    }
                } catch (IOException e) {
                    DLog.handleException(e);
                }
                replaceFragment(mainFragment);
            }
        }, 2000L);
    }

    private void makeConfigFile(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write("[server]\n");
        writer.write("server = 0\n");
        writer.write("name = 0\n");
        writer.write("color = 0\n");
        writer.write("maxonline = 0\n");
        writer.write("online = 0\n");
        writer.write("volga_host = 0\n");
        writer.write("volga_port = 0\n");
        writer.write("id = 0\n");
        writer.close();
    }


    public void PermissionPon() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
            } else {
                Toast.makeText(this, "Дайте разрешение или вы не сможете пользоваться лаунчером!!!", Toast.LENGTH_SHORT).show();
                startTimer();
            }
        } else startTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRestart() {
        super.onRestart();

    }
} 