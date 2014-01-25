package com.ridteam.mafiahelper.dialogs;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ridteam.mafiahelper.MafiaHelperApplication;
import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.database.MafiaHelperTables;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolePropertiesColumns;
import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.model.RoleProperty;
import com.ridteam.mafiahelper.utils.ImageUtils;

public class AddRoleDialogFragment extends DialogFragment {
	public static final String TAG = "addPlayerDialogFragment";
	public static final String ROLE_ID = "roleId";
	
	public static AddRoleDialogFragment create(long roleId) {
		AddRoleDialogFragment fragment = new AddRoleDialogFragment();
		
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

	private CheckBox mCanShoot;
	private CheckBox mCanHeal;
	private CheckBox mCanMute;
	private CheckBox mGiveAlibi;
	
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

		mCanShoot = (CheckBox) view.findViewById(R.id.cbCanShoot);
		mCanHeal = (CheckBox) view.findViewById(R.id.cbCanHeal);
		mCanMute = (CheckBox) view.findViewById(R.id.cbCanMute);
		mGiveAlibi = (CheckBox) view.findViewById(R.id.cbGiveAlibi);
		
		View buttonOk = view.findViewById(R.id.btnOk);
		View buttonCancel = view.findViewById(R.id.btnCancel);
		
		mPicture.setOnClickListener(mPictureClickListener);
		buttonOk.setOnClickListener(mOkClickListener);
		buttonCancel.setOnClickListener(mCancelClickListener);
		
		if(mRoleId != 0) {
			IBaseModel model = MafiaHelperApplication.getBaseModel(getActivity());
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
				
				Cursor roleOptions = model.getRolePropertiesLoader(mRoleId).loadInBackground();
				int propertyTypeIndex = roleOptions.getColumnIndex(RolePropertiesColumns.TYPE);
				if(roleOptions.moveToFirst()) do {
					int propertyType = roleOptions.getInt(propertyTypeIndex);
					switch (propertyType) {
					case RoleProperty.CAN_SHOOT:
						mCanShoot.setChecked(true);
						break;
					case RoleProperty.CAN_HEAL:
						mCanHeal.setChecked(true);
						break;
					case RoleProperty.CAN_MUTE:
						mCanMute.setChecked(true);
						break;
					case RoleProperty.GIVE_ALIBI:
						mGiveAlibi.setChecked(true);
						break;
					}
				} while(roleOptions.moveToNext());
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
	
	private OnClickListener mCancelClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			dismiss();
		}
	};
	
	private OnClickListener mOkClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			IBaseModel model = MafiaHelperApplication.getBaseModel(getActivity());
			if(model != null) {
				String roleName = mName.getText().toString();
				String rolrDesc = mDescription.getText().toString();
				int roleSide = mSide.getSelectedItemPosition();
				
				if(mRoleId != 0) {
					model.editRole(mRoleId, roleName, rolrDesc, roleSide, mPictureUri);
					model.removeRolePropertysByRole(mRoleId);
				}
				else model.addRole(roleName, rolrDesc, roleSide, mPictureUri);
				
				if(mRoleId != 0) {
					if(mCanShoot.isChecked()) model.addRoleProperty(mRoleId, RoleProperty.CAN_SHOOT, 1);
					if(mCanHeal.isChecked()) model.addRoleProperty(mRoleId, RoleProperty.CAN_HEAL, 1);
					if(mCanMute.isChecked()) model.addRoleProperty(mRoleId, RoleProperty.CAN_MUTE, 1);
					if(mGiveAlibi.isChecked()) model.addRoleProperty(mRoleId, RoleProperty.GIVE_ALIBI, 1);
				}
				
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
