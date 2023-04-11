package org.newdawn.spaceinvaders.entity;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Sprite;
import org.newdawn.spaceinvaders.SpriteStore;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The entity that represents the players ship
 *
 * @author Kevin Glass
 */
public class BossEntity extends Entity {
    /** The game in which the ship exists */
    private Game game;
    private double moveSpeed = 100;

    private Sprite[] frames = new Sprite[2];

    private Sprite hitFrame;

    private Sprite godFrame;

    private Sprite reflectFrame;

    /** The time since the last frame change took place */
    private long lastFrameChange;
    /** The frame duration in milliseconds, i.e. how long any given frame of animation lasts */
    private long frameDuration = 250;

    public int hp;

    public Boolean immortal =false;

    public Boolean gotHit=false;

    private int frameNumber;

    public Boolean reflect =false;

    public BossEntity(Game game,int x,int y) {
        super("sprites/boss1_.png",x,y);
        frames[0] = sprite;
        frames[1] = SpriteStore.get().getSprite("sprites/boss1_.png");
        hitFrame = SpriteStore.get().getSprite("sprites/boss1_hit.png");
        godFrame = SpriteStore.get().getSprite("sprites/boss_God_Mode.png");
        reflectFrame = SpriteStore.get().getSprite("sprites/bossReflect.png");

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

            // update the frame
            frameNumber++;
            if (frameNumber >= frames.length) {
                frameNumber = 0;
            }
            sprite = frames[frameNumber];
            if (gotHit == true)
            {
                sprite = hitFrame;
            }
            else if(immortal ==true){
                sprite = godFrame;
            }
            gotHit = false;
            if(reflect ==true&&immortal ==true){
                sprite = reflectFrame;
            }
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
    public void ImmortallityCheck(int timer){
        if(timer %800 ==0){
            immortal = true;
        }
        else if(timer %1000 == 0){
            immortal = false;
        }
    }
    public void ReflectCheck(int timer){
        if(timer %800 ==0){
            reflect = true;
            immortal = true;
        }
        else if(timer %1000 == 0){
            reflect = false;
            immortal =false;
        }
    }
    public int getHp(){return (int)hp;}
    public void setHp(int hp){this.hp = hp;}


    /**
     * Notification that the player's ship has collided with something
     *
     * @param other The entity with which the ship has collided
     */
    public void collidedWith(Entity other) {
        if (other instanceof ShotEntity) {
            if(immortal ==false ){
                hp--;
                gotHit = true;
                if(hp<=0){
                    game.removeEntity(this);
                    game.notifyBossKilled();
                }
            }
            else if(reflect  == true){
                game.bossReflectStart();
            }
        }
    }
}