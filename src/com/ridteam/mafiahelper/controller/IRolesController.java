package com.ridteam.mafiahelper.controller;

import android.widget.AdapterView.AdapterContextMenuInfo;

public interface IRolesController extends IController {

	public void handleContextMenuClick(AdapterContextMenuInfo menuInfo);
	public void handleAddRoleClick();
	public void handleEditRoleClick(long roleId);
	public void handleDeleteRoleClick(long roleId);
	
	public void addRole(String name, String desc, int side, String picture);
	public void editRole(long roleId, String name, String desc, int side, String picture);
	public void deleteRole(long roleId);
}
