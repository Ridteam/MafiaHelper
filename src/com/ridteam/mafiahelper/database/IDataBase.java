package com.ridteam.mafiahelper.database;

import android.content.Context;
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
	public Cursor getPlayers(Context context);
	
	/**
	 * Add player to the database.
	 * 
	 * @param name Name of added player.
	 */
	public void addPlayer(Context context, String name);
	
	/**
	 * Remove player from database.
	 * 
	 * @param playerId Player ID.
	 */
	public void removePlayer(Context context, long playerId);
	
	/**
	 * Set role for specified player.
	 * @param playerId Player ID.
	 * @param roleId Role ID that will be set for player.
	 */
	public void setRole(Context context, long playerId, long roleId);
	
	/**
	 * Set for specified player is live or dead.
	 * @param playerId Player ID.
	 * @param isLive True is live false is dead.
	 */
	public void setLive(Context context, long playerId, boolean isLive);
	
	/**
	 * Set mark as dead for specified player. 
	 * @param playerId Player ID.
	 * @param isMarkAsDead True - set mark, false - drop mark.
	 */
	public void setMarkAsDead(Context context, long playerId, boolean isMarkAsDead);
	
	/**
	 * Kill all players that been marked as dead via 
	 * {@link #setMarkAsDead}.
	 */
	public void killMarkAsDead(Context context);
	public void setNomination(Context context, long playerId, boolean isNominant);
	public void setNomination(Context context, int minimumAccuse);
	public void resetNomination(Context context );
	public void increaseAccuse(Context context, long playerId, int value);
	public void resetAccuse(Context context);
	public void increaseRebuke(Context context, long playerId, int value);

	public Cursor getPlayersEffects(Context context, long playerId);
	public void addPlayersEffects(Context context, long playerId, int type, int time);
	public void removePlayersEffects(Context context, long playerId, int type);
	public void removePlayersEffects(Context context, long playerId);
	public void decreasePlayersEffectsTime(Context context, int value);

	public Cursor getPlayersHistory(Context context);
	public Cursor getPlayersHistory(Context context, int limit);
	public void addPlayersHistory(Context context, String name, String date, boolean isWin);
	public void removePlayersHistory(Context context, long playerId);

	public Cursor getRoles(Context context);
	public Cursor getRole(Context context, long roleId);
	public void addRole(Context context, String name, String desc, int side, String picture);
	public void editRole(Context context, long roleId, String name, String desc, int side, String picture);
	public void removeRole(Context context, long roleId);
	
	public Cursor getRolePropertys(Context context, long roleId);
	public void addRoleProperty(Context context, long roleId, int type, int value);
	public void removeRolePropertysByRole(Context context, long roleId);
}