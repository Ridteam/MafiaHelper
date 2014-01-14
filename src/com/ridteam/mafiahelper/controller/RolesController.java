package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IBaseModel;

public class RolesController extends BaseController implements IRolesController {

	public RolesController(IBaseModel model) {
		super(model);
	}

	@Override
	public void addRole(String name, String desc, int side, String picture) {
		getModel().addRole(name, desc, side, picture);
	}

	@Override
	public void editRole(long roleId, String name, String desc, int side,
			String picture) {
		getModel().editRole(roleId, name, desc, side, picture);
	}

	@Override
	public void deleteRole(long roleId) {
		getModel().removeRole(roleId);
	}
}
