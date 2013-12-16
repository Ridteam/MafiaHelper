package com.ridteam.mafiahelper.views;

import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

import com.ridteam.mafiahelper.adapters.IListAdapter;
import com.ridteam.mafiahelper.controller.IListController;

public interface IListView {
	public void setController(IListController controller);
	public void setListAdapter(IListAdapter adapter);
	public void showContextMenu(ContextMenu menu, View viev, ContextMenuInfo menuInfo);
}
