package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IModel;

/**
 * Abstract controller of IController interface that implement getDataBase method.
 * 
 * @author Shurygin Denis
 *
 */
public abstract class AbsController implements IController{
	private IModel mDataBase;
	
	public AbsController(IModel dataBase) {
		mDataBase = dataBase;
	}
	
	@Override
	public IModel getModel() {
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
