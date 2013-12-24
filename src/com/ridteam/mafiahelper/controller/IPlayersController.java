package com.ridteam.mafiahelper.controller;

import android.widget.AdapterView.AdapterContextMenuInfo;

public interface IPlayersController extends IController, IListController {
	
	public void handleContextMenuClick(AdapterContextMenuInfo menuInfo);
	public void handleAddPlayerClick();
	public void handleDeletePlayerClick(long playerId);
	public void handleSetRoleClick(long playerId);
	public void handleSetPictureClick(long playerId);
	
	public void addPlayer(String name);
	public void deletePlayer(long playerId);
	public void setRole(long playerId, long roleId);
	public void setPicture(long playerId, String picture);
}
