package com.ridteam.mafiahelper.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.fragments.RoleDetailsFragment;

public class RoleDetailsActivity extends FragmentActivity {

	private RoleDetailsFragment mRoleDetailsFragment;
//===============================================================================
//=========== Methods of supper class ===========================================
//===============================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_role_details);
		
		mRoleDetailsFragment = (RoleDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentRoleDetails);
		if(mRoleDetailsFragment != null) {
			long roleId = getIntent().getLongExtra(RoleDetailsFragment.ROLE_ID, 0);
			mRoleDetailsFragment.setRoleId(roleId);
		}
	}
	
}
