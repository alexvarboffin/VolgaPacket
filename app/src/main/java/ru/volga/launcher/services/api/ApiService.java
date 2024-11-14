package ru.volga.launcher.services.api;

import ru.volga.launcher.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService instance;

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://volgarp.online")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public int version_launcher;
    public String version_modpack;
    public String url_cache;
    public String url_client;

    private ApiService() {
    }

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public Api getApiService() {
        return this.retrofit.create(Api.class);
    }
}