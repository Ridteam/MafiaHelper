package com.ridteam.mafiahelper.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewFragment extends ListFragment {
	private OnItemClickListener mOnItemClickListener;
	
	public void setOnItemClickListener(OnItemClickListener listener) {
		mOnItemClickListener = listener;
		if(getView() != null && getListView() != null)
			getListView().setOnItemClickListener(mOnItemClickListener);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setOnItemClickListener(mOnItemClickListener);
	}

}
