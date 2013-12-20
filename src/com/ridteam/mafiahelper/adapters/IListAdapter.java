package com.ridteam.mafiahelper.adapters;

import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListAdapter;

public interface IListAdapter extends ListAdapter {
	public void setOnContextButtonClickListener(OnContextButtonClickListener onContextButtonClickListener);
	
	public interface OnContextButtonClickListener {
		public void onContextButtonClick(AdapterContextMenuInfo menuInfo);
	}
}
