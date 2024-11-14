package com.liverussia.launcher.services.model;
/**********************
 *******************
 WEIKTON x ERROR
 *******************
 **********************/
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servers {
	@SerializedName("action")
	@Expose
	private boolean action;
	@SerializedName("color")
	@Expose
	private String color;

	@SerializedName("dopname")
	@Expose
	private String dopname;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("online")
	@Expose
	private int online;

	@SerializedName("maxonline")
	@Expose
	private int maxonline;

	@SerializedName("lock")
	@Expose
	private boolean lock;

	public Servers(String color, String dopname, String name, int online, int maxonline, boolean action, boolean lock) {
		this.color = color;
		this.dopname = dopname;
		this.name = name;
		this.online = online;
		this.maxonline = maxonline;
		this.action = action;
		this.lock = lock;
	}

	public String getname() {
		return name;
	}

	public String getDopname() {
		return dopname;
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
	public boolean getLock() { return lock; }
	public boolean getAction() { return action; }
}
/**********************
 *******************
 WEIKTON x ERROR
 *******************
 **********************/