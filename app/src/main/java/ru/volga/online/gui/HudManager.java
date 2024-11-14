package ru.volga.online.gui;

import android.app.Activity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nvidia.devtech.NvEventQueueActivity;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Formatter;

import ru.volga.online.R;
import ru.volga.online.gui.chatedgar.ChatManager;
import ru.volga.online.gui.keyboard.KeyBoard0;
import ru.volga.online.gui.util.Utils;

public class HudManager {
    public Activity activity;
    public FrameLayout btn_shop, alt_layout, ctrl_layout, gift_layout, btn_shop_x2, happy_layout, wanted_layout, hud_weapon_ammo, hud_weapon_slot, weapon_gz_layout;
    public FrameLayout hud_layout, bonus_view;
    public TextView hud_money, quest_btn_optional, edgar, progressWanted, progressammo, text_notif, text_work;
    public TextView fname_text, fdate_text, fserver_text;
    public LinearLayout quest_layout, quest_btn_hide, armour_layout;

    public LinearLayout info_layout, click_layout;

    public FrameLayout btn_quest, btn_setting, hud_chat_btn, work_layout;

    public FrameLayout btn_bp, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7;


    public ImageView hud_weapon, chat_icon, weapon_ammo_image;

    public ProgressBar progressHP, progressEat, progressArmour;

    public static String text;
    int q = 0;

