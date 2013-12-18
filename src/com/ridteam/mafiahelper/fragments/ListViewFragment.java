package com.ridteam.mafiahelper.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ListAdapter;

import com.ridteam.mafiahelper.adapters.IListAdapter;
import com.ridteam.mafiahelper.controller.IListController;
import com.ridteam.mafiahelper.views.IListView;

public class ListViewFragment extends ListFragment implements IListView {
	private IListController mController;
	
	public void setController(IListController controller) {
		mController = controller;
		if(getView() != null) {
			if(getListView() != null)
				getListView().setOnItemClickListener(mController);
			if(getListAdapter() != null)
				getListAdapter().setOnContextMenuButtonClickListener(mController);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setOnItemClickListener(mController);
	}

	@Override
	public void setListAdapter(IListAdapter adapter) {
		super.setListAdapter(adapter);
		adapter.setOnContextMenuButtonClickListener(mController);
	}
	
	public IListAdapter getListAdapter() {
		return (IListAdapter) super.getListAdapter();
	}

	@Override
	public void showContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		// TODO show context menu
	}

}
