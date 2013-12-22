package com.ridteam.mafiahelper;

import android.app.Application;
import android.content.Context;

import com.ridteam.mafiahelper.model.ContentProviderModel;
import com.ridteam.mafiahelper.model.IBaseModel;

public class MafiaHelperApplication extends Application {

	public static IBaseModel getBaseModel(Context context) {
		return new ContentProviderModel(context);
	}
}
