package org.newdawn.spaceinvaders.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Sprite;
import org.newdawn.spaceinvaders.SystemTimer;

import java.sql.Struct;

/**
 * The entity that represents the players ship
 *
 * @author Kevin Glass
 */
public class BossEntity extends Entity {
    /** The game in which the ship exists */
    private Game game;
    private double moveSpeed = 100;

    /** The time since the last frame change took place */
    private long lastFrameChange;
    /** The frame duration in milliseconds, i.e. how long any given frame of animation lasts */
    private long frameDuration = 250;

    public int hp = 1;

    public Boolean immortal =false;


    /**
     * Create a new entity to represent the players ship
     *
     * @param game The game in which the ship is being created
     * @param ref The reference to the sprite to show for the ship
     * @param x The initial x location of the player's ship
     * @param y The initial y location of the player's ship
     */
    public BossEntity(Game game,String ref,int x,int y) {
        super(ref,x,y);

        this.game = game;

        dx = -moveSpeed;
    }

    /**
     * Request that the ship move itself based on an elapsed ammount of
     * time
     *
     * @param delta The time that has elapsed since last move (ms)
     */
    public void move(long delta) {
        // since the move tells us how much time has passed
        // by we can use it to drive the animation, however
        // its the not the prettiest solution
        lastFrameChange += delta;

        // if we need to change the frame, update the frame number
        // and flip over the sprite in use
        if (lastFrameChange > frameDuration) {
            // reset our frame change time counter
            lastFrameChange = 0;

        }

        // if we have reached the left hand side of the screen and
        // are moving left then request a logic update
        if ((dx < 0) && (x < 10)) {
            game.updateLogic();
        }
        // and vice vesa, if we have reached the right hand side of
        // the screen and are moving right, request a logic update
        if ((dx > 0) && (x > 710)) {
            game.updateLogic();
        }

        // proceed with normal move
        super.move(delta);
    }
    public void doLogic() {
        // swap over horizontal movement and move down the
        // screen a bit
        dx = -dx;
        y += 10;

        // if we've reached the bottom of the screen then the player
        // dies
        if (y > 570) {
            game.notifyDeath();
        }
    }

    public void ImmortallityCheck(){
        long lastLoopTime = SystemTimer.getTime();
        while(true){
            lastLoopTime = SystemTimer.getTime();
            if(lastLoopTime %10==0){
                immortal= true;
            }
        }
    }


    public double getHp() {
        return hp;
    }

    /**
     * Notification that the player's ship has collided with something
     *
     * @param other The entity with which the ship has collided
     */
    public void collidedWith(Entity other) {
        if (other instanceof ShotEntity && immortal==false) {
            hp--;
            if(hp<=0){
                game.removeEntity(this);
                game.notifyBossKilled();
            }
        }
    }
}