package com.ridteam.mafiahelper.views;

import android.view.ContextMenu;
import android.widget.AdapterView;

public interface IView {
	public static final int SCENE_PLAYERS_LIST	= 1;
	public static final int SCENE_GAME			= 2;
	public static final int SCENE_ROLES_LIST	= 3;
	
	public void setScene(int scene);
	
	public void showContextMenu(ContextMenu menu,
			AdapterView.AdapterContextMenuInfo menuInfo);
	
	public void showAddPlayersDialog();
	public void showDeletePlayerDialog(long playerId);
	public void showSetPlayerRoleDialog(long playerId);
	public void showSetPlayerPictureDialog(long playerId);
}
