package com.ridteam.mafiahelper.database;

import com.ridteam.mafiahelper.database.MafiaHelperDBHelper.Tables;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerEffectsTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerHistoryTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayersTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolePropertiesTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolesTable;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

public class MafiaHelperProvider extends ContentProvider {

	private static final String TAG = "MafiaHelperProvider";
	private static final boolean LOGV = Log.isLoggable(TAG, Log.VERBOSE);

	private MafiaHelperDBHelper mOpenHelper;

	private static final UriMatcher sUriMatcher = buildUriMatcher();

	private static final int PLAYERS = 100;
	private static final int PLAYERS_ID = 101;

	private static final int PLAYER_EFFECTS = 200;
	private static final int PLAYER_EFFECTS_ID = 201;

	private static final int PLAYER_HISTORY = 300;
	private static final int PLAYER_HISTORY_ID = 301;

	private static final int ROLES = 400;
	private static final int ROLES_ID = 401;

	private static final int ROLE_PROPERTIES = 500;
	private static final int ROLE_PROPERTIES_ID = 501;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int rowsAffected = 0;
		int id;
		Log.d(TAG, "delete Uri = " + uri);
		switch (sUriMatcher.match(uri)) {
		case PLAYERS_ID:
			id = Integer.parseInt(uri.getLastPathSegment());
			if (TextUtils.isEmpty(selection)) {
				rowsAffected = db.delete(Tables.PLAYERS, BaseColumns._ID + "=" + id, null);
			} else {
				rowsAffected = db.delete(Tables.PLAYERS, selection + " and " + BaseColumns._ID + "=" + id,
						selectionArgs);
			}
			break;
		case PLAYERS:
			rowsAffected = db.delete(Tables.PLAYERS, selection, selectionArgs);
			break;
		case PLAYER_EFFECTS_ID:
			id = Integer.parseInt(uri.getLastPathSegment());
			if (TextUtils.isEmpty(selection)) {
				rowsAffected = db.delete(Tables.PLAYER_EFFECTS, BaseColumns._ID + "=" + id, null);
			} else {
				rowsAffected = db.delete(Tables.PLAYER_EFFECTS, selection + " and " + BaseColumns._ID + "=" + id,
						selectionArgs);
			}
			break;
		case PLAYER_EFFECTS:
			rowsAffected = db.delete(Tables.PLAYERS, selection, selectionArgs);
		case PLAYER_HISTORY_ID:
			id = Integer.parseInt(uri.getLastPathSegment());
			if (TextUtils.isEmpty(selection)) {
				rowsAffected = db.delete(Tables.PLAYER_HISTORY, BaseColumns._ID + "=" + id, null);
			} else {
				rowsAffected = db.delete(Tables.PLAYER_HISTORY, selection + " and " + BaseColumns._ID + "=" + id,
						selectionArgs);
			}
			break;
		case PLAYER_HISTORY:
			rowsAffected = db.delete(Tables.PLAYER_HISTORY, selection, selectionArgs);
		case ROLES_ID:
			id = Integer.parseInt(uri.getLastPathSegment());
			if (TextUtils.isEmpty(selection)) {
				rowsAffected = db.delete(Tables.ROLES, BaseColumns._ID + "=" + id, null);
			} else {
				rowsAffected = db.delete(Tables.ROLES, selection + " and " + BaseColumns._ID + "=" + id, selectionArgs);
			}
			break;
		case ROLES:
			rowsAffected = db.delete(Tables.ROLES, selection, selectionArgs);
		case ROLE_PROPERTIES_ID:
			id = Integer.parseInt(uri.getLastPathSegment());
			if (TextUtils.isEmpty(selection)) {
				rowsAffected = db.delete(Tables.ROLE_PROPERTIES, BaseColumns._ID + "=" + id, null);
			} else {
				rowsAffected = db.delete(Tables.ROLE_PROPERTIES, selection + " and " + BaseColumns._ID + "=" + id,
						selectionArgs);
			}
			break;
		case ROLE_PROPERTIES:
			rowsAffected = db.delete(Tables.ROLE_PROPERTIES, selection, selectionArgs);
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsAffected;

	}

