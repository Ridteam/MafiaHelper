package com.ridteam.mafiahelper.adapters;

import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ListAdapter;

public interface IListAdapter extends ListAdapter {
	public void setOnContextMenuButtonClickListener(OnContextMenuButtonClickListener onContextMenuButtonClickListener);
	
	public interface OnContextMenuButtonClickListener {
		public void onContextMenuButtonClick(View button, ContextMenuInfo menuInfo);
	}
}
