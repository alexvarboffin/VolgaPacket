package ru.volga.launcher.services.api;
/**********************
 *******************
 WEIKTON x ERROR
 *******************
 **********************/
import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("LAUNCHER_VERSION")
    private int LAUNCHER_VERSION;

    @SerializedName("MODPACK_VERSION")
    private String MODPACK_VERSION;

    @SerializedName("URL_GAME_FILES")
    private String URL_GAME_FILES;

    @SerializedName("URL_CLIENT")
    private String URL_CLIENT;
    
    public Links()
    {
    }
    public final String getUrlFiles() {
        return this.URL_GAME_FILES;
    }
    public final String getUrlClient() {
        return this.URL_CLIENT;
    }
    public final String getVersionModPack() {
        return this.MODPACK_VERSION;
    }
    public final int getVersionLauncher()
    {
        return this.LAUNCHER_VERSION;
    }
}
/**********************
 *******************
 WEIKTON x ERROR
 *******************
 **********************/