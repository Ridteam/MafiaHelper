package com.ridteam.mafiahelper.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class OkCancelDialogFragment extends DialogFragment {
	public static final String TAG = "okCancelDialogFragment";
	
	private static final String TITLE = "title";
	private static final String MESSAGE = "message";

	private DialogInterface.OnClickListener mPositiveClickListener;
	private DialogInterface.OnClickListener mNegativeClickListener;

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args = getArguments();
        String title = args.getString(TITLE);
        String message = args.getString(MESSAGE);

        return new AlertDialog.Builder(getActivity())
		        .setTitle(title)
		        .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                	mPositiveClickListener
                )
                .setNegativeButton(android.R.string.cancel,
                	mNegativeClickListener
                )
                .create();
    }
	
	public void setPropertys(String title, String message) {
		Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE, message);
        setArguments(args);
	}
	
	public void setPositiveClickListener(DialogInterface.OnClickListener listener) {
		if(listener == null) mPositiveClickListener = mEmptyClickListener;
		else mPositiveClickListener = listener;
	}
	
	public void setNegativeClickListener(DialogInterface.OnClickListener listener) {
		if(listener == null) mNegativeClickListener = mEmptyClickListener;
		else mNegativeClickListener = listener;
	}
	
	private DialogInterface.OnClickListener mEmptyClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) { }
	};
}
