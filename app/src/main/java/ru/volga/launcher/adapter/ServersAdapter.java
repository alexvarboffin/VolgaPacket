package ru.volga.launcher.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.ini4j.Wini;

import java.io.File;
import java.util.ArrayList;

import ru.volga.launcher.model.Servers;
import ru.volga.online.R;
import ru.volga.utils.DLog;

public class ServersAdapter extends RecyclerView.Adapter<ServersAdapter.ServersViewHolder> {


    public interface AdapterCallback{

        void notifyServerClose();
    }


    private final AdapterCallback clb;
    Context context;

    ArrayList<Servers> slist;

    public ServersAdapter(Context context, ArrayList<Servers> slist, AdapterCallback clb) {
        this.context = context;
        this.slist = slist;
        this.clb = clb;
    }

    @NonNull
    @Override
    public ServersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.serverlist_item, parent, false);
        return new ServersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ServersViewHolder holder, int position) {
        Servers servers = slist.get(position);
        holder.ponka.setColorFilter(Color.parseColor("#" + servers.getColor()), PorterDuff.Mode.SRC_ATOP);
        holder.people.setColorFilter(Color.parseColor("#" + servers.getColor()), PorterDuff.Mode.SRC_ATOP);
        holder.backColor.setColorFilter(Color.parseColor("#" + servers.getColor()), PorterDuff.Mode.SRC_ATOP);
        holder.name.setText(servers.getname());
        holder.textonline.setText("Низкая плотность");
        //holder.textonline.setText(Integer.toString(servers.getOnline()) + "/" + Integer.toString(servers.getmaxOnline()));
	   /* holder.progressBar.setProgressStartColor(Color.parseColor("#" + servers.getColor()));
		holder.progressBar.setProgressEndColor(Color.parseColor("#" + servers.getColor()));
		holder.progressBar.setProgress(servers.getOnline());
		holder.progressBar.setMax(servers.getmaxOnline());*/
        boolean status = servers.getRecommend();
        DLog.d("бля pon ==== " + status);
        if (status) {
            holder.server_recommend_text.setVisibility(View.VISIBLE);
            holder.backColor.setVisibility(View.VISIBLE);
        } else {
            holder.server_recommend_text.setVisibility(View.GONE);
            holder.backColor.setVisibility(View.GONE);
        }
        holder.container.setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.button_click));
            if(clb!=null){
                clb.notifyServerClose();
            }
            try {
                Wini w = new Wini(new File(Environment.getExternalStorageDirectory() + "/VolgaOnline/SAMP/local.ini"));
                w.put("server", "server", 1);
                w.put("server", "name", servers.getname());
                w.put("server", "color", servers.getColor());
                w.put("server", "maxonline", servers.getmaxOnline());
                w.put("server", "online", servers.getOnline());
                w.put("server", "volga_host", servers.getHost());
                w.put("server", "volga_port", servers.getPort());
                w.put("server", "id", servers.getId());
                w.store();
            } catch (Exception e) {
                DLog.handleException(e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return slist.size();
    }

    public static class ServersViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout container;
        ImageView ponka, people;
        TextView name, textonline, server_recommend_text;
        ImageView backColor;

        public ServersViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.server_item_name);
            server_recommend_text = itemView.findViewById(R.id.server_recommend_text);
            ponka = itemView.findViewById(R.id.server_item_background);
            people = itemView.findViewById(R.id.server_item_image);
            textonline = itemView.findViewById(R.id.server_item_online);
            backColor = itemView.findViewById(R.id.server_recommend_card);
            container = itemView.findViewById(R.id.edgar_con);
        }
    }

}