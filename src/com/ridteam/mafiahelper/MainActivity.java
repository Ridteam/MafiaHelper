package com.ridteam.mafiahelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

import com.ridteam.mafiahelper.adapters.CursorAdapterLoader;
import com.ridteam.mafiahelper.adapters.IListAdapter.OnContextButtonClickListener;
import com.ridteam.mafiahelper.adapters.PlayersListAdapter;
import com.ridteam.mafiahelper.controller.IPlayersController;
import com.ridteam.mafiahelper.controller.PlayersController;
import com.ridteam.mafiahelper.dialogs.AddPlayerDialogFragment;
import com.ridteam.mafiahelper.dialogs.OkCancelDialogFragment;
import com.ridteam.mafiahelper.fragments.ListViewFragment;
import com.ridteam.mafiahelper.model.IModel;
import com.ridteam.mafiahelper.views.IView;

public class MainActivity extends ActionBarActivity implements IView {
	private IModel mModel;
	private IPlayersController mPlayersController;
	private ListViewFragment mAddPlayersFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mModel = MafiaHelperApplication.getDataBase(this);
		mAddPlayersFragment = getAddPlayersFragment(mModel);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.fragment, mAddPlayersFragment);
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			mPlayersController.handleAddplayerClick();
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void setScene(int scene) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showContextMenu(ContextMenu menu,
			AdapterContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAddPlayersDialog() {
		AddPlayerDialogFragment dialog = new AddPlayerDialogFragment();
		dialog.setController(mPlayersController);
		dialog.show(getSupportFragmentManager(), AddPlayerDialogFragment.TAG);
	}

	@Override
	public void showDeletePlayerDialog(final long playerId) {
		Bundle args = new Bundle();
		args.putString(OkCancelDialogFragment.TITLE, getString(R.string.dialog_title_delete_player));
        args.putString(OkCancelDialogFragment.MESSAGE, getString(R.string.dialog_message_delete_player));

		OkCancelDialogFragment dialog = new OkCancelDialogFragment();
		dialog.setArguments(args);
		dialog.setPositiveClickListener(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mPlayersController.deletePlayer(playerId);
			}
		});
		dialog.show(getSupportFragmentManager(), OkCancelDialogFragment.TAG);
	}

	@Override
	public void showSetPlayerRoleDialog(long playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSetPlayerPictureDialog(long playerId) {
		// TODO Auto-generated method stub
		
	}
	
	private ListViewFragment getAddPlayersFragment(IModel model) {
		PlayersListAdapter adapter = new PlayersListAdapter(this, null);
		CursorAdapterLoader loaderCallback = new CursorAdapterLoader(model.getLoaderPlayers(), adapter);
		getSupportLoaderManager().initLoader(0, null, loaderCallback);
		
		ListViewFragment fragment = new ListViewFragment();
		fragment.setListAdapter(adapter);
		mPlayersController = new PlayersController(model, this);
		adapter.setOnContextButtonClickListener(new OnContextButtonClickListener() {
			@Override
			public void onContextButtonClick(AdapterContextMenuInfo menuInfo) {
				mPlayersController.handleContextMenuClick(menuInfo);
			}
		});
		fragment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mPlayersController.handleSetRoleClick(id);
			}
		});
		return fragment;
	}

}
