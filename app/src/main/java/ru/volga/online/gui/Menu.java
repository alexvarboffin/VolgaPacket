package ru.volga.online.gui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nvidia.devtech.NvEventQueueActivity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ru.volga.online.R;
import ru.volga.online.gui.adapters.DialogMenuAdapter;
import ru.volga.online.gui.chatedgar.ChatManager;
import ru.volga.online.gui.models.DataDialogMenu;
import ru.volga.online.gui.util.Utils;
import ru.volga.utils.DLog;

public class Menu {
    public View mRootView;
    public Activity activity;
    public LinearLayout menu_layout;
    public TextView menuTitle;
    private final Animation anim;
    private int index = -1;
    private final ArrayList<DataDialogMenu> dataDialogMenuArrayList = new ArrayList<>();

    @SuppressLint("InflateParams")
    public Menu(Activity aactivity) {

        activity = aactivity;
        anim = AnimationUtils.loadAnimation(aactivity, R.anim.button_click);
        menu_layout = aactivity.findViewById(R.id.main_menu_layout_new_layout);
        aactivity.findViewById(R.id.bonus_exit).setOnClickListener(view -> {
            close();
        });
        this.mRootView = ((LayoutInflater) aactivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.menu_action_dialog, null, false);
        Utils.HideLayout(menu_layout,false);
    }

    public void Update(boolean z) {
        RecyclerView recyclerView = activity.findViewById(R.id.br_rec_view_menu);
        /*if (this.index == -1) {
            TransitionManager.beginDelayedTransition(recyclerView);
        }*/
        this.index = -1;
        this.menuTitle = activity.findViewById(R.id.br_menu_title);
        if (!z) {
            setMenu();
            this.menuTitle.setText("Меню");
            setDataInRecyclerView((dataDialogMenu, view) -> {
                index = dataDialogMenu.getId();
                view.startAnimation(anim);
                new Handler().postDelayed(() -> {
                    if (index == 398) {
                        Update(true);
                    } else {
                        try {
                            NvEventQueueActivity.getInstance().sendRPC(1, String.valueOf(index).getBytes("windows-1251"), index);
                            //Toast.makeText(activity, String.valueOf(index), Toast.LENGTH_SHORT).show();
                            close();
                        } catch (UnsupportedEncodingException e) {
                            DLog.handleException(e);
                        }
                    }
                }, 300);
            }, this.dataDialogMenuArrayList, recyclerView, mRootView, 4);
            return;
        }
        setDialogMenu();
        this.menuTitle.setText("Общение");
        setDataInRecyclerView((dataDialogMenu, view) -> {
            index = dataDialogMenu.getId();
            view.startAnimation(anim);
            new Handler().postDelayed(() -> {
                if (index == 411) {
                    Update(false);
                } else {
                    try {
                        NvEventQueueActivity.getInstance().sendRPC(1, String.valueOf(index).getBytes("windows-1251"), index);
                        close();
                    } catch (UnsupportedEncodingException e) {
                        DLog.handleException(e);
                    }
                }

            }, 300);
        }, this.dataDialogMenuArrayList, recyclerView, mRootView, 3);
    }

    public void ShowMenu()
    {
        Update(false);
        Utils.ShowLayout(menu_layout, true);
        NvEventQueueActivity.getInstance().hideHud();
        ChatManager.getChatManager().setChatStatys(3);
        //ChatManager.getChatManager().ChatClose();

    }

    private void setMenu() {
        this.dataDialogMenuArrayList.clear();
        this.dataDialogMenuArrayList.add(new DataDialogMenu(398, R.drawable.ic_radial_icon_01, "Действие"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(399, R.drawable.ic_radial_icon_22, "Статистика"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(400, R.drawable.ic_buttnpanel_settings, "Настройки"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(401, R.drawable.ic_radial_icon_07, "Донат"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(402, R.drawable.ic_radial_icon_37, "Рейтинг"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(403, R.drawable.ic_speed_in_alarm_active, "Жалоба"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(404, R.drawable.ic_um_help_ask, "Вопрос"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(405, R.drawable.ic_radial_icon_06, "Промо"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(406, R.drawable.ic_keyboard_cmd, "Команды"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(407, R.drawable.ic_um_help_rules, "Правила"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(408, R.drawable.ic_um_help_rules, "Помощь"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(409, R.drawable.ic_um_settings_security, "Безопасность"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(410, R.drawable.ic_family_main_info, "Информация"));
    }

    private void setDialogMenu() {
        this.dataDialogMenuArrayList.clear();
        this.dataDialogMenuArrayList.add(new DataDialogMenu(411, R.drawable.ic_radial_icon_36, "Назад"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(412, R.drawable.ic_radial_icon_03, "Передать паспорт"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(413, R.drawable.ic_radial_icon_03, "Передать мед.карту"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(414, R.drawable.ic_radial_icon_03, "Передать лицензии"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(415, R.drawable.ic_radial_icon_03, "Передать ПТС"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(416, R.drawable.ic_radial_icon_03, "Показать навыки"));
        this.dataDialogMenuArrayList.add(new DataDialogMenu(417, R.drawable.ic_radial_icon_03, "Передать в.билет"));
        //this.dataDialogMenuArrayList.add(new DataDialogMenu(12, R.drawable.menu_exchange, "Совершить обмен"));
    }

    private void setDataInRecyclerView(DialogMenuAdapter.OnUserClickListener onUserClickListener, ArrayList<DataDialogMenu> arrayList, RecyclerView recyclerView, final View view, int i) {
        DialogMenuAdapter dialogMenuAdapter = new DialogMenuAdapter(arrayList, onUserClickListener);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), i) {
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
                float f = 30.0f / view.getResources().getDisplayMetrics().density;
                int i2 = (int) f;
                layoutParams.setMarginStart(i2);
                layoutParams.setMarginEnd(i2);
                layoutParams.setMargins(0, i2, 0, 0);
                layoutParams.width = (int) (((float) (getWidth() / getSpanCount())) - f);
                return true;
            }
        });
        recyclerView.setAdapter(dialogMenuAdapter);
    }

    public void close() {
        Utils.HideLayout(menu_layout, true);
        NvEventQueueActivity.getInstance().togglePlayer(0);
        NvEventQueueActivity.getInstance().showHud();
        ChatManager.getChatManager().setChatStatys(1);
    }
}