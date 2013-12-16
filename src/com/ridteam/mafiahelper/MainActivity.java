package com.ridteam.mafiahelper;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.ridteam.mafiahelper.adapters.PlayersListAdapter;
import com.ridteam.mafiahelper.controller.AddPlayersController;
import com.ridteam.mafiahelper.controller.IListController;
import com.ridteam.mafiahelper.database.IDataBase;
import com.ridteam.mafiahelper.fragments.ListViewFragment;

public class MainActivity extends ActionBarActivity {
	private IDataBase mDataBase;
	private IListController mPlayersListController;
	private ListViewFragment mAddPlayersFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDataBase = MafiaHelperApplication.getDataBase();
		ListAdapter adapter = new PlayersListAdapter(this, mDataBase.getPlayers());
		mPlayersListController = new AddPlayersController(mDataBase);
		mAddPlayersFragment = new ListViewFragment();
		mAddPlayersFragment.setController(mPlayersListController);
		mAddPlayersFragment.setListAdapter(adapter);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.fragment, mAddPlayersFragment);
		transaction.commit();
		
		// For testing
		final EditText editAdd = (EditText) findViewById(R.id.edAdd);
		Button buttonAdd = (Button) findViewById(R.id.btnAdd);
		buttonAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDataBase.addPlayer(editAdd.getText().toString());
			}
		});
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
