package com.ridteam.mafiahelper.dialogs;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.controller.IRolesController;
import com.ridteam.mafiahelper.database.MafiaHelperTables;
import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.utils.ImageUtils;

public class AddRoleDialogFragment extends DialogFragment {
	public static final String TAG = "addPlayerDialogFragment";
	public static final String ROLE_ID = "roleId";
	
	public static AddRoleDialogFragment create(IRolesController controller, long roleId) {
		AddRoleDialogFragment fragment = new AddRoleDialogFragment();
		fragment.setController(controller);
		
		Bundle args = new Bundle();
		args.putLong(ROLE_ID, roleId);
		fragment.setArguments(args);
		
		return fragment;
	}

	private long mRoleId;
	private TextView mName;
	private TextView mDescription;
	private ImageView mPicture;
	private Spinner mSide;
	private String mPictureUri;
	private IRolesController mController;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if(args != null) mRoleId = args.getLong(ROLE_ID, 0);
		else mRoleId = 0;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_add_role, container, false);
		
		mName = (TextView) view.findViewById(R.id.edRoleName);
		mDescription = (TextView) view.findViewById(R.id.edRoleDescription);
		mPicture = (ImageView) view.findViewById(R.id.imgRolePicture);
		mSide = (Spinner) view.findViewById(R.id.spinSide);
		View buttonOk = view.findViewById(R.id.btnOk);
		View buttonCancel = view.findViewById(R.id.btnCancel);
		
		mPicture.setOnClickListener(mPictureClickListener);
		buttonOk.setOnClickListener(mOkClickListener);
		buttonCancel.setOnClickListener(mCancelClickListener);
		
		if(mRoleId != 0) {
			IBaseModel model = mController.getModel();
			Cursor role = model.getRoleLoader(mRoleId).loadInBackground();
			if(role.moveToFirst()) {
				String name = role.getString(role.getColumnIndex(MafiaHelperTables.RolesColumns.NAME));
				String desc = role.getString(role.getColumnIndex(MafiaHelperTables.RolesColumns.DESC));
				String picture = role.getString(role.getColumnIndex(MafiaHelperTables.RolesColumns.PICTURE));
				int side = role.getInt(role.getColumnIndex(MafiaHelperTables.RolesColumns.SIDE));
				
				mName.setText(name);
				mDescription.setText(desc);
				ImageUtils.setImage(mPicture, ImageUtils.ROLES_FOLDER, picture, getActivity());
				mSide.setSelection(side);
			}
			else {
				mRoleId = 0;
			}
		}
		
		return view;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		if(mRoleId != 0) dialog.setTitle(R.string.dialog_edit_role_title);
		else dialog.setTitle(R.string.dialog_add_role_title);
		return dialog;
	}
	
	public void setController(IRolesController controller) {
		mController = controller;
	}
	
	private OnClickListener mCancelClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			dismiss();
		}
	};
	
	private OnClickListener mOkClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(mController != null) {
				String roleName = mName.getText().toString();
				String rolrDesc = mDescription.getText().toString();
				int roleSide = mSide.getSelectedItemPosition();
				
				if(mRoleId != 0) mController.editRole(mRoleId, roleName, rolrDesc, roleSide, mPictureUri);
				else mController.addRole(roleName, rolrDesc, roleSide, mPictureUri);
				
			}
			dismiss();
		}
	};
	
	private OnClickListener mPictureClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//TODO
		}
	};
	
}
