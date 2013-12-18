package com.ridteam.mafiahelper.model;

import com.ridteam.mafiahelper.database.MafiaHelperTables.PlayersTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		Log.d(TAG,PlayersTable.CONTENT_URI.toString() );
		return mContext.getContentResolver().query(PlayersTable.CONTENT_URI,
				null, null, null, null);

	}

	@Override
	public void addPlayer(String name) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(PlayersTable.NAME, name);
		mContext.getContentResolver().insert(PlayersTable.CONTENT_URI, contentValues);
	}

	@Override
	public void removePlayer(long playerId) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlayersEffects(long playerId) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}




}
