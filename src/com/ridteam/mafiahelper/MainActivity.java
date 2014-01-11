package com.ridteam.mafiahelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ridteam.mafiahelper.controller.AppController;
import com.ridteam.mafiahelper.controller.IAppController;
import com.ridteam.mafiahelper.controller.IPlayersController;
import com.ridteam.mafiahelper.controller.IRolesController;
import com.ridteam.mafiahelper.controller.PlayersController;
import com.ridteam.mafiahelper.controller.RolesController;
import com.ridteam.mafiahelper.dialogs.AddPlayerDialogFragment;
import com.ridteam.mafiahelper.dialogs.AddRoleDialogFragment;
import com.ridteam.mafiahelper.fragments.ListViewFragment;
import com.ridteam.mafiahelper.fragments.PlayersListFragment;
import com.ridteam.mafiahelper.fragments.RolesListFragment;
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
		mPlayersController = new PlayersController(mBaseModel);
		mRolesController = new RolesController(mBaseModel);
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
	public boolean onPrepareOptionsMenu(Menu menu) {
		mAppController.prepareMenu(menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_player:
			showAddPlayersDialog();
			break;
		case R.id.action_add_role:
			showAddRoleDialog();
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
				mAddPlayersFragment = new PlayersListFragment();
			sceneFragment = mAddPlayersFragment;
			break;
		case SCENE_ROLES_LIST:
			if(mRolesListFragment == null)
				mRolesListFragment = new RolesListFragment();
			sceneFragment = mRolesListFragment;
			break;
		case SCENE_GAME:
			
			break;
		}
		if(sceneFragment != null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.fragment, sceneFragment);
			transaction.commit();
		}
	}
	
//===============================================================================
//=========== Private Classes and methods =======================================
//===============================================================================

	private void showAddPlayersDialog() {
		AddPlayerDialogFragment dialog = new AddPlayerDialogFragment();
		dialog.setController(mPlayersController);
		dialog.show(getSupportFragmentManager(), AddPlayerDialogFragment.TAG);
	}
	
	private void showAddRoleDialog() {
		AddRoleDialogFragment dialog = AddRoleDialogFragment.create(mRolesController, 0);
		dialog.show(getSupportFragmentManager(), AddPlayerDialogFragment.TAG);
	}

}
