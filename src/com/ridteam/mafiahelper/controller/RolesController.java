package com.ridteam.mafiahelper.controller;

import android.widget.AdapterView.AdapterContextMenuInfo;

import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.views.IView;

public class RolesController extends BaseController implements IRolesController {

	public RolesController(IBaseModel model, IView view) {
		super(model, view);
	}

	@Override
	public void handleContextMenuClick(AdapterContextMenuInfo menuInfo) {
		getView().showDeleteRoleDialog(menuInfo.id);
	}

	@Override
	public void handleAddRoleClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleEditRoleClick(long roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDeleteRoleClick(long roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRole(String name, String desc, int side, String picture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editRole(long roleId, String name, String desc, int side,
			String picture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRole(long roleId) {
		// TODO Auto-generated method stub
		
	}
}
