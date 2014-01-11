package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IBaseModel;

public class BaseController implements IController {
	
	private IBaseModel mModel;
	
	public BaseController(IBaseModel model) {
		mModel = model;
	}
	
	@Override
	public IBaseModel getModel() {
		return mModel;
	}

}
