package com.ridteam.mafiahelper.controller;

import android.widget.AdapterView.AdapterContextMenuInfo;

import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.views.IView;


public class PlayersController extends BaseController implements IPlayersController {
	
	public PlayersController(IBaseModel model, IView view) {
		super(model, view);
	}

	@Override
	public void handleContextMenuClick(AdapterContextMenuInfo menuInfo) {
		getView().showDeletePlayerDialog(menuInfo.id);
	}

	@Override
	public void handleAddPlayerClick() {
		getView().showAddPlayersDialog();
	}

	@Override
	public void handleDeletePlayerClick(long playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSetRoleClick(long playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSetPictureClick(long playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlayer(String name) {
		if(!"".equals(name))
			getModel().addPlayer(name);
	}

	@Override
	public void deletePlayer(long playerId) {
		getModel().removePlayer(playerId);
	}

	@Override
	public void setRole(long playerId, long roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPicture(long playerId, String picture) {
		// TODO Auto-generated method stub
		
	}
}