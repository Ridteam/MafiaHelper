package com.ridteam.mafiahelper.controller;

import android.view.Menu;


public interface IAppController extends IController {
	
	public void handleSetSceneClick(int sceneId);
	
	public void setScene(int sceneId);
	
	public int getScene();
	public int getMenuResId();
	public void prepareMenu(Menu menu);
	public int getContextMenuResId();
}
