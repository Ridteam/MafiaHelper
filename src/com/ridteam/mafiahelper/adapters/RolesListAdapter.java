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

public class RolesListAdapter extends ContextedCursorAdapter<RolesListAdapter.ViewHolder> {
	private int mIdIndex;
	private int mRoleNameIndex;
	private int mRoleSideIndex;
	private int mRolePictureIndex;
	
	private String[] mSides;

	public RolesListAdapter(Context context, Cursor cursor, int layout) {
		super(context, cursor, layout, 0);
		mSides = context.getResources().getStringArray(R.array.side);
	}
	
	void bindHolder(ViewHolder holder, Context context, Cursor cursor) {
		String rolePicture = cursor.getString(mRolePictureIndex);
		String roleName = cursor.getString(mRoleNameIndex);
		int sideId = cursor.getInt(mRoleSideIndex);
		String side = mSides[sideId];

		holder.roleName.setText(roleName);
		holder.side.setText(side);
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

	@Override
	void findColumns() {
		Cursor cursor = getCursor();
		if(cursor != null) {
			mIdIndex = cursor.getColumnIndex("_id");
			mRoleNameIndex = cursor.getColumnIndex(MafiaHelperTables.RolesColumns.NAME);
			mRoleSideIndex = cursor.getColumnIndex(MafiaHelperTables.RolesColumns.SIDE);
			mRolePictureIndex = cursor.getColumnIndex(MafiaHelperTables.RolesColumns.PICTURE);
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
		public final ImageView rolePicture;
		public final TextView roleName;
		public final TextView side;
		public final View menuButton;
		
		public ViewHolder(View view) {
			rolePicture = (ImageView) view.findViewById(R.id.imgRolePicture);
			roleName = (TextView) view.findViewById(R.id.txtRoleName);
			side = (TextView) view.findViewById(R.id.txtSide);
			menuButton = (TextView) view.findViewById(R.id.btnMenu);
			menuButton.setTag(new ContextHolder());
			menuButton.setOnClickListener(mMenuClickListener);
			menuButton.setFocusable(false);
		}
	}
}
