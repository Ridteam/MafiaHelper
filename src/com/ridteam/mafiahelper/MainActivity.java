package com.ridteam.mafiahelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ridteam.mafiahelper.dialogs.AddPlayerDialogFragment;
import com.ridteam.mafiahelper.dialogs.AddRoleDialogFragment;
import com.ridteam.mafiahelper.fragments.ListViewFragment;
import com.ridteam.mafiahelper.fragments.PlayersListFragment;
import com.ridteam.mafiahelper.fragments.RolesListFragment;

public class MainActivity extends ActionBarActivity {
	public static final int SCENE_PLAYERS_LIST	= 1;
	public static final int SCENE_GAME			= 2;
	public static final int SCENE_ROLES_LIST	= 3;
	
	private static final String EXTRA_SCENE = "Scene";
	
	private int mScene;
	private ListViewFragment mAddPlayersFragment;
	private ListViewFragment mRolesListFragment;

//===============================================================================
//=========== Methods of supper class ===========================================
//===============================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        
        if(savedInstanceState == null) setScene(SCENE_PLAYERS_LIST);
        else
        	mScene = savedInstanceState.getInt(EXTRA_SCENE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	
	private static final int[] GROUPS_LIST = {R.id.action_group_players, R.id.action_group_roles};
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		int groupId = 0;
		
		switch (mScene) {
		case SCENE_PLAYERS_LIST:
			groupId = R.id.action_group_players;
			break;
		case SCENE_ROLES_LIST:
			groupId = R.id.action_group_roles;
			break;
		}
		for(int i = 0; i < GROUPS_LIST.length; i++)
			menu.setGroupVisible(GROUPS_LIST[i], GROUPS_LIST[i] == groupId);
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
			setScene(SCENE_PLAYERS_LIST);
			break;
		case R.id.action_show_roles:
			setScene(SCENE_ROLES_LIST);
			break;

		default:
			break;
		}
		return true;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(EXTRA_SCENE, mScene);
	}
	
//===============================================================================
//=========== Private Classes and methods =======================================
//===============================================================================

	private void setScene(int scene) {
		mScene = scene;
		switch (scene) {
		case SCENE_PLAYERS_LIST:
			if (mAddPlayersFragment == null)
				mAddPlayersFragment = new PlayersListFragment();
			replaceSceneFragment(mAddPlayersFragment);
			break;
		case SCENE_ROLES_LIST:
			if(mRolesListFragment == null)
				mRolesListFragment = new RolesListFragment();
			replaceSceneFragment(mRolesListFragment);
			break;
		case SCENE_GAME:
			
			break;
		}
		
	}
	
	private void replaceSceneFragment(Fragment fragment) {
		if(fragment != null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.fragment, fragment, fragment.getClass().getName());
			transaction.commit();
		}
	}
	
	private void addSceneFragment(Fragment fragment) {
		if(fragment != null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.fragment, fragment, fragment.getClass().getName());
			transaction.commit();
		}
	}

	private void showAddPlayersDialog() {
		AddPlayerDialogFragment dialog = new AddPlayerDialogFragment();
		dialog.show(getSupportFragmentManager(), AddPlayerDialogFragment.TAG);
	}
	
	private void showAddRoleDialog() {
		AddRoleDialogFragment dialog = AddRoleDialogFragment.create(0);
		dialog.show(getSupportFragmentManager(), AddPlayerDialogFragment.TAG);
	}

}
