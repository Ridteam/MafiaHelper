package com.ridteam.mafiahelper.database;

import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerEffectsColumns;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerHistoryColumns;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayersColumns;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolePropertiesColumns;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolesColumns;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
/**
 * 
 * @author Foenix
 *
 */
public class MafiaHelperDBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "mafiahelper.db";
	private static final int DATABASE_VERSION = 2;
	
	private static final String TAG = "MafiaHelperDBHelper";
	Context context;
	
	public MafiaHelperDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	interface Tables {
        String PLAYERS = "players";
        String PLAYER_HISTORY = "player_history";
        String PLAYER_EFFECTS = "player_effects";
        String ROLES = "roles";
        String ROLE_PROPERTIES = "role_properties";
        int SSID_PATH_POSITION = 1;

    } 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + Tables.PLAYERS + " ("
				+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ PlayersColumns.NAME + " TEXT,"
				+ PlayersColumns.ROLE_ID + " INTEGER," 
				+ PlayersColumns.IS_ALIVE	+ " INTEGER," 
				+ PlayersColumns.IS_MARKED_AS_DEAD + " INTEGER,"
				+ PlayersColumns.IS_NOMINANT + " INTEGER,"
				+ PlayersColumns.ACCUSE + " INTEGER,"
				+ PlayersColumns.REBUKE + " INTEGER,"
				+ PlayersColumns.PICTURE + " TEXT);");
		
		db.execSQL("CREATE TABLE " + Tables.PLAYER_EFFECTS + " ("
				+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ PlayerEffectsColumns.PLAYER_ID + " INTEGER,"
				+ PlayerEffectsColumns.TYPE + " INTEGER," 
				+ PlayerEffectsColumns.TIME	+ " INTEGER);");

		db.execSQL("CREATE TABLE " + Tables.PLAYER_HISTORY + " ("
				+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ PlayerHistoryColumns.NAME + " TEXT,"
				+ PlayerHistoryColumns.LAST_GAME + " TEXT," 
				+ PlayerHistoryColumns.GAMES	+ " INTEGER," 
				+ PlayerHistoryColumns.WINS + " INTEGER,"
				+ PlayerHistoryColumns.PICTURE + " TEXT);");
		
		db.execSQL("CREATE TABLE " + Tables.ROLES + " ("
				+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ RolesColumns.NAME + " TEXT,"
				+ RolesColumns.DESC + " TEXT," 
				+ RolesColumns.SIDE	+ " INTEGER," 
				+ RolesColumns.LAST_GAME + " TEXT,"
				+ RolesColumns.IS_PRESET + " INTEGER,"
				+ RolesColumns.PICTURE + " TEXT);");

		db.execSQL("CREATE TABLE " + Tables.ROLE_PROPERTIES + " ("
				+ BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ RolePropertiesColumns.ROLE_ID + " INTEGER,"
				+ RolePropertiesColumns.TYPE + " INTEGER," 
				+ RolePropertiesColumns.VALUE + " INTEGER);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("LOG_TAG", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + Tables.PLAYERS);
		db.execSQL("DROP TABLE IF EXISTS " + Tables.PLAYER_HISTORY);
		db.execSQL("DROP TABLE IF EXISTS " + Tables.PLAYER_EFFECTS);
		db.execSQL("DROP TABLE IF EXISTS " + Tables.ROLES);
		db.execSQL("DROP TABLE IF EXISTS " + Tables.ROLE_PROPERTIES);
		
		onCreate(db);

	}

}
