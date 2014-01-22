package com.ridteam.mafiahelper.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.ridteam.mafiahelper.MafiaHelperApplication;
import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.activitys.RoleDetailsActivity;
import com.ridteam.mafiahelper.adapters.CursorAdapterLoader;
import com.ridteam.mafiahelper.adapters.IContextedAdapter.OnContextButtonClickListener;
import com.ridteam.mafiahelper.adapters.RolesListAdapter;
import com.ridteam.mafiahelper.model.IBaseModel;

public class SelectRoleDialogFragment extends DialogFragment implements OnClickListener, OnContextButtonClickListener {
	public static final String TAG = "SelectRoleDialogFragment";
	public static final String PLAYER_ID = "PlayerId";
	
	private IBaseModel mBaseModel;
	private RolesListAdapter mAdapter;
	
	private long mPlayerId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mBaseModel = MafiaHelperApplication.getBaseModel(activity);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mAdapter = new RolesListAdapter(getActivity(), null, R.layout.item_select_role);
		mAdapter.setOnContextButtonClickListener(this);
		CursorAdapterLoader loaderCallback = new CursorAdapterLoader(mBaseModel.getRolesLoader(), mAdapter);
		getLoaderManager().destroyLoader(0);
		getLoaderManager().initLoader(0, null, loaderCallback);
		
		Builder builder = new AlertDialog.Builder(getActivity());
		builder.setAdapter(mAdapter, this);
		builder.setTitle(R.string.dialog_select_role_title);
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		long roleId = mAdapter.getItemId(which);
		mBaseModel.setRole(mPlayerId, roleId);
	}
	
	public void setPlayerId(long id) {
		mPlayerId = id;
	}

	@Override
	public void onContextButtonClick(AdapterContextMenuInfo menuInfo) {
		RoleDetailsActivity.show(getActivity(), menuInfo.id);
	}
}
