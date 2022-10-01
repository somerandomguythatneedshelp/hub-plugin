package com.f2erg.vexctyhub.server.data.type;

public enum GameType implements ServerType {

    KITPVP("KitPvP", "Kitpvp", 1),
    SPLEEF("Spleef", "Spleef", 2),
    IMPOSSIBLESURVIVAL("Impossible Survival", "impsur", 3),
    NOOBSURVIVAL("Noob Survival", "noosur", 4);

    private static final GameType[] VALUES = values();

    private final String name, dbName;
    private final int id;

    GameType(String name, String dbName, int id) {
        this.name = name;
        this.dbName = dbName;
        this.id = id;
    }

    /**
     * @param id The internal id
     * @return The GameType associated with that id, or null if there isn't one.
     */
    public static GameType fromId(int id) {
        for (GameType gameType : VALUES) {
            if (gameType.id == id) {
                return gameType;
            }
        }
        return null;
    }

    /**
     * @param dbName The key used in the database
     * @return The GameType associated with that key, or null if there isn't one.
     */
    public static GameType fromDatabase(String dbName) {
        for (GameType gameType : VALUES) {
            if (gameType.dbName.equals(dbName)) {
                return gameType;
            }
        }
        return null;
    }

    /**
     * Exposing this method allows people to use the array without cloning.
     * Slightly faster but not as safe since the array could be modified.
     */
    public static GameType[] getValues() {
        return VALUES;
    }

    /**
     * @return The official name of the GameType
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return The internal ID that is occasionally used in various database schemas
     */
    public int getId() {
        return id;
    }

    public String getDbName() {
        return dbName;
    }

}
