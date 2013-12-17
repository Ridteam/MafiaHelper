package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IModel;

/** 
 * Controller interface for view.
 * 
 * @author Shurygin Denis
 *
 */
public interface IController {
	public static final int MESSAGE_MENU_CLICK = 101;
	
	public IModel getModel();
	public boolean sendMessage(int message);
	public boolean sendMessage(int message, Object data);
}