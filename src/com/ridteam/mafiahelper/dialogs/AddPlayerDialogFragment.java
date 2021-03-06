package com.ridteam.mafiahelper.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ridteam.mafiahelper.MafiaHelperApplication;
import com.ridteam.mafiahelper.R;
import com.ridteam.mafiahelper.model.IBaseModel;

public class AddPlayerDialogFragment extends DialogFragment implements OnClickListener{
	public static final String TAG = "addPlayerDialogFragment";
	
	private TextView mName;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_add_players, null);
		mName = (TextView) view.findViewById(R.id.edAdd);
		View buttonAdd = view.findViewById(R.id.btnOk);
		buttonAdd.setOnClickListener(this);
		return view;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.setTitle(R.string.dialog_add_player_title);
		return dialog;
	}

	@Override
	public void onClick(View v) {
		IBaseModel baseModel = MafiaHelperApplication.getBaseModel(getActivity());
		String name = mName.getText().toString();
		if(baseModel != null && !"".equals(name))
			baseModel.addPlayer(name);
		dismiss();
	}
}
