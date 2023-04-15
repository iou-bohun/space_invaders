package org.newdawn.spaceinvaders.entity;

import org.newdawn.spaceinvaders.Game;

/**
 * An entity which represents one of our space invader aliens.
 *
 * @author Kevin Glass
 */
public class LobbyUI extends Entity {
    /** The game in which the entity exists */
    /**
     * Create a new alien entity
     *
     * @param x The intial x location of this alien
     * @param y The intial y location of this alient
     */

    public LobbyUI(String ref, int x, int y) {
        super(ref,x,y);
    }
    public void collidedWith(Entity otherF) {
        // collisions with aliens are handled elsewhere
    }
}
