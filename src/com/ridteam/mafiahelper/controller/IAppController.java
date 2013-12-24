package com.ridteam.mafiahelper.controller;


public interface IAppController extends IController {
	
	public void handleSetSceneClick(int sceneId);
	
	public void setScene(int sceneId);
	
	public int getScene();
	public int getMenuResId();
	public int getContextMenuResId();
}
