package com.ridteam.mafiahelper.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ridteam.mafiahelper.R;

public class SlidingMenuFragment extends Fragment {
	public static final int OPTION_PLAYERS = 1;
	public static final int OPTION_ROLES = 2;
	
	private ISlidingMenuCallback mCallbacks;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sliding_menu, null);
		View btnPlayers = view.findViewById(R.id.btnPlayers);
		if(btnPlayers != null) btnPlayers.setOnClickListener(mMenuButtonClick);
		View btnRoles = view.findViewById(R.id.btnRoles);
		if(btnRoles != null) btnRoles.setOnClickListener(mMenuButtonClick);
		return view;
	}
	
	public void setCallback(ISlidingMenuCallback pCallback) {
		mCallbacks = pCallback;
	}
	
	public interface ISlidingMenuCallback {
		public void onSlidingMenuOptionClick(int pOptionId);
	}
	
	private OnClickListener mMenuButtonClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnPlayers:
				handleOptionClick(OPTION_PLAYERS);
				break;
			case R.id.btnRoles:
				handleOptionClick(OPTION_ROLES);
				break;
			}
		}
	};
	
	private void handleOptionClick(int pOptionId) {
		if(mCallbacks != null)
			mCallbacks.onSlidingMenuOptionClick(pOptionId);
	}
}
