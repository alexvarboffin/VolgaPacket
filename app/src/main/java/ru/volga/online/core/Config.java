package ru.volga.online.core;

import ru.volga.launcher.services.api.ApiService;

public class Config {
    public static final String GAME_PATH = "/storage/emulated/0/VolgaOnline/";
    public static final String URL_SERVERS = "http://cdn.volgarp.online/api/servers.json";
    public static final String URL_NEWS = "http://cdn.volgarp.online/api/stories.json";
    public static final String URL_FILES = ApiService.getInstance().url_cache;
    public static final String URL_FILES_UPDATE = "https://san-web.host/upd.zip";
    public static final String URL_CLIENT = "https://san-web.host/launcher.apk";
    public static final String URL_DONATE = "https://san-web.host/donate";
}