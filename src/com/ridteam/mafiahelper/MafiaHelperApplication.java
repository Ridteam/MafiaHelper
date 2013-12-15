package com.ridteam.mafiahelper;

import android.app.Application;

import com.ridteam.mafiahelper.database.IDataBase;

public class MafiaHelperApplication extends Application {

	public static IDataBase getDataBase() {
		return null;
	}
}
