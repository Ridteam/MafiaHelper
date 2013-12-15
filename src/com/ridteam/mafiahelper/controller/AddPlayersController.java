package com.ridteam.mafiahelper.controller;

import android.view.View;
import android.widget.AdapterView;

import com.ridteam.mafiahelper.database.IDataBase;


public class AddPlayersController extends AbsListController {
	
	public AddPlayersController(IDataBase database) {
		super(database);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendMessage(int message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendMessage(int message, Object data) {
		// TODO Auto-generated method stub
		return false;
	}

}
