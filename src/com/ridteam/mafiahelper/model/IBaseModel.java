package com.ridteam.mafiahelper.model;

import android.support.v4.content.CursorLoader;

/**
 * Provide a methods for work with database.
 * 
 * @author Shurygin Denis
 *
 */
public interface IBaseModel {

	/**
	 * Return cursor that contains players list.
	 * 
	 * @return Cursor with players list.
	 */
	public CursorLoader getPlayersLoader();
	/**
	 * Add player to the database.
	 * 
	 * @param name Name of added player.
	 */
	public long addPlayer(String name);
	
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
	public void setAlive(long playerId, boolean isAlive);
	
	/**
	 * Set mark as dead for specified player. 
	 * @param playerId Player ID.
	 * @param isMarkAsDead True - set mark, false - drop mark.
	 */
	public void setMarkedAsDead(long playerId, boolean isMarkedAsDead);
	
	/**
	 * Kill all players that been marked as dead via 
	 * {@link #setMarkAsDead}.
	 */
	public void killMarkedAsDead();
	public void setNomination(long playerId, boolean isNominant);
	public void setNomination(int minimumAccuse);
	public void resetNomination();
	public void increaseAccuse(long playerId, int value);
	public void resetAccuse();
	public void increaseRebuke(long playerId, int value);

	public CursorLoader getPlayersEffectsLoader(long playerId);
	public long addPlayersEffects(long playerId, int type, int time);
	public void removePlayersEffects(long playerId, int type);
	public void removePlayersEffects(long playerId);
	public void decreasePlayersEffectsTime(int value);

	public CursorLoader getPlayerHistoryLoader();
	public CursorLoader getPlayerHistoryLoader(int limit);
	public long addPlayerHistory(String name, String date, boolean isWin);
	public void removePlayersHistory(long playerId);

	public CursorLoader getRolesLoader();
	public CursorLoader getRoleLoader(long roleId);
	public long addRole(String name, String desc, int side, String picture);
	public void editRole(long roleId, String name, String desc, int side, String picture);
	public void removeRole(long roleId);
	
	public CursorLoader getRolePropertiesLoader(long roleId);
	public long addRoleProperty(long roleId, int type, int value);
	public void removeRolePropertysByRole(long roleId);
}