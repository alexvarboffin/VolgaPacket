package ru.volga.launcher.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.volga.launcher.activity.OnServerSelectListener;
import ru.volga.launcher.adapter.ServersAdapter;
import ru.volga.utils.DLog;

public class BaseFragment extends Fragment implements ServersAdapter.AdapterCallback {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DLog.d(getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        DLog.d(getClass().getSimpleName());
    }


    private OnServerSelectListener callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnServerSelectListener) {
            callback = (OnServerSelectListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnServerSelectListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public void notifyServerClose() {
        if (callback != null) {
            callback.closeServers();
        }
    }

    public void replaceServers() {
        if (callback != null) {
            callback.replaceServers();
        }
    }

    public void replaceSettings() {
        if (callback != null) {
            callback.replaceSettings();
        }
    }

    public void onClickPlay() {
        if (callback != null) {
            callback.onClickPlay();
        }
    }
}