	@Override
	public String getType(Uri uri) {
		final int match = sUriMatcher.match(uri);
		switch (match) {
		case ROLES:
			return RolesTable.CONTENT_TYPE;
		case ROLES_ID:
			return RolesTable.CONTENT_ITEM_TYPE;
		case ROLE_PROPERTIES:
			return RolePropertiesTable.CONTENT_TYPE;
		case ROLE_PROPERTIES_ID:
			return RolePropertiesTable.CONTENT_ITEM_TYPE;
		case PLAYERS:
			return PlayersTable.CONTENT_TYPE;
		case PLAYERS_ID:
			return PlayersTable.CONTENT_ITEM_TYPE;
		case PLAYER_EFFECTS:
			return PlayerEffectsTable.CONTENT_TYPE;
		case PLAYER_EFFECTS_ID:
			return PlayerEffectsTable.CONTENT_ITEM_TYPE;
		case PLAYER_HISTORY:
			return PlayerHistoryTable.CONTENT_TYPE;
		case PLAYER_HISTORY_ID:
			return PlayerHistoryTable.CONTENT_ITEM_TYPE;
		default:
			throw new UnsupportedOperationException("Unknown uri: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = 0;
		switch (sUriMatcher.match(uri)) {
		case PLAYERS:
			rowId = db.insert(Tables.PLAYERS, null, values);
			break;
		case PLAYER_EFFECTS:
			rowId = db.insert(Tables.PLAYER_EFFECTS, null, values);
			break;
		case PLAYER_HISTORY:
			rowId = db.insert(Tables.PLAYER_HISTORY, null, values);
			break;
		case ROLES:
			rowId = db.insert(Tables.ROLES, null, values);
			break;
		case ROLE_PROPERTIES:
			rowId = db.insert(Tables.ROLE_PROPERTIES, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		if (rowId > 0) {
			Uri resUri = ContentUris.withAppendedId(uri, rowId);
			getContext().getContentResolver().notifyChange(resUri, null);
			return resUri;
		}
		throw new SQLException("Failed to insert row into " + uri);

	}

	@Override
	public boolean onCreate() {
		final Context context = getContext();
		mOpenHelper = new MafiaHelperDBHelper(context);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		String ssid;
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();

		switch (sUriMatcher.match(uri)) {
		case PLAYERS:
			Log.d(TAG, "PLAYERS");
			qb.setTables(Tables.PLAYERS);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			cursor = PlayersTable.getPlayersWithRoles(db);
			break;
		case PLAYERS_ID:
			Log.d(TAG, "PLAYERS_ID");
			qb.setTables(Tables.PLAYERS);
			ssid = uri.getPathSegments().get(Tables.SSID_PATH_POSITION);
			qb.appendWhere(BaseColumns._ID + "=" + ssid);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case PLAYER_EFFECTS:
			Log.d(TAG, "PLAYER_EFFECTS");
			qb.setTables(Tables.PLAYER_EFFECTS);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case PLAYER_EFFECTS_ID:
			Log.d(TAG, "PLAYER_EFFECTS_ID");
			qb.setTables(Tables.PLAYER_EFFECTS);
			ssid = uri.getPathSegments().get(Tables.SSID_PATH_POSITION);
			qb.appendWhere(BaseColumns._ID + "=" + ssid);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case PLAYER_HISTORY:
			Log.d(TAG, "PLAYER_HISTORY");
			qb.setTables(Tables.PLAYER_HISTORY);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case PLAYER_HISTORY_ID:
			Log.d(TAG, "PLAYER_HISTORY_ID");
			qb.setTables(Tables.PLAYER_HISTORY);
			ssid = uri.getPathSegments().get(Tables.SSID_PATH_POSITION);
			qb.appendWhere(BaseColumns._ID + "=" + ssid);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case ROLES:
			Log.d(TAG, "ROLES");
			qb.setTables(Tables.ROLES);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case ROLES_ID:
			Log.d(TAG, "ROLES_ID");
			qb.setTables(Tables.ROLES);
			ssid = uri.getPathSegments().get(Tables.SSID_PATH_POSITION);
			qb.appendWhere(BaseColumns._ID + "=" + ssid);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case ROLE_PROPERTIES:
			Log.d(TAG, "ROLE_PROPERTIES");
			qb.setTables(Tables.ROLE_PROPERTIES);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case ROLE_PROPERTIES_ID:
			Log.d(TAG, "ROLE_PROPERTIES_ID");
			qb.setTables(Tables.ROLE_PROPERTIES);
			ssid = uri.getPathSegments().get(Tables.SSID_PATH_POSITION);
			qb.appendWhere(BaseColumns._ID + "=" + ssid);
			cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int updateCount = 0;
		String ssid;
		switch (sUriMatcher.match(uri)) {
		case PLAYERS:
			updateCount = db.update(Tables.PLAYERS, values, selection, selectionArgs);
			break;
		case PLAYERS_ID:
			ssid = uri.getPathSegments().get(Tables.SSID_PATH_POSITION);
			String where = BaseColumns._ID + " = " + ssid;
			if (!TextUtils.isEmpty(selection)) {
				where += " AND " + selection;
			}
			updateCount = db.update(Tables.PLAYERS, values, where, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		if (updateCount > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return updateCount;
	}

	private static UriMatcher buildUriMatcher() {
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		final String authority = MafiaHelperTables.CONTENT_AUTHORITY;

		matcher.addURI(authority, "players", PLAYERS);
		matcher.addURI(authority, "players/*", PLAYERS_ID);

		matcher.addURI(authority, "player_effects", PLAYER_EFFECTS);
		matcher.addURI(authority, "player_effects/*", PLAYER_EFFECTS_ID);

		matcher.addURI(authority, "player_history", PLAYER_HISTORY);
		matcher.addURI(authority, "player_history/*", PLAYER_HISTORY_ID);

		matcher.addURI(authority, "roles", ROLES);
		matcher.addURI(authority, "roles/*", ROLES_ID);

		matcher.addURI(authority, "role_properties", ROLE_PROPERTIES);
		matcher.addURI(authority, "role_properties/*", ROLE_PROPERTIES_ID);

		return matcher;
	}
}
