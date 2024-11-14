package ru.volga.launcher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import ru.volga.launcher.adapter.ServersAdapter;
import ru.volga.launcher.model.Servers;
import ru.volga.launcher.other.Lists;
import ru.volga.online.R;

public class ServerSelectFragment extends BaseFragment {

    ImageView close;

    RecyclerView recyclerServers;
    DatabaseReference databaseServers;
    ServersAdapter serversAdapter;
    ArrayList<Servers> slist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_server_select, container, false);

        close = inflate.findViewById(R.id.btn_close);
        close.setOnClickListener(v -> {
           notifyServerClose();
        });

        recyclerServers = inflate.findViewById(R.id.serverlist_recycler);
		recyclerServers.setHasFixedSize(true);
		LinearLayoutManager layoutManagerr = new LinearLayoutManager(getActivity());
		recyclerServers.setLayoutManager(layoutManagerr);

		this.slist = Lists.slist;
		serversAdapter = new ServersAdapter(getContext(), this.slist, this);
		recyclerServers.setAdapter(serversAdapter);

        return inflate;
    }
}