    public HudManager(Activity aactivity) {
        activity = aactivity;
        hud_layout = aactivity.findViewById(R.id.hud_view);

        bonus_view = aactivity.findViewById(R.id.bonus_view);
        //bonus_exit = aactivity.findViewById(R.id.bonus_exit);

        info_layout = aactivity.findViewById(R.id.info_layout);
        click_layout = aactivity.findViewById(R.id.click_layout);

        progressHP = aactivity.findViewById(R.id.stat_progress);
        progressEat = aactivity.findViewById(R.id.stat_progress_1);
        progressArmour = aactivity.findViewById(R.id.stat_progress_2);

        wanted_layout = aactivity.findViewById(R.id.wanted_layout);
        progressWanted = aactivity.findViewById(R.id.wanted_text);

        edgar = aactivity.findViewById(R.id.quest_text);
        edgar.setText(
                null//@@@@??? MainActivity.getMainActivity().text6
        );

        fname_text = activity.findViewById(R.id.fname_text);
        fdate_text = activity.findViewById(R.id.fdate_text);
        fserver_text = activity.findViewById(R.id.fserver_text);

        text_notif = activity.findViewById(R.id.gift_text);
        text_work = activity.findViewById(R.id.work_text);

        hud_money = aactivity.findViewById(R.id.money_text);
        quest_btn_optional = aactivity.findViewById(R.id.quest_btn_optional);
        quest_btn_hide = aactivity.findViewById(R.id.quest_btn_hide);
        hud_weapon = aactivity.findViewById(R.id.weapon_melee_image);
        hud_weapon_slot = aactivity.findViewById(R.id.weapon_melee_layout);

        weapon_gz_layout = aactivity.findViewById(R.id.weapon_gz_layout);

        hud_weapon_ammo = aactivity.findViewById(R.id.weapon_ammo_layout);
        progressammo = aactivity.findViewById(R.id.weapon_ammo_text);
        weapon_ammo_image = aactivity.findViewById(R.id.weapon_ammo_image);

        btn_quest = aactivity.findViewById(R.id.btn_quest);
        quest_layout = aactivity.findViewById(R.id.quest_layout);
        armour_layout = aactivity.findViewById(R.id.stat_2);
        btn_shop = aactivity.findViewById(R.id.btn_shop);
        gift_layout = aactivity.findViewById(R.id.gift_layout);
        ctrl_layout = aactivity.findViewById(R.id.ctrl_layout);
        alt_layout = aactivity.findViewById(R.id.alt_layout);
        work_layout = aactivity.findViewById(R.id.work_layout);
        happy_layout = aactivity.findViewById(R.id.happy_layout);
        btn_shop_x2 = aactivity.findViewById(R.id.btn_shop_x2);
        btn_bp = aactivity.findViewById(R.id.btn_bp);
        btn_setting = aactivity.findViewById(R.id.btn_setting);
        btn_0 = aactivity.findViewById(R.id.btn_0);
        btn_1 = aactivity.findViewById(R.id.btn_1);
        btn_2 = aactivity.findViewById(R.id.btn_2);
        btn_3 = aactivity.findViewById(R.id.btn_3);
        btn_4 = aactivity.findViewById(R.id.btn_4);
        btn_5 = aactivity.findViewById(R.id.btn_5);
        btn_6 = aactivity.findViewById(R.id.btn_6);
        btn_7 = aactivity.findViewById(R.id.btn_7);

        hud_chat_btn = aactivity.findViewById(R.id.hud_chat_btn);
        chat_icon = aactivity.findViewById(R.id.chat_icon);

        ctrl_layout.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/launcherctrlmenu".getBytes(StandardCharsets.UTF_8));

        });
        alt_layout.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/launcheraltmenu".getBytes(StandardCharsets.UTF_8));

        });
        gift_layout.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/launcheraltmenu".getBytes(StandardCharsets.UTF_8));

        });
        work_layout.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/launcheractivejob".getBytes(StandardCharsets.UTF_8));

        });

        btn_shop.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/donate".getBytes(StandardCharsets.UTF_8));

        });

        btn_bp.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/rating".getBytes(StandardCharsets.UTF_8));
        });

        btn_setting.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            //NvEventQueueActivity.getInstance().sendCommand("/hudmanager".getBytes(StandardCharsets.UTF_8));
            NvEventQueueActivity.getInstance().showClientSettings();
        });

		btn_0.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/gps".getBytes(StandardCharsets.UTF_8));
            //NvEventQueueActivity.getInstance().togglePlayer(1);
        });
		
        btn_1.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            //NvEventQueueActivity.getInstance().sendCommand("/mm".getBytes(StandardCharsets.UTF_8));
            NvEventQueueActivity.getInstance().showMenuu();
            NvEventQueueActivity.getInstance().togglePlayer(1);
        });
		
		btn_2.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/cars".getBytes(StandardCharsets.UTF_8));
            //NvEventQueueActivity.getInstance().togglePlayer(1);
        });
        btn_3.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/apanel".getBytes(StandardCharsets.UTF_8));
            //NvEventQueueActivity.getInstance().togglePlayer(1);
        });
        btn_4.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/inv".getBytes(StandardCharsets.UTF_8));
            //NvEventQueueActivity.getInstance().togglePlayer(1);
        });
        btn_5.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/anim".getBytes(StandardCharsets.UTF_8));
            //NvEventQueueActivity.getInstance().togglePlayer(1);
        });
        btn_6.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/reps".getBytes(StandardCharsets.UTF_8));
            //NvEventQueueActivity.getInstance().togglePlayer(1);
        });
        btn_7.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            NvEventQueueActivity.getInstance().sendCommand("/asks".getBytes(StandardCharsets.UTF_8));
            //NvEventQueueActivity.getInstance().togglePlayer(1);
        });
        //Бонусы
        happy_layout.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.ShowLayout(bonus_view, true);
            HideHud();
            ChatManager.getChatManager().setChatStatys(3);
            NvEventQueueActivity.getInstance().togglePlayer(1);

        });
        aactivity.findViewById(R.id.bonus_select_1).setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
            NvEventQueueActivity.getInstance().sendCommand("/bonuses".getBytes(StandardCharsets.UTF_8));
        });
        aactivity.findViewById(R.id.bonus_select_2).setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
            NvEventQueueActivity.getInstance().sendCommand("/bonush".getBytes(StandardCharsets.UTF_8));
        });
        aactivity.findViewById(R.id.bonus_select_3).setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
            NvEventQueueActivity.getInstance().sendCommand("/mybonus".getBytes(StandardCharsets.UTF_8));
        });
        aactivity.findViewById(R.id.bonus_select_4).setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
            NvEventQueueActivity.getInstance().sendCommand("/bonusexp".getBytes(StandardCharsets.UTF_8));
        });
        aactivity.findViewById(R.id.bonus_select_5).setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
            NvEventQueueActivity.getInstance().sendCommand("/happy".getBytes(StandardCharsets.UTF_8));
        });
        aactivity.findViewById(R.id.bonus_select_6).setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
            NvEventQueueActivity.getInstance().sendCommand("/levelup".getBytes(StandardCharsets.UTF_8));
        });
        aactivity.findViewById(R.id.bonus_select_7).setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
            NvEventQueueActivity.getInstance().sendCommand("/bonusmoney".getBytes(StandardCharsets.UTF_8));
        });
        aactivity.findViewById(R.id.bonus_select_8).setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(aactivity, R.anim.button_click));
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
            NvEventQueueActivity.getInstance().sendCommand("/bonusloto".getBytes(StandardCharsets.UTF_8));
        });
        aactivity.findViewById(R.id.bonus_menu_exit).setOnClickListener(view -> {
            Utils.HideLayout(bonus_view, false);
            ShowHud();
            ChatManager.getChatManager().setChatStatys(1);
            NvEventQueueActivity.getInstance().togglePlayer(0);
        });
        //Конец бонусы

        /*quest_btn_hide.setOnClickListener(v -> {
            hud_layou.setVisibility(View.VISIBLE);
            quest_layout.setVisibility(View.GONE);
        });*/

        /*quest_btn_optional.setOnClickListener(v -> {
            hud_layou.setVisibility(View.VISIBLE);
            quest_layout.setVisibility(View.GONE);
        });*/

        /*btn_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hud_layou.setVisibility(View.GONE);
                quest_layout.setVisibility(View.VISIBLE);
            }
        });*/

        hud_chat_btn.setOnClickListener( v -> {
            int i;
            if(ChatManager.statusChat == 3)
            {
                i = 1;
            } else i = ChatManager.statusChat + 2;
            ChatManager.getChatManager().setChatStatys(i);
            chat_icon.setImageResource(i == 1 ? R.drawable.ic_hud_chat_active : i == 2 ? R.drawable.ic_hud_chat_middle : R.drawable.ic_hud_chat_inactive);
        });
        hud_chat_btn.setOnTouchListener(new KeyBoard0.clicabilel(aactivity, hud_chat_btn));
        Utils.HideLayout(hud_layout, true);
    }

    public void UpdateHudInfo(int health, int armour, int eat, int weaponidweik, int ammo, int playerid, int money, int wanted)
    {
        progressHP.setProgress(health);

        progressEat.setProgress(eat);

        progressArmour.setProgress(armour);
        if(armour > 0)
        {
            armour_layout.setVisibility(View.VISIBLE);
        }
        else armour_layout.setVisibility(View.GONE);

        DecimalFormat formatter=new DecimalFormat();
        DecimalFormatSymbols symbols= DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        String s= formatter.format(money);
        hud_money.setText(s);

        int id = activity.getResources().getIdentifier(new Formatter().format("weapon_%d", Integer.valueOf(weaponidweik)).toString(), "drawable", activity.getPackageName());
        hud_weapon.setImageResource(id);

        int ida = activity.getResources().getIdentifier(new Formatter().format("weapon_%d", Integer.valueOf(weaponidweik)).toString(), "drawable", activity.getPackageName());
        weapon_ammo_image.setImageResource(ida);

        if(ammo > 0)
        {
            hud_weapon_ammo.setVisibility(View.VISIBLE);
            hud_weapon_slot.setVisibility(View.GONE);
            String sammo= formatter.format(ammo);
            progressammo.setText(sammo);
        }
        else {
            hud_weapon_ammo.setVisibility(View.GONE);
            hud_weapon_slot.setVisibility(View.VISIBLE);
        }
        hud_weapon.setOnClickListener(v -> NvEventQueueActivity.getInstance().onWeaponChanged());
        hud_weapon_ammo.setOnClickListener(v -> NvEventQueueActivity.getInstance().onWeaponChanged());
        //
        if(wanted > 0)
        {
            wanted_layout.setVisibility(View.VISIBLE);
            String ss= formatter.format(wanted);
            progressWanted.setText(ss);
        }
        else wanted_layout.setVisibility(View.GONE);
    }

    public void ShowGps()
    {

    }

    public void HideGps()
    {

    }

    public void ShowX2()
    {
        btn_shop_x2.setVisibility(View.VISIBLE);
    }

    public void HideX2()
    {
        btn_shop_x2.setVisibility(View.GONE);
    }

    public void ShowZona(String text)
    {
        gift_layout.setVisibility(View.VISIBLE);
        this.text = text;
        this.text_notif.setText(this.text);
    }

    public void HideZona()
    {
        gift_layout.setVisibility(View.GONE);
    }

    public void ShowWork(String text)
    {
        work_layout.setVisibility(View.VISIBLE);
        this.text = text;
        this.text_work.setText(this.text);
    }

    public void HideWork()
    {
        work_layout.setVisibility(View.GONE);
    }

    public void ShowHappy()
    {
        happy_layout.setVisibility(View.VISIBLE);
    }

    public void HideHappy()
    {
        happy_layout.setVisibility(View.GONE);
    }

    public void ShowAlogin()
    {
        btn_3.setVisibility(View.VISIBLE);
    }

    public void HideAlogin()
    {
        btn_3.setVisibility(View.GONE);
    }

    public void ShowAdmReps()
    {
        btn_6.setVisibility(View.VISIBLE);
    }

    public void HideAdmReps()
    {
        btn_6.setVisibility(View.GONE);
    }

    public void ShowAdmAsk()
    {
        btn_7.setVisibility(View.VISIBLE);
    }

    public void HideAdmAsk()
    {
        btn_7.setVisibility(View.GONE);
    }

    public void ShowRadar()
    {

    }

    public void HideRadar()
    {

    }

    public void ShowHud()
    {
        Utils.ShowLayout(hud_layout, true);
    }

    public void HideHud()
    {
        Utils.HideLayout(hud_layout, false);
    }
    public void ShowHudAndLogo()
    {
        hud_layout.setVisibility(View.VISIBLE);
    }

    public void HideHudAndLogo()
    {
        hud_layout.setVisibility(View.GONE);
    }
    /*public void ShowZZ()
    {
        Utils.ShowLayout(hud_weapon_slot, true);
        Utils.ShowLayout(hud_weapon_ammo, true);
        Utils.ShowLayout(weapon_gz_layout, false);
    }

    public void HideZZ()
    {
        Utils.HideLayout(hud_weapon_slot, false);
        Utils.ShowLayout(hud_weapon_ammo, false);
        Utils.ShowLayout(weapon_gz_layout, true);
    }*/
    public void SetShowNameID(String text)
    {
        this.text = text;
        this.fname_text.setText(this.text);
    }
    public void SetFootDate(String text)
    {
        this.text = text;
        this.fdate_text.setText(this.text);
    }
    public void SetFootServer(String text)
    {
        this.text = text;
        this.fserver_text.setText(this.text);
    }

}
