package ru.zont.gamelogic.logic.objs;

public interface Entity {
    /**
     * Reset entity-dependent states and enable entity mode
     */
    void enableEntity();

    /**
     * Disable entity mode
     */
    void disableEntity();

    /**
     * Checks for enabled entity state
     * @return true if enabled
     */
    boolean isEntity();

    /**
     * Get maximum HP
     * @return Max HP
     */
    int getMaxHP();

    /**
     * Get current HP
     * @return HP
     */
    int getHP();

    /**
     * Set current HP to max value
     */
    void resetHP();

    /**
     * Decrease HP
     * @param decr Amount
     * @return HP > 0
     */
    boolean decrHP(int decr);
}
