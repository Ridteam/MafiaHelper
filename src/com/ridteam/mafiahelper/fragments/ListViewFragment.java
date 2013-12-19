package com.ridteam.mafiahelper.fragments;

import android.content.DialogInterface.OnClickListener;
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
		if(getView() != null && getListView() != null)
			getListView().setOnItemClickListener(mController);
		if(getListAdapter() != null)
			getListAdapter().setOnContextMenuButtonClickListener(mController);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setOnItemClickListener(mController);
	}

	@Override
	public void setAdapter(IListAdapter adapter) {
		super.setListAdapter(adapter);
		adapter.setOnContextMenuButtonClickListener(mController);
	}
	
	@Override
	public void setListAdapter(ListAdapter adapter) {
		if(!(adapter instanceof IListAdapter))
			throw new UnsupportedOperationException("Use setAdapter(IListAdapter adapter)");
		super.setListAdapter(adapter);
	}
	
	public IListAdapter getListAdapter() {
		return (IListAdapter) super.getListAdapter();
	}

	@Override
	public void showContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		// TODO show context menu
	}

	@Override
	public void showDialog(String title, String message,
			OnClickListener positiveClickListener,
			OnClickListener negativeClickListener) {
		OkCancelDialogFragment newFragment = new OkCancelDialogFragment();
	    newFragment.setPropertys(title, message);
	    newFragment.setPositiveClickListener(positiveClickListener);
	    newFragment.setNegativeClickListener(negativeClickListener);
	    newFragment.show(getFragmentManager(), OkCancelDialogFragment.TAG);
	}

}
