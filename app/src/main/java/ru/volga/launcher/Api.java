package ru.volga.launcher;


import retrofit2.Call;
import retrofit2.http.GET;
import ru.volga.launcher.services.api.Links;


public interface Api {
    @GET("http://cdn.volgarp.online/api/api.json")
    Call<Links> getLinks();
}
