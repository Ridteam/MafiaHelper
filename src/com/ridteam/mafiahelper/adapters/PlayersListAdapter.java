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
import com.ridteam.mafiahelper.database.MafiaHelperTables;
import com.ridteam.mafiahelper.utils.ImageUtils;

public class PlayersListAdapter extends CursorAdapter implements IListAdapter {
	private LayoutInflater mInflater;
	private int mIdIndex;
	private int mUserNameIndex;
	private int mUserPictureIndex;
	private int mRoleNameIndex;
	private int mRolePictureIndex;
	
	private OnContextButtonClickListener mOnContextMenuButtonClickListener;

	public PlayersListAdapter(Context context, Cursor cursor) {
		super(context, cursor, 0);
		mInflater = LayoutInflater.from(context);
		findColumns();
	}
	
    @Override
    public Cursor swapCursor(Cursor cursor) {
        Cursor res = super.swapCursor(cursor);
        findColumns();
        return res;
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
		
		bindHolder(holder, context, cursor);
	}

	@Override
	public void setOnContextButtonClickListener(
			OnContextButtonClickListener onContextMenuButtonClickListener) {
		mOnContextMenuButtonClickListener = onContextMenuButtonClickListener;
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
		
		MenuButtonHolder menuHolder = (MenuButtonHolder) holder.menuButton.getTag();
		menuHolder.position = cursor.getPosition();
		menuHolder.id = cursor.getLong(mIdIndex);
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
			if(mOnContextMenuButtonClickListener != null) {
				MenuButtonHolder menuHolder = (MenuButtonHolder) viev.getTag();
				AdapterView.AdapterContextMenuInfo menuInfo = new AdapterView.AdapterContextMenuInfo(viev, menuHolder.position, menuHolder.id);
				mOnContextMenuButtonClickListener.onContextButtonClick(menuInfo);
			}
		}
	};
	
	private class ViewHolder {
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
			menuButton.setTag(new MenuButtonHolder());
			menuButton.setOnClickListener(mMenuClickListener);
			menuButton.setFocusable(false);
		}
	}
	
	private class MenuButtonHolder {
		public int position;
		public long id;
	}
}