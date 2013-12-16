package com.ridteam.mafiahelper.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;

import com.ridteam.mafiahelper.adapters.AddPlayersAdapter;
import com.ridteam.mafiahelper.controller.AddPlayersController;
import com.ridteam.mafiahelper.controller.IListController;
import com.ridteam.mafiahelper.database.IDataBase;

public class AddPlayersFragment extends ListFragment {
	private IDataBase mDataBase;
	private IListController mController;
	private CursorAdapter mAdapter;
	
	public void setModel(IDataBase dataBase) {
		mDataBase = dataBase;
		mController = new AddPlayersController(mDataBase);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Cursor cursor = mDataBase.getPlayers(getActivity().getBaseContext());
		mAdapter = new AddPlayersAdapter(getActivity(), cursor, 0);
		setListAdapter(mAdapter);
		getListView().setOnItemClickListener(mController);
		getListView().setOnItemLongClickListener(mController);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		if(mAdapter != null) mAdapter.getCursor().close();
	}

}
