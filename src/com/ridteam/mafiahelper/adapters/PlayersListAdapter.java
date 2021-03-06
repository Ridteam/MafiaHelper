package com.ridteam.mafiahelper.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.database.MafiaHelperTables;
import com.ridteam.mafiahelper.utils.ImageUtils;

public class PlayersListAdapter extends ContextedCursorAdapter<PlayersListAdapter.ViewHolder> {
	private int mIdIndex;
	private int mUserNameIndex;
	private int mUserPictureIndex;
	private int mRoleNameIndex;
	private int mRolePictureIndex;

	public PlayersListAdapter(Context context, Cursor cursor) {
		super(context, cursor, R.layout.item_add_players, 0);
	}
	
	void bindHolder(ViewHolder holder, Context context, Cursor cursor) {
		String userName = cursor.getString(mUserNameIndex);
		String roleName = cursor.getString(mRoleNameIndex);
		String userPicture = cursor.getString(mUserPictureIndex);
		String rolePicture = cursor.getString(mRolePictureIndex);
		
		holder.userName.setText(userName);
		holder.roleName.setText(roleName);
		ImageUtils.setImage(holder.userPicture, ImageUtils.USERS_FOLDER, userPicture, context);
		ImageUtils.setImage(holder.rolePicture, ImageUtils.ROLES_FOLDER, rolePicture, context);
		
		@SuppressWarnings("unchecked")
		ContextHolder contextHolder = (ContextHolder) holder.menuButton.getTag();
		contextHolder.position = cursor.getPosition();
		contextHolder.id = cursor.getLong(mIdIndex);
	}

	@Override
	ViewHolder newHolder(View view) {
		ViewHolder holder = new ViewHolder(view);
		holder.menuButton.setOnClickListener(mMenuClickListener);
		return holder;
	}
	
	void findColumns() {
		Cursor cursor = getCursor();
		if(cursor != null) {
			mIdIndex = cursor.getColumnIndex("_id");
			mUserNameIndex = cursor.getColumnIndex(MafiaHelperTables.PlayersColumns.NAME);
			mUserPictureIndex = cursor.getColumnIndex(MafiaHelperTables.PlayersColumns.PICTURE);
			mRoleNameIndex = cursor.getColumnIndex(MafiaHelperTables.PlayersColumns.ROLE_NAME);
			mRolePictureIndex = cursor.getColumnIndex(MafiaHelperTables.PlayersColumns.ROLE_PICTURE);
		}
	}
	
	private OnClickListener mMenuClickListener = new OnClickListener() {
		@Override
		public void onClick(View viev) {
			@SuppressWarnings("unchecked")
			ContextHolder contextHolder = (ContextHolder) viev.getTag();
			handleContextButtonClick(viev, contextHolder);
		}
	};
	
	class ViewHolder {
		public final TextView userName;
		public final TextView roleName;
		public final ImageView userPicture;
		public final ImageView rolePicture;
		public final View menuButton;
		
		public ViewHolder(View view) {
			userName = (TextView) view.findViewById(R.id.txtUserName);
			roleName = (TextView) view.findViewById(R.id.txtRoleName);
			userPicture = (ImageView) view.findViewById(R.id.imgUserPicture);
			rolePicture = (ImageView) view.findViewById(R.id.imgRolePicture);
			menuButton = (TextView) view.findViewById(R.id.btnMenu);
			menuButton.setTag(new ContextHolder());
			menuButton.setOnClickListener(mMenuClickListener);
			menuButton.setFocusable(false);
		}
	}
}