package com.ridteam.mafiahelper.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public class MafiaHelperTables {
	/**
	 * 
	 * @author Foenix
	 *
	 */
	public interface PlayersColumns {  
		String NAME = "name";
		String ROLE_ID = "role_id";
		String IS_ALIVE = "is_alive";
		String IS_MARKED_AS_DEAD = "is_marked_as_dead";
		String IS_NOMINANT = "is_nominant";
		String ACCUSE = "accuse";
		String REBUKE = "rebuke";
		String PICTURE = "picture";
		String ROLE_NAME = "role_name";
		String ROLE_PICTURE = "role_picture";
	}
	
	public interface PlayerEffectsColumns {
		String PLAYER_ID = "player_id";
		String TYPE = "type";
		String TIME = "time";
	}
	
	public interface PlayerHistoryColumns {
		String PLAYER_ID = "player_id";
		String NAME = "name";
		String LAST_GAME = "last_game";
		String GAMES = "games";
		String WINS = "wins";
		String PICTURE = "picture";
	}
	
	public interface RolesColumns {
		String NAME = "name";
		String DESC = "desc";
		String SIDE = "side";
		String LAST_GAME = "lase_game";
		String IS_PRESET = "is_preset";
		String PICTURE = "picture";
	}
	
	public interface RolePropertiesColumns {
		String ROLE_ID = "role_id";
		String TYPE = "type";
		String VALUE = "value";
	}

	public static final String CONTENT_AUTHORITY = "com.ridteam.mafiahelper";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY); 
    private static final String PATH_ROLES = "roles";
    private static final String PATH_ROLEPROPERTIES = "role_properties";
    
    private static final String PATH_PLAYERS = "players";
    private static final String PATH_PLAYER_HISTORY = "player_history";
    private static final String PATH_PLAYER_EFFECTS = "player_effects";
    
    public static class PlayersTable implements BaseColumns, PlayersColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLAYERS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.ridteam.players";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.ridteam.players";
        
        public static Cursor getPlayersWithRoles(SQLiteDatabase mDb) {
    		String strQ = "select players.*, roles.name, roles.picture from players " +
    				"left outer join roles on players.role_id = roles._id ";
    		Log.d("getPlayers", " players, strq= " + strQ);
    		return mDb.rawQuery(strQ, null);
    	}
    } 

    public static class PlayerHistoryTable implements BaseColumns, PlayerHistoryColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLAYER_HISTORY).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.ridteam.playerhistory";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.ridteam.playerhistory";
    } 

    public static class PlayerEffectsTable implements BaseColumns, PlayerEffectsColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLAYER_EFFECTS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.ridteam.playereffects";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.ridteam.playereffects";
    } 

    public static class RolesTable implements BaseColumns, RolesColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ROLES).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.ridteam.roles";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.ridteam.roles";
    } 

    public static class RolePropertiesTable implements BaseColumns, RolePropertiesColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ROLEPROPERTIES).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.ridteam.roleproperties";
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.ridteam.roleproperties";
    } 

}
