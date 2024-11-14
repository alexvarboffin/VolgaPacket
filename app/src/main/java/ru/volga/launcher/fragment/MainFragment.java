package ru.volga.launcher.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import ru.volga.launcher.adapter.NewsAdapter;
import ru.volga.launcher.model.News;
import ru.volga.launcher.other.Lists;
import ru.volga.launcher.services.api.ApiService;
import ru.volga.online.R;
import ru.volga.utils.DLog;
import ru.volga.utils.GameUtils;

public class MainFragment extends BaseFragment {

    ConstraintLayout select_server_layout;

    int pon, lite;
    String name;
    String hex;
    int max;
    String online;
    int id;
    //
    String name_client;

    public ImageView settings, social_vk;

    TextView textserver, name_launcher, serverinfo_onlinee, serverinfo_version;
    CircularProgressBar progressBar;
    ImageView server_background;

    RecyclerView recyclerNews;
    DatabaseReference databaseNews;
    NewsAdapter newsAdapter;
    //ApiAdapter apiAdapter;
    ArrayList<News> nlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main, container, false);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.button_click);

        select_server_layout = inflate.findViewById(R.id.select_server_layout);
        textserver = inflate.findViewById(R.id.serverinfo_name);
        progressBar = inflate.findViewById(R.id.serverinfo_online_bar);
        server_background = inflate.findViewById(R.id.server_background);
        name_launcher = inflate.findViewById(R.id.serverinfo_person_name);
        serverinfo_onlinee = inflate.findViewById(R.id.serverinfo_online);
        serverinfo_version = inflate.findViewById(R.id.text_versionl);

        try {
            if (GameUtils.IsGameInstalled()) {

                Wini client = new Wini(new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/settings.ini"));
                //
                //val file = File(folderPath, filename);
                Wini w = new Wini(new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/local.ini"));

                pon = Integer.parseInt(w.get("server", "server"));
                name = w.get("server", "name");
                hex = w.get("server", "color");
                max = Integer.parseInt(w.get("server", "maxonline"));
                online = w.get("server", "online");
                lite = Integer.parseInt(w.get("server", "online"));
                id = Integer.parseInt(w.get("server", "id"));
                //
                name_client = client.get("client", "name");
                w.store();
                //
                if (pon == 0) {
                    inflate.findViewById(R.id.serverinfo_layout).setVisibility(View.GONE);
                    //inflate.findViewById(R.id.bonus_layout).setVisibility(8);
                    inflate.findViewById(R.id.select_layout).setVisibility(View.VISIBLE);
                } else {
                    inflate.findViewById(R.id.serverinfo_layout).setVisibility(View.VISIBLE);
                    //inflate.findViewById(R.id.bonus_layout).setVisibility(0);
                    inflate.findViewById(R.id.select_layout).setVisibility(View.GONE);
                    name_launcher.setText(name_client);
                    textserver.setText(name);
                    progressBar.setProgressBarColor(Color.parseColor("#" + hex));
                    progressBar.setProgressBarColorEnd(Color.parseColor("#" + hex));
                    progressBar.setProgress(lite);
                    progressBar.setProgressMax(max);
                    server_background.setColorFilter(Color.parseColor("#" + hex), PorterDuff.Mode.SRC_ATOP);
                    serverinfo_onlinee.setText(online);
                }
                client.store();
            } else {
                installGame(inflate);
            }
        } catch (IOException e) {
            DLog.handleException(e);
        }

        select_server_layout.setOnClickListener(view -> {
            select_server_layout.startAnimation(animation);
            replaceServers();
        });

        settings = inflate.findViewById(R.id.btn_settings);

        settings.setOnClickListener(v -> {
            v.startAnimation(animation);
            replaceSettings();
        });

        social_vk = inflate.findViewById(R.id.btn_social_vk);
        social_vk.setOnClickListener(v -> {
            v.startAnimation(animation);
            Toast.makeText(getContext(), "Новая версия " + ApiService.getInstance().version_launcher, Toast.LENGTH_SHORT).show();

        });

        inflate.findViewById(R.id.btn_play).setOnClickListener(v -> {
            v.startAnimation(animation);
            if (pon == 1) {
                onClickPlay();
            } else {
                Toast.makeText(getContext(), "Выберите сервер!", Toast.LENGTH_SHORT).show();
            }

        });

        inflate.findViewById(R.id.btn_bonus).setOnClickListener(v -> {
            v.startAnimation(animation);
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://volgarp.online/")));

        });
        inflate.findViewById(R.id.btn_cabinet).setOnClickListener(v -> {
            v.startAnimation(animation);
            replaceSettings();
            //startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://volgarp.online/")));
        });

        recyclerNews = inflate.findViewById(R.id.story_recycler);
        recyclerNews.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerNews.setLayoutManager(layoutManager);

        this.nlist = Lists.nlist;
        newsAdapter = new NewsAdapter(getContext(), this.nlist);
        recyclerNews.setAdapter(newsAdapter);
        return inflate;
    }

    private void installGame(View inflate) {

        ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();
        File mm = new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/local.ini");
        try {
            Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());
//            Wini w = new Wini(mm);
//            pon = Integer.parseInt(w.get("server", "server"));
//            name = w.get("server", "name");
//            hex = w.get("server", "color");
//            max = Integer.parseInt(w.get("server", "maxonline"));
//            online = w.get("server", "online");
//            lite = Integer.parseInt(w.get("server", "online"));
//            id = Integer.parseInt(w.get("server", "id"));
            //w.store();


            Map<String, Map<String, String>> iniData = IniParser.parseIniFile(mm);

            if (iniData.containsKey("server")) {
                Map<String, String> serverSection = iniData.get("server");
                pon = Integer.parseInt(serverSection.get("server"));
                name = serverSection.get("name");
                hex = serverSection.get("color");
                max = Integer.parseInt(serverSection.get("maxonline"));
                online = serverSection.get("online");
                lite = Integer.parseInt(serverSection.get("online"));
                id = Integer.parseInt(serverSection.get("id"));
            }

            DLog.d("бля pon ==== " + pon);
            if (pon == 0) {
                inflate.findViewById(R.id.serverinfo_layout).setVisibility(View.GONE);
                //inflate.findViewById(R.id.bonus_layout).setVisibility(8);
                inflate.findViewById(R.id.select_layout).setVisibility(View.VISIBLE);
            } else {
                inflate.findViewById(R.id.serverinfo_layout).setVisibility(View.VISIBLE);
                //inflate.findViewById(R.id.bonus_layout).setVisibility(0);
                inflate.findViewById(R.id.select_layout).setVisibility(View.GONE);
                name_launcher.setText("Установите игру");
                textserver.setText(name);
                progressBar.setProgressBarColor(Color.parseColor("#" + hex));
                progressBar.setProgressBarColorEnd(Color.parseColor("#" + hex));
                progressBar.setProgress(lite);
                progressBar.setProgressMax(max);
                server_background.setColorFilter(Color.parseColor("#" + hex), PorterDuff.Mode.SRC_ATOP);
                serverinfo_onlinee.setText(online);
            }
        } catch (Exception er) {
            DLog.handleException(er);
            DLog.d(String.valueOf(mm.exists()) + " " + mm.getAbsolutePath());
        } finally {
            Thread.currentThread().setContextClassLoader(currentThreadClassLoader);
        }
    }
}