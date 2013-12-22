package com.ridteam.mafiahelper.controller;

import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.views.IView;

public class AppController extends BaseController implements IAppController{
	private int mScene;
	

	public AppController(IBaseModel model, IView view) {
		super(model, view);
	}

	@Override
	public void handleSetSceneClick(int sceneId) {
		setScene(sceneId);
	}

	@Override
	public void setScene(int sceneId) {
		mScene = sceneId;
		getView().setScene(sceneId);
	}

	@Override
	public int getScene() {
		return mScene;
	}

	@Override
	public int getMenuResId() {
		switch (mScene) {
		case IView.SCENE_PLAYERS_LIST:
			return R.menu.menu_players_list;
		case IView.SCENE_ROLES_LIST:
			return R.menu.menu_roles_list;
		}
		return R.menu.menu_empty;
	}

}
