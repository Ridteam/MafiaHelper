package com.ridteam.mafiahelper.fragments;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public abstract class ListViewFragment extends ListFragment {
	private OnItemClickListener mOnItemClickListener;
	
	public void setOnItemClickListener(OnItemClickListener listener) {
		mOnItemClickListener = listener;
		if(getView() != null && getListView() != null)
			getListView().setOnItemClickListener(mOnItemClickListener);
	}

	public LoaderManager getSupportLoaderManager() {
		return ((FragmentActivity) getActivity()).getSupportLoaderManager();
	}
	
	public void showContextPopupMenu(final AdapterContextMenuInfo menuInfo) {
		PopupMenu popupMenu = new PopupMenu(getActivity(), menuInfo.targetView);
		onCreateContextPopupMenu(popupMenu.getMenuInflater(), popupMenu.getMenu(), menuInfo);
		popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				return onContextPopupMenuClick(menuItem, menuInfo);
			}
		});
		popupMenu.show();
		
	}

	public abstract void onCreateContextPopupMenu(MenuInflater inflater, Menu menu, AdapterContextMenuInfo menuInfo);
	public abstract boolean onContextPopupMenuClick(MenuItem item, AdapterContextMenuInfo menuInfo);

}
