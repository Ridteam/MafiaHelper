package com.ridteam.mafiahelper.database;

import android.database.Cursor;

/**
 * Provide a methods for work with database.
 * 
 * @author Shurygin Denis
 *
 */
public interface IDataBase {

	/**
	 * Return cursor that contains players list.
	 * 
	 * @return Cursor with players list.
	 */
	public Cursor getPlayers();
	
	/**
	 * Add player to the database.
	 * 
	 * @param userName Name of added player.
	 */
	public void addPlayer(String name);
	
	/**
	 * Remove player from database.
	 * 
	 * @param playerId Player ID.
	 */
	public void removePlayer(long playerId);
	
	/**
	 * Set role for specified player.
	 * @param playerId Player ID.
	 * @param roleId Role ID that will be set for player.
	 */
	public void setRole(long playerId, long roleId);
	
	/**
	 * Set for specified player is live or dead.
	 * @param playerId Player ID.
	 * @param isLive True is live false is dead.
	 */
	public void setLive(long playerId, boolean isLive);
	
	/**
	 * Set mark as dead for specified player. 
	 * @param playerId Player ID.
	 * @param isMarkAsDead True - set mark, false - drop mark.
	 */
	public void setMarkAsDead(long playerId, boolean isMarkAsDead);
	
	/**
	 * Kill all players that been marked as dead via 
	 * {@link #setMarkAsDead}.
	 */
	public void killMarkAsDead();
	public void setNomination(long playerId, boolean isNominant);
	public void setNomination(int minimumAccuse);
	public void resetNomination();
	public void increaseAccuse(long playerId, int value);
	public void resetAccuse();
	public void increaseRebuke(long playerId, int value);

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
	public void addRole(String name, String desc, int side, String picture);
	public void editRole(long roleId, String name, String desc, int side, String picture);
	public void removeRole(long roleId);
	
	public Cursor getRolePropertys(long roleId);
	public void addRoleProperty(long roleId, int type, int value);
	public void removeRolePropertysByRole(long roleId);
}