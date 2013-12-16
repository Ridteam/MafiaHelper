package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.database.IDataBase;

/** 
 * Controller interface for view.
 * 
 * @author Shurygin Denis
 *
 */
public interface IController {
	public static final int MESSAGE_MENU_CLICK = 101;
	
	public IDataBase getDataBase();
	public boolean sendMessage(int message);
	public boolean sendMessage(int message, Object data);
}