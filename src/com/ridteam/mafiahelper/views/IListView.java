package com.ridteam.mafiahelper.views;

import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;

import com.ridteam.mafiahelper.adapters.IListAdapter;
import com.ridteam.mafiahelper.controller.IListController;

public interface IListView {
	public void setController(IListController controller);

	public void setAdapter(IListAdapter adapter);

	public void showDialog(String title, String message,
			DialogInterface.OnClickListener positiveClickListener,
			DialogInterface.OnClickListener negativeClickListener);

	public void showContextMenu(ContextMenu menu, View viev,
			ContextMenuInfo menuInfo);
}
