package com.ridteam.mafiahelper.model;

import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerEffectsColumns;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerEffectsTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayerHistoryTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayersTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolePropertiesTable;
import com.ridteam.mafiahelper.database.MafiaHelperTables.RolesTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.util.Log;

/**
 * 
 * @author Foenix Wrapper class for database actorlive.db queries
 */

public class ContentProviderModel implements IModel {
	private static final String TAG = "ContentProviderModel";
	private Context mContext;

	public ContentProviderModel(Context context) {
		super();

		this.mContext = context;
	}

	@Override
	public Cursor getPlayers() {
		Log.d(TAG, PlayersTable.CONTENT_URI.toString());
		return mContext.getContentResolver().query(PlayersTable.CONTENT_URI,
				null, null, null, null);

	}

	public CursorLoader getLoaderPlayers() {
		Log.d(TAG, PlayersTable.CONTENT_URI.toString());
		return new CursorLoader(mContext, PlayersTable.CONTENT_URI, null, null,
				null, null);
	}

	@Override
	public void addPlayer(String name) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayersTable.NAME, name);
		mContext.getContentResolver().insert(PlayersTable.CONTENT_URI,
				contentValues);
	}

	@Override
	public void removePlayer(long playerId) {
		Uri uri = Uri.withAppendedPath(PlayersTable.CONTENT_URI,
				String.valueOf(playerId));
		mContext.getContentResolver().delete(uri, null, null);
	}

	@Override
	public void setRole(long playerId, long roleId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLive(long playerId, boolean isLive) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMarkAsDead(long playerId, boolean isMarkAsDead) {
		// TODO Auto-generated method stub

	}

	@Override
	public void killMarkAsDead() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNomination(long playerId, boolean isNominant) {
		// TODO Auto-generated method stub

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
	public Cursor getPlayersEffects(long playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlayersEffects(long playerId, int type, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePlayersEffects(long playerId, int type) {
		mContext.getContentResolver()
				.delete(PlayerEffectsTable.CONTENT_URI,
						PlayerEffectsColumns.PLAYER_ID + "=?"
								+ PlayerEffectsColumns.TYPE + "= ?",
						new String[] { String.valueOf(playerId),
								String.valueOf(type) });

	}

	@Override
	public void removePlayersEffects(long playerId) {
		mContext.getContentResolver().delete(PlayerEffectsTable.CONTENT_URI,
				PlayerEffectsColumns.PLAYER_ID + "=?",
				new String[] { String.valueOf(playerId) });

	}

	@Override
	public void decreasePlayersEffectsTime(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cursor getPlayersHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor getPlayersHistory(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlayersHistory(String name, String date, boolean isWin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePlayersHistory(long playerId) {
		mContext.getContentResolver().delete(PlayerHistoryTable.CONTENT_URI,
				PlayerHistoryTable.PLAYER_ID + "=?",
				new String[] { String.valueOf(playerId) });

	}

	@Override
	public Cursor getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor getRole(long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRole(String name, String desc, int side, String picture) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editRole(long roleId, String name, String desc, int side,
			String picture) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRole(long roleId) {
		Uri uri = Uri.withAppendedPath(RolesTable.CONTENT_URI,
				String.valueOf(roleId));
		mContext.getContentResolver().delete(uri, null, null);
	}

	@Override
	public Cursor getRolePropertys(long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRoleProperty(long roleId, int type, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRolePropertysByRole(long roleId) {
		mContext.getContentResolver().delete(RolePropertiesTable.CONTENT_URI,
				RolePropertiesTable.ROLE_ID + "=?",
				new String[] { String.valueOf(roleId) });

	}

}
