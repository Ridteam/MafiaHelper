package com.ridteam.mafiahelper.controller;

import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;

import com.ridteam.mafiahelper.model.IModel;


public class AddPlayersController extends AbsListController {
	
	public AddPlayersController(IModel model) {
		super(model);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//TODO perform click
	}

	@Override
	public void onContextMenuButtonClick(View button, ContextMenuInfo menuInfo) {
		//TODO create context menu and perform getView().showContextMenu(menu, viev, menuInfo)
	}

}