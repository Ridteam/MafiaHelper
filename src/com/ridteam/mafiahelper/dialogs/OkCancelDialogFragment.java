package com.ridteam.mafiahelper.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class OkCancelDialogFragment extends DialogFragment {
	public static final String TAG = "okCancelDialogFragment";
	
	public static final String TITLE = "title";
	public static final String MESSAGE = "message";

	private DialogInterface.OnClickListener mEmptyClickListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) { }
	};
	private DialogInterface.OnClickListener mPositiveClickListener = mEmptyClickListener;
	private DialogInterface.OnClickListener mNegativeClickListener = mEmptyClickListener;

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
	
	public void setPositiveClickListener(DialogInterface.OnClickListener listener) {
		if(listener != null)
			mPositiveClickListener = listener;
		else
			mPositiveClickListener = mEmptyClickListener;
	}
	
	public void setNegativeClickListener(DialogInterface.OnClickListener listener) {
		if(listener != null)
			mNegativeClickListener = listener;
		else
			mNegativeClickListener = mEmptyClickListener;
	}
}
