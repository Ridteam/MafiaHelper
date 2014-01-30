package com.ridteam.mafiahelper;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;

import com.ridteam.mafiahelper.fragments.ListViewFragment;
import com.ridteam.mafiahelper.fragments.PlayersListFragment;
import com.ridteam.mafiahelper.fragments.RolesListFragment;
import com.ridteam.mafiahelper.fragments.SlidingMenuFragment;
import com.ridteam.mafiahelper.fragments.SlidingMenuFragment.ISlidingMenuCallback;

public class MainActivity extends ActionBarActivity implements ISlidingMenuCallback {
	public static final int SCENE_PLAYERS_LIST	= 1;
	public static final int SCENE_GAME			= 2;
	public static final int SCENE_ROLES_LIST	= 3;

	private static final String EXTRA_CONTENT_FRAGMENT = "ContentFragment";
	private static final String EXTRA_TITLE = "Title";
	
	private ListViewFragment mAddPlayersFragment;
	private ListViewFragment mRolesListFragment;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

//===============================================================================
//=========== Methods of supper class ===========================================
//===============================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
        if(savedInstanceState == null)
        	showPlayersList();
        else {
        	Fragment fragment = getSupportFragmentManager().getFragment(savedInstanceState, EXTRA_CONTENT_FRAGMENT);
        	replaceContentFragment(fragment);
        	CharSequence title = savedInstanceState.getCharSequence(EXTRA_TITLE);
        	if(title != null)
        		setTitle(title);
        }
        
        SlidingMenuFragment slidingMenuFragment = (SlidingMenuFragment) getSupportFragmentManager().findFragmentById(R.id.left_drawer);
        slidingMenuFragment.setCallback(this);
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = fragmentManager.findFragmentById(R.id.content_frame);
		if(fragment != null)
			fragmentManager.putFragment(outState, EXTRA_CONTENT_FRAGMENT, fragment);
		outState.putCharSequence(EXTRA_TITLE, getTitle());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			if(mDrawerLayout.isDrawerOpen(Gravity.LEFT))
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			else
				mDrawerLayout.openDrawer(Gravity.LEFT);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
//===============================================================================
//=========== Interfaces methods ================================================
//===============================================================================

	@Override
	public void onSlidingMenuOptionClick(int pOptionId) {
		switch (pOptionId) {
		case SlidingMenuFragment.OPTION_PLAYERS:
			showPlayersList();
			break;
		case SlidingMenuFragment.OPTION_ROLES:
			showRolesList();
			break;
		}
		mDrawerLayout.closeDrawer(Gravity.LEFT);
	}
	
//===============================================================================
//=========== Private Classes and methods =======================================
//===============================================================================
	
	private void showPlayersList() {
		if (mAddPlayersFragment == null)
			mAddPlayersFragment = new PlayersListFragment();
		replaceContentFragment(mAddPlayersFragment);
		setTitle(R.string.action_players);
	}
	
	private void showRolesList() {
		if(mRolesListFragment == null)
			mRolesListFragment = new RolesListFragment();
		replaceContentFragment(mRolesListFragment);
		setTitle(R.string.action_roles);
	}
	
	private void replaceContentFragment(Fragment fragment) {
		if(fragment != null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
			transaction.commit();
		}
	}

}
