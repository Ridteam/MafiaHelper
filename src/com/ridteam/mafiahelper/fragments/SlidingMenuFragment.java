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
	public static final int OPTION_RULES = 3;
	public static final int OPTION_ABOUT = 4;
	
	private ISlidingMenuCallback mCallbacks;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sliding_menu, null);
		setClickListenerFor(R.id.btnPlayers, view);
		setClickListenerFor(R.id.btnRoles, view);
		setClickListenerFor(R.id.btnRules, view);
		setClickListenerFor(R.id.btnAbout, view);
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
			case R.id.btnRules:
				handleOptionClick(OPTION_RULES);
				break;
			case R.id.btnAbout:
				handleOptionClick(OPTION_ABOUT);
				break;
			}
		}
	};
	
	private void setClickListenerFor(int viewId, View parentView) {
		View view = parentView.findViewById(viewId);
		if(view != null) view.setOnClickListener(mMenuButtonClick);
	}
	
	private void handleOptionClick(int pOptionId) {
		if(mCallbacks != null)
			mCallbacks.onSlidingMenuOptionClick(pOptionId);
	}
}
