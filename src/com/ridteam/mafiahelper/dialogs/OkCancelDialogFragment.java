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

	private final static DialogInterface.OnClickListener EMPTY_CLICK_LISTENER = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) { }
	};
	private DialogInterface.OnClickListener mClickListener = EMPTY_CLICK_LISTENER;
	
	public static OkCancelDialogFragment create(String title, String message, DialogInterface.OnClickListener listener) {
		OkCancelDialogFragment dialog = new OkCancelDialogFragment();
		dialog.setClickListener(listener);
		
		Bundle args = new Bundle();
		args.putString(OkCancelDialogFragment.TITLE, title);
        args.putString(OkCancelDialogFragment.MESSAGE, message);
		dialog.setArguments(args);
		
		return dialog;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args = getArguments();
        String title = args.getString(TITLE);
        String message = args.getString(MESSAGE);

        return new AlertDialog.Builder(getActivity())
		        .setTitle(title)
		        .setMessage(message)
                .setPositiveButton(android.R.string.ok, mClickListener)
                .setNegativeButton(android.R.string.cancel, mClickListener)
                .create();
    }
	
	public void setClickListener(DialogInterface.OnClickListener listener) {
		if(listener != null)
			mClickListener = listener;
		else
			mClickListener = EMPTY_CLICK_LISTENER;
	}
	
	
}
