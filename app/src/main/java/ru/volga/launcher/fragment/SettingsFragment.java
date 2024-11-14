package ru.volga.launcher.fragment;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ini4j.Ini;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;

import ru.volga.launcher.other.Utils;
import ru.volga.launcher.services.api.ApiService;
import ru.volga.online.BuildConfig;
import ru.volga.online.R;
import ru.volga.utils.DLog;

public class SettingsFragment extends BaseFragment {

    Animation animation;
    public EditText nickname;
    String nickName;
    TextView faq_text, text_version, account_not_auth_text;
    Timer i;
    public static SettingsFragment in;

    public static SettingsFragment getInSettings() {
        return in;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_settings, container, false);
        in = this;
        i = new Timer();

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.button_click);

        inflate.findViewById(R.id.btn_close).setOnClickListener(v -> {
            v.startAnimation(animation);
            //i.cancel();
           notifyServerClose();
        });
        nickname = inflate.findViewById(R.id.account_text);
        faq_text = inflate.findViewById(R.id.faq_text);
        text_version = inflate.findViewById(R.id.text_version);
        account_not_auth_text = inflate.findViewById(R.id.account_not_auth_text);

        faq_text.setText("\nПроблемы? Мы можем вам помочь!");
        text_version.setText("Версия лаунчера - " + BuildConfig.VERSION_CODE);
        // АВторство account_not_auth_text.setText(MainActivity.getMainActivity().text4);
        //DLog.d(MainActivity.getMainActivity().text4);

        InitLogic();

        inflate.findViewById(R.id.btn_reinstall_data_dev)
                .setOnClickListener(v -> {
                    v.startAnimation(animation);
                    //File gameDirectoryDev = (new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/texdb"));
                    //Utils.delete(gameDirectoryDev);
                    //File gameDirectoryDev2 = (new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/data"));
                    //Utils.delete(gameDirectoryDev2);
                    //startActivity(new Intent(getActivity(), ru.volga.launcher.fragment.DownloadFragmentDev.class));
                });

        inflate.findViewById(R.id.btn_reinstall_data)
                .setOnClickListener(
                        v -> {
                            v.startAnimation(animation);
                            File gameDirectory = (new File(Environment.getExternalStorageDirectory() + "/VolgaOnline"));
                            Utils.delete(gameDirectory);
                            //startActivity(new Intent(getActivity(), ru.volga.launcher.fragment.DownloadFragment.class));
                        });
        inflate.findViewById(R.id.btn_reinstall_client)
                .setOnClickListener(
                        v -> {
                            v.startAnimation(animation);
                            if (BuildConfig.VERSION_CODE < ApiService.getInstance().version_launcher) {
                                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.rustore.ru/catalog/app/ru.volga.online")));
                            } else
                                Toast.makeText(getContext(), "У Вас последняя версия игры!", Toast.LENGTH_SHORT).show();
                        });
        inflate.findViewById(R.id.save_nick)
                .setOnClickListener(v -> {
                    v.startAnimation(animation);
                    try {
                        File f = new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/settings.ini");
                        if (!f.exists()) {
                            f.createNewFile();
                            f.mkdirs();
                        }
                        Wini w = new Wini(new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/settings.ini"));
                        if (checkValidNick(inflate)) {
                            w.put("client", "name", nickname.getText().toString());
                            w.put("client", "password", " ");
                            w.put("client", "last_server", "0");

                            w.put("gui", "Font", "visby-round-cf-extra-bold.ttf");
                            w.put("gui", "FontSize", "30.000000");
                            w.put("gui", "FontOutline", "2");
                            w.put("gui", "CameraCycleSize", "90.000000");
                            w.put("gui", "CameraCycleX", "1803.000000");
                            w.put("gui", "CameraCycleY", "294.000000");
                            w.put("gui", "ChatMaxMessages", "8");
                            w.put("gui", "voicelist", "1");
                            w.put("gui", "fps", "60");
                            w.put("gui", "cutout", "1");
                            w.put("gui", "androidKeyboard", "0");
                            w.put("gui", "fpscounter", "0");
                            w.put("gui", "outfit", "0");
                            w.put("gui", "radarrect", "0");
                            w.put("gui", "hud", "1");
                            w.put("hud", "hud_scale_x_6", "85");

                            Toast.makeText(getActivity(), "Ваш новый никнейм успешно сохранен!", Toast.LENGTH_SHORT).show();
                            DownloadFragment.nick = nickname.getText().toString();
                        } else {
                            checkValidNick(inflate);
                        }
                        w.store();
                    } catch (IOException e) {
                        DLog.handleException(e);
                        
                        DownloadFragment.nick = nickname.getText().toString();
                        //Toast.makeText(getActivity(), "Установите игру!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "Ваш новый никнейм успешно сохранен!", Toast.LENGTH_SHORT).show();
                    }
                });

        /*((ImageView) inflate.findViewById(R.id.instagramButton))
                .setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                v.startAnimation(animation);
                                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("")));
                            }
        });*/

        nickname
                .setOnEditorActionListener(
                        (v, actionId, event) -> {
                            if (actionId == EditorInfo.IME_ACTION_SEARCH
                                    || actionId == EditorInfo.IME_ACTION_DONE
                                    || event.getAction() == KeyEvent.ACTION_DOWN
                                    && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                try {
                                    File f =
                                            new File(
                                                    Environment.getExternalStorageDirectory()
                                                            + "/VolgaOnline/SAMP/settings.ini");
                                    if (!f.exists()) {
                                        f.createNewFile();
                                        f.mkdirs();

                                    }
                                    Wini w = new Wini(new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/settings.ini"));

                                    if (checkValidNick(inflate)) {

                                        w.put("client", "name", nickname.getText().toString());
                                        w.put("client", "password", " ");
                                        w.put("client", "last_server", "0");

                                        w.put("gui", "Font", "visby-round-cf-extra-bold.ttf");
                                        w.put("gui", "FontSize", "30.000000");
                                        w.put("gui", "FontOutline", "2");
                                        w.put("gui", "CameraCycleSize", "90.000000");
                                        w.put("gui", "CameraCycleX", "1803.000000");
                                        w.put("gui", "CameraCycleY", "294.000000");
                                        w.put("gui", "ChatMaxMessages", "8");
                                        w.put("gui", "voicelist", "1");
                                        w.put("gui", "fps", "60");
                                        w.put("gui", "cutout", "1");
                                        w.put("gui", "androidKeyboard", "0");
                                        w.put("gui", "fpscounter", "0");
                                        w.put("gui", "outfit", "0");
                                        w.put("gui", "radarrect", "0");
                                        w.put("gui", "hud", "1");
                                        w.put("hud", "hud_scale_x_6", "85");

                                        Toast.makeText(
                                                getActivity(),
                                                "Ваш новый никнейм успешно сохранен!",
                                                Toast.LENGTH_SHORT).show();
                                        DownloadFragment.nick = nickname.getText().toString();
                                    } else {
                                        checkValidNick(inflate);
                                    }
                                    w.store();
                                } catch (IOException e) {
                                    DLog.handleException(e);
                                    DownloadFragment.nick = nickname.getText().toString();
                                    //Toast.makeText(getActivity(), "Установите игру!", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(
                                            getActivity(),
                                            "Ваш новый никнейм успешно сохранен!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                            return false;
                        });
        return inflate;
    }

    private boolean loadIni() {
        AssetManager assetMgr = getResources().getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetMgr.open("test.ini"); // assets 폴더에 있는 test.ini를 open
            Ini iniFile = new Ini(inputStream); // ini4j 라이브러리에서 test.ini를 읽음
        } catch (IOException e) {
            DLog.handleException(e);
            return false; // 실패하면 false 반환
        }
        return true; // 성공하면 true 반환
    }

    private void InitLogic() {
        String userName = "";
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            // Установите ClassLoader, который может быть использован ini4j
            Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

            Wini w = new Wini(new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/settings.ini"));
            userName = w.get("client", "name");
            w.store();
        } catch (Exception e) {
            DLog.handleException(e);
        } finally {
            // Восстановить ClassLoader
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
        nickname.setText(userName);
    }

    public boolean checkValidNick(View inflate) {
        EditText nick = inflate.findViewById(R.id.account_text);
        if (nick.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Введите ник", Toast.LENGTH_SHORT).show();
            return false;
        }
		/*if(!(nick.getText().toString().contains("_"))){
			Toast.makeText(getActivity(), "Ник должен содержать символ \"_\"", Toast.LENGTH_SHORT).show();
			return false;
		}*/
        if (nick.getText().toString().length() < 4) {
            Toast.makeText(getActivity(), "Длина ника должна быть не менее 4 символов", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}