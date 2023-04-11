package org.newdawn.spaceinvaders.entity;

import org.newdawn.spaceinvaders.Game;

/**
 * An entity which represents one of our space invader aliens.
 * 
 * @author Kevin Glass
 */
public class GameUi extends Entity {
	/** The game in which the entity exists */
	private Game game;
	private boolean used = false;

	/**
	 * Create a new alien entity
	 *
	 * @param game The game in which this entity is being created
	 * @param x The intial x location of this alien
	 * @param y The intial y location of this alient
	 */

	public GameUi(Game game, String ref, int x, int y) {
		super(ref,x,y);
		this.game = game;
	}
	public void RemoveThis(){
		if (used) {
			return;
		}
		game.removeEntity(this);
		used = true;
	}
	public void collidedWith(Entity other) {
		// collisions with aliens are handled elsewhere
	}
}