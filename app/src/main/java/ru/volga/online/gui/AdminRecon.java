package ru.volga.online.gui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.nvidia.devtech.NvEventQueueActivity;

import ru.volga.online.R;
import ru.volga.utils.DLog;

public class AdminRecon {
    private static final int EXIT_BUTTON = 0;
    private static final int STATS_BUTTON = 1;
    private static final int FREEZE_BUTTON = 2;
    private static final int UNFREEZE_BUTTON = 3;
    private static final int SPAWN_BUTTON = 4;
    private static final int SLAP_BUTTON = 5;
    private static final int REFRESH_BUTTON = 6;
    private static final int MUTE_BUTTON = 7;
    private static final int JAIL_BUTTON = 8;
    private static final int KICK_BUTTON = 9;
    private static final int BAN_BUTTON = 10;
    private static final int WARN_BUTTON = 11;
    private static final int NEXT_BUTTON = 12;
    private static final int PREV_BUTTON = 13;
    private static final int FLIP_BUTTON = 14;

    Activity activity;
    TextView re_nickname_text;
    TextView re_id_text;
    ConstraintLayout re_main_layout;
    Button re_exit_butt;
    ConstraintLayout re_stats_button;
    ConstraintLayout re_freeze_butt;
    ConstraintLayout re_unfreeze_butt;
    ConstraintLayout re_spawn_butt;
    ConstraintLayout re_slap_butt;
    Button re_refresh_button;
    ConstraintLayout re_mute_button;
    ConstraintLayout re_jail_butt;
    ConstraintLayout re_kick_butt;
    ConstraintLayout re_ban_butt;
    ConstraintLayout re_warn_butt;
    ConstraintLayout re_prev_button;
    ConstraintLayout re_next_button;
    ConstraintLayout re_flip_butt;

    int playerid;

    public AdminRecon(Activity activity){

        this.activity = activity;
        re_main_layout = activity.findViewById(R.id.re_main_layout);
        re_main_layout.setVisibility(View.GONE);

        re_nickname_text = activity.findViewById(R.id.re_nickname_text);
        re_id_text = activity.findViewById(R.id.re_id_text);

        re_flip_butt = activity.findViewById(R.id.re_flip_butt);
        re_flip_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(FLIP_BUTTON, playerid);
            DLog.d(FLIP_BUTTON + "id:"+playerid);
        });

        re_exit_butt = activity.findViewById(R.id.re_exit_butt);
        re_exit_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(EXIT_BUTTON, playerid);
            hide();
        });

        re_stats_button = activity.findViewById(R.id.re_stats_button);
        re_stats_button.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(STATS_BUTTON, playerid);
            //NvEventQueueActivity.getInstance().sendCommand("/check".getBytes(StandardCharsets.UTF_8));
        });

        re_freeze_butt = activity.findViewById(R.id.re_freeze_butt);
        re_freeze_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(FREEZE_BUTTON, playerid);
        });

        re_unfreeze_butt = activity.findViewById(R.id.re_unfreeze_butt);
        re_unfreeze_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(UNFREEZE_BUTTON, playerid);
        });

        re_spawn_butt = activity.findViewById(R.id.re_spawn_butt);
        re_spawn_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(SPAWN_BUTTON, playerid);
        });

        re_slap_butt = activity.findViewById(R.id.re_slap_butt);
        re_slap_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(SLAP_BUTTON, playerid);
        });

        re_refresh_button = activity.findViewById(R.id.re_refresh_button);
        re_refresh_button.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(REFRESH_BUTTON, playerid);
        });

        re_mute_button = activity.findViewById(R.id.re_mute_button);
        re_mute_button.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(MUTE_BUTTON, playerid);
        });

        re_jail_butt = activity.findViewById(R.id.re_jail_butt);
        re_jail_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(JAIL_BUTTON, playerid);
        });

        re_kick_butt = activity.findViewById(R.id.re_kick_butt);
        re_kick_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(KICK_BUTTON, playerid);
        });

        re_ban_butt = activity.findViewById(R.id.re_ban_butt);
        re_ban_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(BAN_BUTTON, playerid);
        });

        re_warn_butt = activity.findViewById(R.id.re_warn_butt);
        re_warn_butt.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(WARN_BUTTON, playerid);
        });

        re_prev_button = activity.findViewById(R.id.re_prev_button);
        re_prev_button.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(PREV_BUTTON, playerid);
        });

        re_next_button = activity.findViewById(R.id.re_next_button);
        re_next_button.setOnClickListener(view -> {
            NvEventQueueActivity.getInstance().clickButton(NEXT_BUTTON, playerid);
        });
    }

    @SuppressLint("DefaultLocale")
    public void show(String nick, int id){
        activity.runOnUiThread(() -> {
            re_nickname_text.setText(nick);
            re_id_text.setText(String.format("%d", id));
            playerid = id;
            re_main_layout.setVisibility(View.VISIBLE);
        });
        NvEventQueueActivity.getInstance().hideHud();
    }

    void tempToggle(boolean toggle){
        activity.runOnUiThread(() -> {
            if(toggle) {
                re_main_layout.setVisibility(View.VISIBLE);
            }
            else {
                re_main_layout.setVisibility(View.GONE);
            }
        });
    }

    public void hide(){
        activity.runOnUiThread(() -> {
            re_main_layout.setVisibility(View.GONE);
            NvEventQueueActivity.getInstance().showHud();
        });
    }
}
