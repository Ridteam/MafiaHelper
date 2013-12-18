package com.ridteam.mafiahelper.adapters;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;

/**
 * Loader for loading cursor by CursorLoader and swapping it in
 * CursorAdapter after loading.
 * 
 * @author Shurygin Denis
 * 
 */
public class CursorAdapterLoader implements LoaderCallbacks<Cursor> {
	private CursorLoader mLoader;
	private CursorAdapter mAdapter;
	
	public CursorAdapterLoader(CursorLoader loader, CursorAdapter adapter) {
		if(loader == null)
			throw new NullPointerException("Loader shoul be not null.");
		if(adapter == null)
			throw new NullPointerException("Adapter shoul be not null.");
		mLoader = loader;
		mAdapter = adapter;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return mLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		swapCursor(null);
	}
	
	private void swapCursor(Cursor cursor) {
		Cursor oldCursor = mAdapter.swapCursor(cursor);
		if(oldCursor != null)
			oldCursor.close();
	}

}
