package com.ridteam.mafiahelper.controller;

import android.content.DialogInterface;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;

import com.ridteam.mafiahelper.model.IModel;
import com.ridteam.mafiahelper.views.IListView;


public class AddPlayersController extends AbsListController {
	
	public AddPlayersController(IModel model, IListView view) {
		super(model, view);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		getView().showDialog("Some title", "Some message", null, null);
	}

	@Override
	public void onContextMenuButtonClick(View button, final ContextMenuInfo menuInfo) {
		//TODO create context menu and perform getView().showContextMenu(menu, viev, menuInfo)
		getView().showDialog("Some title", "Some message from menu", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				long id = ((AdapterView.AdapterContextMenuInfo) menuInfo).id;
				getView().showDialog("Click", "ID is:" + id, null, null);
			}
		}, null);
	}

}