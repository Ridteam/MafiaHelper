package com.ridteam.mafiahelper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.ridteam.mafiahelper.database.IDataBase;
import com.ridteam.mafiahelper.fragments.AddPlayersFragment;

public class MainActivity extends FragmentActivity {//ActionBarActivity {
	private IDataBase mDataBase;
	private AddPlayersFragment mAddPlayersFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDataBase = MafiaHelperApplication.getDataBase();
		mAddPlayersFragment = new AddPlayersFragment();
		mAddPlayersFragment.setModel(mDataBase);
		
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

}
