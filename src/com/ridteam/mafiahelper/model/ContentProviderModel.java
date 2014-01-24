package com.ridteam.mafiahelper.model;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerEffectsColumns;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerEffectsTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerHistoryTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayersTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolePropertiesTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolesTable;

/**
 * 
 * @author Foenix Wrapper class for database actorlive.db queries
 */

public class ContentProviderModel implements IBaseModel {
	private static final String TAG = "ContentProviderModel";
	private Context mContext;

	public ContentProviderModel(Context context) {
		super();
		this.mContext = context;
	}

	public CursorLoader getPlayersLoader() {
		Log.d(TAG, PlayersTable.CONTENT_URI.toString());
		return new CursorLoader(mContext, PlayersTable.CONTENT_URI, null, null, null, null);
	}

	@Override
	public void addPlayer(String name) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayersTable.NAME, name);
		mContext.getContentResolver().insert(PlayersTable.CONTENT_URI, contentValues);
	}

	@Override
	public void removePlayer(long playerId) {
		Uri uri = Uri.withAppendedPath(PlayersTable.CONTENT_URI, String.valueOf(playerId));
		mContext.getContentResolver().delete(uri, null, null);
	}

	@Override
	public void setRole(long playerId, long roleId) {
		Uri uri = Uri.withAppendedPath(PlayersTable.CONTENT_URI, String.valueOf(playerId));
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayersTable.ROLE_ID, roleId);
		mContext.getContentResolver().update(uri, contentValues, BaseColumns._ID + "=?",
				new String[] { String.valueOf(playerId) });
	}

	@Override
	public void setAlive(long playerId, boolean isAlive) {
		Uri uri = Uri.withAppendedPath(PlayersTable.CONTENT_URI, String.valueOf(playerId));
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayersTable.IS_ALIVE, isAlive);
		mContext.getContentResolver().update(uri, contentValues, BaseColumns._ID + "=?",
				new String[] { String.valueOf(playerId) });
	}

	@Override
	public void setMarkedAsDead(long playerId, boolean isMarkedAsDead) {
		Uri uri = Uri.withAppendedPath(PlayersTable.CONTENT_URI, String.valueOf(playerId));
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayersTable.IS_MARKED_AS_DEAD, isMarkedAsDead);
		mContext.getContentResolver().update(uri, contentValues, BaseColumns._ID + "=?",
				new String[] { String.valueOf(playerId) });
	}

	@Override
	public void killMarkedAsDead() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayersTable.IS_ALIVE, 0);
		mContext.getContentResolver().update(PlayersTable.CONTENT_URI, contentValues,
				PlayersTable.IS_MARKED_AS_DEAD + "=?", new String[] { String.valueOf(1) });

	}

	@Override
	public void setNomination(long playerId, boolean isNominant) {
		Uri uri = Uri.withAppendedPath(PlayersTable.CONTENT_URI, String.valueOf(playerId));
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayersTable.IS_NOMINANT, isNominant);
		mContext.getContentResolver().update(uri, contentValues, BaseColumns._ID + "=?",
				new String[] { String.valueOf(playerId) });

	}

	@Override
	public void setNomination(int minimumAccuse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetNomination() {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseAccuse(long playerId, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetAccuse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseRebuke(long playerId, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public CursorLoader getPlayersEffectsLoader(long playerId) {
		Log.d(TAG, PlayerEffectsTable.CONTENT_URI.toString());
		return new CursorLoader(mContext, PlayerEffectsTable.CONTENT_URI, null, PlayerEffectsColumns.PLAYER_ID + "=?",
				new String[] { String.valueOf(playerId) }, null);
	}

	@Override
	public void addPlayersEffects(long playerId, int type, int time) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayerEffectsTable.TYPE, type);
		contentValues.put(PlayerEffectsTable.TIME, time);
		contentValues.put(PlayerEffectsTable.PLAYER_ID, playerId);
		mContext.getContentResolver().insert(PlayersTable.CONTENT_URI, contentValues);
	}

	@Override
	public void removePlayersEffects(long playerId, int type) {
		mContext.getContentResolver().delete(PlayerEffectsTable.CONTENT_URI,
				PlayerEffectsColumns.PLAYER_ID + "=?" + PlayerEffectsColumns.TYPE + "= ?",
				new String[] { String.valueOf(playerId), String.valueOf(type) });

	}

	@Override
	public void removePlayersEffects(long playerId) {
		mContext.getContentResolver().delete(PlayerEffectsTable.CONTENT_URI, PlayerEffectsColumns.PLAYER_ID + "=?",
				new String[] { String.valueOf(playerId) });

	}

	@Override
	public void decreasePlayersEffectsTime(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public CursorLoader getPlayerHistoryLoader() {
		Log.d(TAG, PlayerHistoryTable.CONTENT_URI.toString());
		return new CursorLoader(mContext, PlayerHistoryTable.CONTENT_URI, null, null, null, null);
	}

	@Override
	public CursorLoader getPlayerHistoryLoader(int limit) {
		return new CursorLoader(mContext, PlayerHistoryTable.CONTENT_URI, null, null, null,
				PlayerHistoryTable.LAST_GAME + " DESC LIMIT " + limit);
	}

	@Override
	public void addPlayerHistory(String name, String date, boolean isWin) {
		// ContentValues contentValues = new ContentValues();
		// contentValues.put(PlayerHistoryTable.LAST_GAME, date);
		// contentValues.put(PlayerHistoryTable.WINS, date);
		// mContext.getContentResolver().insert(PlayersTable.CONTENT_URI,
		// contentValues);
	}

	@Override
	public void removePlayersHistory(long playerId) {
		mContext.getContentResolver().delete(PlayerHistoryTable.CONTENT_URI, PlayerHistoryTable.PLAYER_ID + "=?",
				new String[] { String.valueOf(playerId) });

	}

	@Override
	public CursorLoader getRolesLoader() {
		Log.d(TAG, RolesTable.CONTENT_URI.toString());
		return new CursorLoader(mContext, RolesTable.CONTENT_URI, null, null, null, null);
	}

	@Override
	public CursorLoader getRoleLoader(long roleId) {
		Log.d(TAG, RolesTable.CONTENT_URI.toString()+" id = "+roleId);
		Log.d(TAG, RolesTable.CONTENT_URI.toString());
		return new CursorLoader(mContext, RolesTable.CONTENT_URI, null, BaseColumns._ID + "=?",
				new String[] { String.valueOf(roleId) }, null);
	}

	@Override
	public void addRole(String name, String desc, int side, String picture) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(RolesTable.NAME, name);
		contentValues.put(RolesTable.DESC, desc);
		contentValues.put(RolesTable.SIDE, side);
		contentValues.put(RolesTable.PICTURE, picture);
		mContext.getContentResolver().insert(RolesTable.CONTENT_URI, contentValues);
	}

	@Override
	public void editRole(long roleId, String name, String desc, int side, String picture) {
		Uri uri = Uri.withAppendedPath(RolesTable.CONTENT_URI, String.valueOf(roleId));
		ContentValues contentValues = new ContentValues();
		contentValues.put(RolesTable.NAME, name);
		contentValues.put(RolesTable.DESC, desc);
		contentValues.put(RolesTable.SIDE, side);
		contentValues.put(RolesTable.PICTURE, picture);
		;
		mContext.getContentResolver().update(uri, contentValues, BaseColumns._ID + "=?",
				new String[] { String.valueOf(roleId) });
	}

	@Override
	public void removeRole(long roleId) {
		Uri uri = Uri.withAppendedPath(RolesTable.CONTENT_URI, String.valueOf(roleId));
		mContext.getContentResolver().delete(uri, null, null);
	}

	@Override
	public CursorLoader getRolePropertiesLoader(long roleId) {
		Log.d(TAG, RolePropertiesTable.CONTENT_URI.toString());
		return new CursorLoader(mContext, RolePropertiesTable.CONTENT_URI, null, RolePropertiesTable.ROLE_ID + "=?",
				new String[] { String.valueOf(roleId) }, null);
	}

	@Override
	public void addRoleProperty(long roleId, int type, int value) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(RolePropertiesTable.ROLE_ID, roleId);
		contentValues.put(RolePropertiesTable.TYPE, type);
		contentValues.put(RolePropertiesTable.VALUE, value);
		mContext.getContentResolver().insert(RolesTable.CONTENT_URI, contentValues);
	}

	@Override
	public void removeRolePropertysByRole(long roleId) {
		mContext.getContentResolver().delete(RolePropertiesTable.CONTENT_URI, RolePropertiesTable.ROLE_ID + "=?",
				new String[] { String.valueOf(roleId) });
	}

}
