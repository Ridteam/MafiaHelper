package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.database.IDataBase;
import com.ridteam.mafiahelper.views.IListView;

/**
 * Abstract controller of IListController interface that implement getDataBase method.
 * 
 * @author Shurygin Denis
 *
 */
public abstract class AbsListController implements IListController {
	private IDataBase mDataBase;
	private IListView mView;
	
	public AbsListController(IDataBase dataBase) {
		mDataBase = dataBase;
	}
	
	@Override
	public IDataBase getDataBase() {
		return mDataBase;
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
