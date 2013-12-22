package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.views.IView;

public class BaseController implements IController {
	
	private IBaseModel mModel;
	private IView mView;
	
	public BaseController(IBaseModel model, IView view) {
		mModel = model;
		mView = view;
	}
	
	@Override
	public IBaseModel getModel() {
		return mModel;
	}
	
	@Override
	public IView getView() {
		return mView;
	}

}
