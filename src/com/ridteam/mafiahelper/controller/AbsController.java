package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.database.IDataBase;

public abstract class AbsController implements IController{
	private IDataBase mDataBase;
	
	public AbsController(IDataBase dataBase) {
		mDataBase = dataBase;
	}
	
	@Override
	public IDataBase getDataBase() {
		return mDataBase;
	}

}
