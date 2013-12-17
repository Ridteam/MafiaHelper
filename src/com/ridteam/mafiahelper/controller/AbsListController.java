package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IModel;
import com.ridteam.mafiahelper.views.IListView;

/**
 * Abstract controller of IListController interface that implement getDataBase method.
 * 
 * @author Shurygin Denis
 *
 */
public abstract class AbsListController implements IListController {
	private IModel mModel;
	private IListView mView;
	
	public AbsListController(IModel model) {
		mModel = model;
	}
	
	@Override
	public IModel getModel() {
		return mModel;
	}

	@Override
	public void setView(IListView view) {
		mView = view;
	}
	
	public IListView getView() {
		return mView;
	}

	@Override
	public boolean sendMessage(int message) {
		return false;
	}

	@Override
	public boolean sendMessage(int message, Object data) {
		return false;
	}

}
