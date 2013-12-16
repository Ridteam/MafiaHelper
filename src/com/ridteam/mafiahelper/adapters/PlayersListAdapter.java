package com.ridteam.mafiahelper.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.database.IPlayers;
import com.ridteam.mafiahelper.utils.ImageUtils;

public class PlayersListAdapter extends CursorAdapter implements IListAdapter {
	private LayoutInflater mInflater;
	private int mIdIndex;
	private int mUserNameIndex;
	private int mUserImageIndex;
	private int mRoleNameIndex;
	private int mRoleImageIndex;
	
	private OnContextMenuButtonClickListener mOnContextMenuButtonClickListener;

	public PlayersListAdapter(Context context, Cursor cursor) {
		super(context, cursor, 0);
		mInflater = LayoutInflater.from(context);
		mIdIndex = cursor.getColumnIndex("_id");
		mUserNameIndex = cursor.getColumnIndex(IPlayers.NAME);
		mUserImageIndex = cursor.getColumnIndex(IPlayers.IMAGE);
		mRoleNameIndex = cursor.getColumnIndex(IPlayers.ROLE_NAME);
		mRoleImageIndex = cursor.getColumnIndex(IPlayers.ROLE_IMAGE);
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
			holder.menuButton.setOnClickListener(mMenuClickListener);
			view.setTag(holder);
		}
		
		String userName = cursor.getString(mUserNameIndex);
		String roleName = cursor.getString(mRoleNameIndex);
		String userImage = cursor.getString(mUserImageIndex);
		String roleImage = cursor.getString(mRoleImageIndex);
		
		holder.userName.setText(userName);
		holder.roleName.setText(roleName);
		ImageUtils.setImage(holder.userImage, ImageUtils.USERS_FOLDER, userImage, context);
		ImageUtils.setImage(holder.roleImage, ImageUtils.ROLES_FOLDER, roleImage, context);
		
		MenuButtonHolder menuHolder = (MenuButtonHolder) holder.menuButton.getTag();
		menuHolder.position = cursor.getPosition();
		menuHolder.id = cursor.getLong(mIdIndex);
	}
	
	private OnClickListener mMenuClickListener = new OnClickListener() {
		@Override
		public void onClick(View viev) {
			if(mOnContextMenuButtonClickListener != null) {
				MenuButtonHolder menuHolder = (MenuButtonHolder) viev.getTag();
				AdapterView.AdapterContextMenuInfo menuInfo = new AdapterView.AdapterContextMenuInfo(viev, menuHolder.position, menuHolder.id);
				mOnContextMenuButtonClickListener.onContextMenuButtonClick(viev, menuInfo);
			}
		}
	};
	
	private class ViewHolder {
		public final TextView userName;
		public final TextView roleName;
		public final ImageView userImage;
		public final ImageView roleImage;
		public final View menuButton;
		
		public ViewHolder(View view) {
			userName = (TextView) view.findViewById(R.id.txtUserName);
			roleName = (TextView) view.findViewById(R.id.txtRoleName);
			userImage = (ImageView) view.findViewById(R.id.imgUserImage);
			roleImage = (ImageView) view.findViewById(R.id.imgRoleImage);
			menuButton = (TextView) view.findViewById(R.id.btnMenu);
			menuButton.setTag(new MenuButtonHolder());
		}
	}
	
	private class MenuButtonHolder {
		public int position;
		public long id;
	}

	@Override
	public void setOnContextMenuButtonClickListener(
			OnContextMenuButtonClickListener onContextMenuButtonClickListener) {
		// TODO Auto-generated method stub
		
	}
}