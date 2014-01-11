package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IBaseModel;

public class RolesController extends BaseController implements IRolesController {

	public RolesController(IBaseModel model) {
		super(model);
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
