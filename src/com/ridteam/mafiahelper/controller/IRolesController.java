package com.ridteam.mafiahelper.controller;


public interface IRolesController extends IController {
	
	public void addRole(String name, String desc, int side, String picture);
	public void editRole(long roleId, String name, String desc, int side, String picture);
	public void deleteRole(long roleId);
}
