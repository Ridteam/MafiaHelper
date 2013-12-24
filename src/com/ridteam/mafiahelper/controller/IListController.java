package com.ridteam.mafiahelper.controller;

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AdapterView.AdapterContextMenuInfo;

public interface IListController {
	public void createContextMenu(MenuInflater inflater, Menu menu, AdapterContextMenuInfo menuInfo);
}
