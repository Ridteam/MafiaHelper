package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.database.IDataBase;

public interface IController {
	public IDataBase getDataBase();
	public boolean sendMessage(int message);
	public boolean sendMessage(int message, Object data);
}
