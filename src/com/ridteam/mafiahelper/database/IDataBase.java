package com.ridteam.mafiahelper.database;

import android.database.Cursor;

public interface IDataBase {

	public Cursor getPlayers();
	public void addPlayer(String name);
	public void removePlayer(long playerId);
	public void setRole(long playerId, long roleId);
	public void setLive(long playerId, boolean isLive);
	public void setMarkAsDead(long playerId, boolean isMarkAsDead);
	public void killMarkAsDead();
	public void setNomination(long playerId, boolean isNominant);
	public void setNomination(int minimumAccuse);
	public void increaseAccuse(long playerId, boolean value);
	public void resetAccuse();
	public void increaseRebuke(long playerId, boolean value);

	public Cursor getPlayersEffects(long playerId);
	public void addPlayersEffects(long playerId, int type, int time);
	public void removePlayersEffects(long playerId, int type);
	public void removePlayersEffects(long playerId);
	public void decreasePlayersEffectsTime(int value);

	public Cursor getPlayersHistory();
	public Cursor getPlayersHistory(int limit);
	public void addPlayersHistory(String name, String date, boolean isWin);
	public void removePlayersHistory(long playerId);

	public Cursor getRoles();
	public Cursor getRole(long roleId);
	public long addRole(String name, String desc, int side);
}