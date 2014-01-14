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
	private int mRoleDescIndex;
	private int mRolePictureIndex;

	public RolesListAdapter(Context context, Cursor cursor) {
		super(context, cursor, R.layout.item_roles_list, 0);
	}
	
	void bindHolder(ViewHolder holder, Context context, Cursor cursor) {
		String roleName = cursor.getString(mRoleNameIndex);
		String description = cursor.getString(mRoleDescIndex);
		String rolePicture = cursor.getString(mRolePictureIndex);

		holder.roleName.setText(roleName);
		holder.description.setText(description);
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
			mRoleDescIndex = cursor.getColumnIndex(MafiaHelperTables.RolesColumns.DESC);
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
		public final TextView roleName;
		public final ImageView rolePicture;
		public final TextView description;
		public final View menuButton;
		
		public ViewHolder(View view) {
			roleName = (TextView) view.findViewById(R.id.txtRoleName);
			rolePicture = (ImageView) view.findViewById(R.id.imgRolePicture);
			description = (TextView) view.findViewById(R.id.txtDesc);
			menuButton = (TextView) view.findViewById(R.id.btnMenu);
			menuButton.setTag(new ContextHolder());
			menuButton.setOnClickListener(mMenuClickListener);
			menuButton.setFocusable(false);
		}
	}
}
