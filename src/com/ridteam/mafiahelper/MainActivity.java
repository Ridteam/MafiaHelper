package com.ridteam.mafiahelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.ridteam.mafiahelper.adapters.IContextedAdapter.OnContextButtonClickListener;
import com.ridteam.mafiahelper.adapters.PlayersListAdapter;
import com.ridteam.mafiahelper.adapters.RolesListAdapter;
import com.ridteam.mafiahelper.controller.AppController;
import com.ridteam.mafiahelper.controller.IAppController;
import com.ridteam.mafiahelper.controller.IPlayersController;
import com.ridteam.mafiahelper.controller.IRolesController;
import com.ridteam.mafiahelper.controller.PlayersController;
import com.ridteam.mafiahelper.controller.RolesController;
import com.ridteam.mafiahelper.dialogs.AddPlayerDialogFragment;
import com.ridteam.mafiahelper.dialogs.OkCancelDialogFragment;
import com.ridteam.mafiahelper.fragments.ListViewFragment;
import com.ridteam.mafiahelper.model.IBaseModel;
import com.ridteam.mafiahelper.views.IView;

public class MainActivity extends ActionBarActivity implements IView {
	private IBaseModel mBaseModel;
	private IPlayersController mPlayersController;
	private IRolesController mRolesController;
	private IAppController mAppController;
	private ListViewFragment mAddPlayersFragment;
	private ListViewFragment mRolesListFragment;

//===============================================================================
//=========== Methods of supper class ===========================================
//===============================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mBaseModel = MafiaHelperApplication.getBaseModel(this);
		mPlayersController = new PlayersController(mBaseModel, this);
		mRolesController = new RolesController(mBaseModel, this);
		mAppController = new AppController(mBaseModel, this);
		mAppController.setScene(SCENE_PLAYERS_LIST);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(mAppController.getMenuResId(), menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_player:
			mPlayersController.handleAddPlayerClick();
			break;
		case R.id.action_add_role:
			mRolesController.handleAddRoleClick();
			break;
		case R.id.action_show_players:
			mAppController.handleSetSceneClick(SCENE_PLAYERS_LIST);
			break;
		case R.id.action_show_roles:
			mAppController.handleSetSceneClick(SCENE_ROLES_LIST);
			break;

		default:
			break;
		}
		return true;
	}
	
//===============================================================================
//=========== Methods of IView interface ========================================
//===============================================================================

	@Override
	public void setScene(int scene) {
		Fragment sceneFragment = null;
		switch (scene) {
		case SCENE_PLAYERS_LIST:
			if(mAddPlayersFragment == null)
				mAddPlayersFragment = getAddPlayersFragment();
			sceneFragment = mAddPlayersFragment;
			break;
		case SCENE_ROLES_LIST:
			if(mRolesListFragment == null)
				mRolesListFragment = getRolesListFragment();
			sceneFragment = mRolesListFragment;
			break;
		case SCENE_GAME:
			
			break;
		}
		if(sceneFragment != null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.fragment, sceneFragment);
			transaction.commit();
		}
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
		args.putString(OkCancelDialogFragment.TITLE, getString(R.string.dialog_delete_player_title));
        args.putString(OkCancelDialogFragment.MESSAGE, getString(R.string.dialog_delete_player_message));

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

	@Override
	public void showAddRoleDialog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showEditRoleDialog(long playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDeleteRoleDialog(long playerId) {
		// TODO Auto-generated method stub
		
	}
	
//===============================================================================
//=========== Private Classes and methods =======================================
//===============================================================================
	
	private ListViewFragment getAddPlayersFragment() {
		PlayersListAdapter adapter = new PlayersListAdapter(this, null);
		CursorAdapterLoader loaderCallback = new CursorAdapterLoader(mBaseModel.getPlayersLoader(), adapter);
		getSupportLoaderManager().initLoader(0, null, loaderCallback);
		
		ListViewFragment fragment = new ListViewFragment();
		fragment.setListAdapter(adapter);
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
	
	private ListViewFragment getRolesListFragment() {
		RolesListAdapter adapter = new RolesListAdapter(this, null);
		CursorAdapterLoader loaderCallback = new CursorAdapterLoader(mBaseModel.getRolesLoader(), adapter);
		getSupportLoaderManager().initLoader(0, null, loaderCallback);
		
		ListViewFragment fragment = new ListViewFragment();
		fragment.setListAdapter(adapter);
		adapter.setOnContextButtonClickListener(new OnContextButtonClickListener() {
			@Override
			public void onContextButtonClick(AdapterContextMenuInfo menuInfo) {
				mRolesController.handleContextMenuClick(menuInfo);
			}
		});
		fragment.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mRolesController.handleEditRoleClick(id);
			}
		});
		return fragment;
	}

}
