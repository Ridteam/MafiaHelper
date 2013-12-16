package com.ridteam.mafiahelper.controller;

import android.widget.AdapterView.OnItemClickListener;

import com.ridteam.mafiahelper.adapters.IListAdapter.OnContextMenuButtonClickListener;
import com.ridteam.mafiahelper.views.IListView;

/**
 * Controller interface for list.
 * 
 * @author Shurygin Denis
 *
 */
public interface IListController extends IController, OnItemClickListener, OnContextMenuButtonClickListener {
	public void setView(IListView view);
}
