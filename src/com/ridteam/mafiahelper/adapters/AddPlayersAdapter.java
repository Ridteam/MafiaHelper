package com.ridteam.mafiahelper.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayersTable;

public class AddPlayersAdapter extends CursorAdapter {
	private LayoutInflater mInflater;
	private int mNameFieldIndex;
	private int mImageFieldIndex;

	public AddPlayersAdapter(Context context, Cursor cursor, int flags) {
		super(context, cursor, flags);
		mInflater = LayoutInflater.from(context);
		mNameFieldIndex = cursor.getColumnIndex(PlayersTable.NAME);
		mImageFieldIndex = cursor.getColumnIndex(PlayersTable.PICTURE);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mInflater.inflate(R.layout.item_add_players, null);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();
		if(holder == null) {
			holder = new ViewHolder(view);
			view.setTag(holder);
		}
		holder.name.setText(cursor.getString(mNameFieldIndex));
	}
	
	private class ViewHolder {
		public final ImageView image;
		public final TextView name;
		
		public ViewHolder(View view) {
			this.image = (ImageView) view.findViewById(R.id.imgUserImage);
			this.name = (TextView) view.findViewById(R.id.txtUserName);
		}
	}
}