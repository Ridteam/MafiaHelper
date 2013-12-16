package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.database.IDataBase;

/**
 * Abstract controller of IController interface that implement getDataBase method.
 * 
 * @author Shurygin Denis
 *
 */
public abstract class AbsController implements IController{
	private IDataBase mDataBase;
	
	public AbsController(IDataBase dataBase) {
		mDataBase = dataBase;
	}
	
	@Override
	public IDataBase getDataBase() {
		return mDataBase;
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
