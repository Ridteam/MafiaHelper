package com.ridteam.mafiahelper.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ridteam.mafiahelper.MafiaHelperApplication;
import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.database.MafiaHelperTables;
import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.utils.ImageUtils;

public class RoleDetailsFragment extends Fragment implements LoaderCallbacks<Cursor> {
	public static final String ROLE_ID = "roleId";
	
	private long mRoleId;
	private ImageView mPicture;
	private TextView mName;
	private TextView mDescription;
	private TextView mSide;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_role_details, container, false);
		
		mPicture = (ImageView) view.findViewById(R.id.imgRolePicture);
		mName = (TextView) view.findViewById(R.id.txtRoleName);
		mDescription = (TextView) view.findViewById(R.id.txtRoleDescription);
		mSide = (TextView) view.findViewById(R.id.txtSide);
		
		load();
		
		return view;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		IBaseModel model = MafiaHelperApplication.getBaseModel(getActivity());
		return model.getRoleLoader(mRoleId);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if(cursor.moveToFirst()) {
			String picture = cursor.getString(cursor.getColumnIndex(MafiaHelperTables.RolesColumns.PICTURE));
			String name = cursor.getString(cursor.getColumnIndex(MafiaHelperTables.RolesColumns.NAME));
			String desc = cursor.getString(cursor.getColumnIndex(MafiaHelperTables.RolesColumns.DESC));
			int sideId = cursor.getInt(cursor.getColumnIndex(MafiaHelperTables.RolesColumns.SIDE));
			String side = getActivity().getResources().getStringArray(R.array.side)[sideId];
			
			ImageUtils.setImage(mPicture, ImageUtils.ROLES_FOLDER, picture, getActivity());
			mName.setText(name);
			mDescription.setText(desc);
			mSide.setText(side);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// Do nothing
	}
	
	public void setRoleId(long id) {
		if(mRoleId != id) {
			mRoleId = id;
			load();
		}
	}
	
	private void load() {
		if(mRoleId != 0) {
			LoaderManager loaderManager = getLoaderManager();
			loaderManager.destroyLoader(0);
			loaderManager.initLoader(0, null, this);
		}
	}
}
