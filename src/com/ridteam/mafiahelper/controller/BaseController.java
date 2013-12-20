package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IModel;
import com.ridteam.mafiahelper.views.IView;

public class BaseController implements IController {
	
	private IModel mModel;
	private IView mView;
	
	public BaseController(IModel model, IView view) {
		mModel = model;
		mView = view;
	}
	
	@Override
	public IModel getModel() {
		return mModel;
	}
	
	@Override
	public IView getView() {
		return mView;
	}

}
