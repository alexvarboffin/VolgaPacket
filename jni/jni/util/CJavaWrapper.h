#pragma once

#include <jni.h>

#include <string>

#define EXCEPTION_CHECK(env) \
	if ((env)->ExceptionCheck()) \ 
	{ \
		(env)->ExceptionDescribe(); \
		(env)->ExceptionClear(); \
		return; \
	}

class CJavaWrapper
{
	jobject activity;

	jmethodID s_GetClipboardText;
	jmethodID s_CallLauncherActivity;

    jmethodID s_RadarBR;

	jmethodID s_ShowInputLayout;
	jmethodID s_HideInputLayout;

	jmethodID s_ShowClientSettings;
	jmethodID s_SetUseFullScreen;
	jmethodID s_MakeDialog;
	
	jmethodID s_showRecon;
	jmethodID s_hideRecon;

	jmethodID s_showHud;
    jmethodID s_hideHud;
	jmethodID s_updateHudInfo;

	jmethodID s_showradar;
    jmethodID s_hideradar;

	jmethodID s_showGps;
    jmethodID s_hideGps;
	
	jmethodID s_setShowNameID;
	jmethodID s_setFootDate;
	jmethodID s_setFootServer;

	jmethodID s_showZona;
	jmethodID s_hideZona;
	
	jmethodID s_showWork;
	jmethodID s_hideWork;

    jmethodID s_showx2;
	jmethodID s_hidex2;
	
	jmethodID s_showhappy;
	jmethodID s_hidehappy;
	
	jmethodID s_showalogin;
	jmethodID s_hidealogin;
	
	jmethodID s_showadmreps;
	jmethodID s_hideadmreps;
	
	jmethodID s_showadmask;
	jmethodID s_hideadmask;
	
	jmethodID s_showhudandlogo;
	jmethodID s_hidehudandlogo;

	jmethodID s_showSpeed;
    jmethodID s_hideSpeed;
	jmethodID s_updateSpeedInfo;

	jmethodID s_showNotification;
	jmethodID s_showMenu;
	jmethodID s_AddChatMessage;//

	jmethodID s_setPauseState;

	jmethodID s_showSplash;
	jmethodID s_updateSplash;
public:
	JNIEnv* GetEnv();

	std::string GetClipboardString();
	void CallLauncherActivity(int type);

	void ShowInputLayout();
	void HideInputLayout();

	void ShowRadar();
	void HideRadar();
	
	void ShowRecon(char* nick, int id);
	void HideRecon();

	void SetRadar();
	void AddChatMessage(const char msg[]);

	void ShowClientSettings();

	void SetUseFullScreen(int b);

	void UpdateHudInfo(int health, int armour, int hunger, int weaponidweik, int ammo, int ammoinclip, int money, int wanted);
	void ShowHud();
                  void HideHud();

	void ShowGPS();
    void HideGPS();
	
	void SetShowNameID(char* text);
	void SetFootDate(char* text);
	void SetFootServer(char* text);

	void ShowZona(char* text);
    void HideZona();
	
	void ShowWork(char* text);
    void HideWork();

	void ShowX2();
                  void HideX2();
				  
	void ShowHappy();
    void HideHappy();
				  
	void ShowAlogin();
    void HideAlogin();
	
	void ShowAdmReps();
    void HideAdmReps();
	
	void ShowAdmAsk();
    void HideAdmAsk();
	
	void ShowHudAndLogo();
    void HideHudAndLogo();

	void UpdateSpeedInfo(int speed, int fuel, int hp, int mileage, int engine, int light, int belt, int lock);
	void ShowSpeed();
                  void HideSpeed();

	void MakeDialog(int dialogId, int dialogTypeId, char* caption, char* content, char* leftBtnText, char* rightBtnText); // Диалоги
	void ShowNotification(int type, char* text, int duration, char* actionforBtn, char* textBtn, char* actionforBtn2);
	void ShowMenu();

	void SetPauseState(bool a1);

	void ShowSplash();
	void UpdateSplash(int progress, int i);

	CJavaWrapper(JNIEnv* env, jobject activity);
	~CJavaWrapper();
};

extern CJavaWrapper* g_pJavaWrapper;