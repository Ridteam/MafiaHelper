package com.ridteam.mafiahelper.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.ridteam.mafiahelper.MafiaHelperApplication;
import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.adapters.CursorAdapterLoader;
import com.ridteam.mafiahelper.adapters.IContextedAdapter.OnContextButtonClickListener;
import com.ridteam.mafiahelper.adapters.PlayersListAdapter;
import com.ridteam.mafiahelper.dialogs.OkCancelDialogFragment;
import com.ridteam.mafiahelper.model.IBaseModel;

public class PlayersListFragment extends ListViewFragment implements OnContextButtonClickListener {
	private IBaseModel mBaseModel;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mBaseModel = MafiaHelperApplication.getBaseModel(activity);
		
		PlayersListAdapter adapter = new PlayersListAdapter(activity, null);
		CursorAdapterLoader loaderCallback = new CursorAdapterLoader(mBaseModel.getPlayersLoader(), adapter);
		getSupportLoaderManager().destroyLoader(0);
		getSupportLoaderManager().initLoader(0, null, loaderCallback);
		
		setListAdapter(adapter);
		adapter.setOnContextButtonClickListener(this);
	}
	
	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		// TODO Show set role dialog
	}

	@Override
	public void onCreateContextPopupMenu(MenuInflater inflater, Menu menu,
			AdapterContextMenuInfo menuInfo) {
		inflater.inflate(R.menu.context_menu_players_list, menu);
	}

	@Override
	public boolean onContextPopupMenuClick(MenuItem item,
			AdapterContextMenuInfo menuInfo) {
		switch (item.getItemId()) {
		case R.id.action_delete_player:
			showDeletePlayerDialog(menuInfo.id);
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
	
	private void showDeletePlayerDialog(final long playerId) {
		OkCancelDialogFragment dialog = OkCancelDialogFragment.create(
				getString(R.string.dialog_delete_player_title),
				getString(R.string.dialog_delete_player_message),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(which == DialogInterface.BUTTON_POSITIVE)
							mBaseModel.removePlayer(playerId);
					}
				});
		dialog.show(getFragmentManager(), OkCancelDialogFragment.TAG);
	}

}
