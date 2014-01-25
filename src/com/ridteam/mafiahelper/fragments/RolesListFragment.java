package com.ridteam.mafiahelper.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.ridteam.mafiahelper.MafiaHelperApplication;
import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.activitys.RoleDetailsActivity;
import com.ridteam.mafiahelper.adapters.CursorAdapterLoader;
import com.ridteam.mafiahelper.adapters.IContextedAdapter.OnContextButtonClickListener;
import com.ridteam.mafiahelper.adapters.RolesListAdapter;
import com.ridteam.mafiahelper.dialogs.AddPlayerDialogFragment;
import com.ridteam.mafiahelper.dialogs.AddRoleDialogFragment;
import com.ridteam.mafiahelper.dialogs.OkCancelDialogFragment;
import com.ridteam.mafiahelper.model.IBaseModel;

public class RolesListFragment extends ListViewFragment implements OnContextButtonClickListener{
	private IBaseModel mBaseModel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mBaseModel = MafiaHelperApplication.getBaseModel(activity);

		RolesListAdapter adapter = new RolesListAdapter(activity, null, R.layout.item_roles_list);
		CursorAdapterLoader loaderCallback = new CursorAdapterLoader(mBaseModel.getRolesLoader(), adapter);
		getSupportLoaderManager().destroyLoader(0);
		getSupportLoaderManager().initLoader(0, null, loaderCallback);
		
		setListAdapter(adapter);
		adapter.setOnContextButtonClickListener(this);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_roles_list, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_role:
			showAddRoleDialog();
			return true;
		}
		return false;
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		RoleDetailsActivity.show(getActivity(), id);
	}

	@Override
	public void onCreateContextPopupMenu(MenuInflater inflater, Menu menu,
			AdapterContextMenuInfo menuInfo) {
		inflater.inflate(R.menu.context_menu_roles_list, menu);
	}

	@Override
	public boolean onContextPopupMenuClick(MenuItem item,
			AdapterContextMenuInfo menuInfo) {
		switch (item.getItemId()) {
		case R.id.action_delete_role:
			showDeleteRoleDialog(menuInfo.id);
			break;
		case R.id.action_edit_role:
			showEditRoleDialog(menuInfo.id);
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void onContextButtonClick(AdapterContextMenuInfo menuInfo) {
		showContextPopupMenu(menuInfo);
	}
	
	private void showDeleteRoleDialog(final long playerId) {
		OkCancelDialogFragment dialog = OkCancelDialogFragment.create(
				getString(R.string.dialog_delete_role_title),
				getString(R.string.dialog_delete_role_message),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(which == DialogInterface.BUTTON_POSITIVE)
							mBaseModel.removeRole(playerId);
					}
				});
		dialog.show(getFragmentManager(), OkCancelDialogFragment.TAG);
	}
	
	private void showEditRoleDialog(long roleId) {
		AddRoleDialogFragment dialog = AddRoleDialogFragment.create(roleId);
		dialog.show(getFragmentManager(), AddPlayerDialogFragment.TAG);
	}
	
	private void showAddRoleDialog() {
		AddRoleDialogFragment dialog = AddRoleDialogFragment.create(0);
		dialog.show(getFragmentManager(), AddPlayerDialogFragment.TAG);
	}
}
