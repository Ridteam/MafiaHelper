package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.database.IDataBase;

public abstract class AbsListController implements IListController {
	private IDataBase mDataBase;
	
	public AbsListController(IDataBase dataBase) {
		mDataBase = dataBase;
	}
	
	@Override
	public IDataBase getDataBase() {
		return mDataBase;
	}

}
