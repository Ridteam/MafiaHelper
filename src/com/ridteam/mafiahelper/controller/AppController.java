package com.ridteam.mafiahelper.controller;

import android.view.Menu;

import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.views.IView;

public class AppController extends BaseController implements IAppController{
	private int mScene;
	private IView mView;

	public AppController(IBaseModel model, IView view) {
		super(model);
		mView = view;
	}

	@Override
	public void handleSetSceneClick(int sceneId) {
		setScene(sceneId);
	}

	@Override
	public void setScene(int sceneId) {
		mScene = sceneId;
		mView.setScene(sceneId);
	}

	@Override
	public int getScene() {
		return mScene;
	}

	@Override
	public int getMenuResId() {
		return R.menu.menu_main;
	}

	private static final int[] GROUPS_LIST = {R.id.action_group_players, R.id.action_group_roles};
	@Override
	public void prepareMenu(Menu menu) {
		int groupId = 0;
		switch (mScene) {
		case IView.SCENE_PLAYERS_LIST:
			groupId = R.id.action_group_players;
			break;
		case IView.SCENE_ROLES_LIST:
			groupId = R.id.action_group_roles;
			break;
		}
		for(int i = 0; i < GROUPS_LIST.length; i++)
			menu.setGroupVisible(GROUPS_LIST[i], GROUPS_LIST[i] == groupId);
	}

	@Override
	public int getContextMenuResId() {
		switch (mScene) {
		case IView.SCENE_PLAYERS_LIST:
			return R.menu.context_menu_players_list;
		case IView.SCENE_ROLES_LIST:
			return R.menu.context_menu_roles_list;
		}
		return R.menu.menu_empty;
	}

}
