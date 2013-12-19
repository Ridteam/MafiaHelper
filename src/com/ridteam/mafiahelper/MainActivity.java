package com.ridteam.mafiahelper;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ridteam.mafiahelper.adapters.CursorAdapterLoader;
import com.ridteam.mafiahelper.adapters.PlayersListAdapter;
import com.ridteam.mafiahelper.controller.AddPlayersController;
import com.ridteam.mafiahelper.controller.IListController;
import com.ridteam.mafiahelper.fragments.ListViewFragment;
import com.ridteam.mafiahelper.model.IModel;

public class MainActivity extends ActionBarActivity {
	private IModel mModel;
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
		
		// For testing
		final EditText editAdd = (EditText) findViewById(R.id.edAdd);
		Button buttonAdd = (Button) findViewById(R.id.btnAdd);
		buttonAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mModel.addPlayer(editAdd.getText().toString());
			}
		});
	}
	
	private ListViewFragment getAddPlayersFragment(IModel model) {
		PlayersListAdapter adapter = new PlayersListAdapter(this, null);
		CursorAdapterLoader loaderCallback = new CursorAdapterLoader(model.getLoaderPlayers(), adapter);
		getSupportLoaderManager().initLoader(0, null, loaderCallback);
		
		ListViewFragment fragment = new ListViewFragment();
		fragment.setAdapter(adapter);
		new AddPlayersController(model, fragment);
		return fragment;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
