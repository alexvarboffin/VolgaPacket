package ru.volga.launcher.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servers {

	//private static Servers instance;

	@SerializedName("versionlauncher")
	@Expose
	private int versionlauncher;

	@SerializedName("color")
	@Expose
	private String color;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("online")
	@Expose
	private int online;

	@SerializedName("maxonline")
	@Expose
	private int maxonline;

	@SerializedName("recommend")
	@Expose
	private boolean recommend;

	@SerializedName("volga_host")
	@Expose
	private String host;

	@SerializedName("volga_port")
	@Expose
	private int port;

	@SerializedName("id")
	@Expose
	private int id;

	/*public static Servers getServers() {
		return instance;
	}*/

	public Servers(int versionlauncher, String color, String name, int online, int maxonline, boolean recommend, String host, int port, int id) {
		this.versionlauncher = versionlauncher;
		this.color = color;
		this.name = name;
		this.online = online;
		this.maxonline = maxonline;
		this.recommend = recommend;
		this.host = host;
		this.port = port;
		this.id = id;
	}
	 
	public String getname() {
		return name;
	}

    public String getColor() {
		return color;
	}
	
	public int getOnline(){
		return online;
	}

	public int getmaxOnline(){
		return maxonline;
	}

	public boolean getRecommend(){
		return recommend;
	}

	public String getHost() {
		return host;
	}

	public int getPort(){
		return port;
	}

	public int getId(){
		return id;
	}

	public int getVersion(){
		return versionlauncher;
	}
}