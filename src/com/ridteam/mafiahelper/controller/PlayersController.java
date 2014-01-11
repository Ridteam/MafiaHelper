package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.model.IBaseModel;


public class PlayersController extends BaseController implements IPlayersController {
	
	public PlayersController(IBaseModel model) {
		super(model);
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
		getModel().setRole(playerId, roleId);
	}

	@Override
	public void setPicture(long playerId, String picture) {
		// TODO
	}
}