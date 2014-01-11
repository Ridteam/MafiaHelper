package com.ridteam.mafiahelper.controller;


public interface IPlayersController extends IController {
	
	public void addPlayer(String name);
	public void deletePlayer(long playerId);
	public void setRole(long playerId, long roleId);
	public void setPicture(long playerId, String picture);
}
