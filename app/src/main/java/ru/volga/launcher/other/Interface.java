package ru.volga.launcher.other;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.volga.launcher.model.News;
import ru.volga.launcher.model.Servers;

public interface Interface {


    @GET(ru.volga.online.core.Config.URL_SERVERS)
    Call<List<Servers>> getServers();
    @GET(ru.volga.online.core.Config.URL_NEWS)
    Call<List<News>> getNews();

}
