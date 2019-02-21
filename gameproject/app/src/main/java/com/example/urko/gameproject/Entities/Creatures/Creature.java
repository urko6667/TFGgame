package com.example.urko.gameproject.Entities.Creatures;

import android.util.Log;

import com.example.urko.gameproject.Entities.Entity;
import com.example.urko.gameproject.Handler;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_WIDTH = 32;
    public static final int DEFAULT_HEIGHT = 32;
    protected float speed, xMove, yMove;
    protected int marginTop, marginLeft, marginRight, marginBottom;
    public Creature(Handler handler, float x, float y, int height, int width, int marginTop, int marginRight, int marginBottom, int marginLeft) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        yMove = 0;
        xMove = 0;

        this.marginTop=marginTop;
        this.marginLeft=marginLeft;
        this.marginRight=marginRight;
        this.marginBottom=marginBottom;
        /*
         * width=DEFAULT_WIDTH; height= DEFAULT_HEIGHT;
         */
    }


    public void move() {
        //if (!checkEntityCollisions(xMove, 0f))
            moveX();
        //if (!checkEntityCollisions(0, yMove))
            moveY();

    }
    @Override
    public  void die(){

    }
    public void contactDamage(){

    }
    public void moveX(){
        Log.d("mytag","x="+ x +", bounds.right="+bounds.right+", bounds.width=" + bounds.width());
        Log.d("mytag","y="+ y +", bounds.top="+bounds.top+", bounds.height=" + bounds.height());
        Log.d("mytag","x="+ x +",width=" + width + ", marginright=" + marginRight + ", tilewidth=" + handler.getGame().getTileWidth() +", coord x="+((x + xMove + width + marginRight) / handler.getGame().getTileWidth()));
        if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + width + marginRight) / handler.getGame().getTileWidth();

            if(!collisionWithTile(tx, (int) (y + marginTop) / handler.getGame().getTileHeight()) &&
                    !collisionWithTile(tx, (int) (y + height - marginBottom) / handler.getGame().getTileHeight())){
                x += xMove;
            }else{
                x = tx * handler.getGame().getTileWidth() + marginRight - width +marginLeft - 1;
            }

        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + marginLeft) / handler.getGame().getTileWidth();

            if(!collisionWithTile(tx, (int) (y + marginTop) / handler.getGame().getTileHeight()) &&
                    !collisionWithTile(tx, (int) (y + height - marginBottom) / handler.getGame().getTileHeight())){
                x += xMove;
            }else{
                x = tx * handler.getGame().getTileWidth() + handler.getGame().getTileWidth() - marginRight;
            }

        }
    }
    public void moveY(){
        Log.d("mytag","entra moveY, yMove=" + yMove);
        if(yMove > 0){//Up
            int ty = (int) (y + yMove + height - marginBottom) / handler.getGame().getTileHeight();

            if(!collisionWithTile((int) (x + marginLeft) / handler.getGame().getTileWidth(), ty) &&
                    !collisionWithTile((int) (x + width - marginRight) / handler.getGame().getTileWidth(), ty)){
            Log.d("mytag","entra moveY2, y="+y);

            y += yMove;
            Log.d("mytag","entra moveY3, y="+y);

            }else{

                y = ty * handler.getGame().getTileHeight() - marginTop - height - 1;
            }

        }else if(yMove < 0){//Down
            int ty = (int) (y + yMove - marginTop) / handler.getGame().getTileWidth();

            if(!collisionWithTile((int) (x + marginLeft) / handler.getGame().getTileWidth(), ty) &&
                    !collisionWithTile((int) (x + width - marginRight) / handler.getGame().getTileWidth(), ty)){
                y += yMove;
            }else{
                y = ty * handler.getGame().getTileHeight() + handler.getGame().getTileHeight() - marginBottom;            }

        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    // SETER & GETTER
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public void  setMarginTop(int m){
        marginTop=m;
    }
    public void  setMarginRight(int m){
        marginRight=m;
    }
    public void  setMarginLeft(int m){
        marginLeft=m;
    }
    public void  setMarginBottom(int m){
        marginBottom=m;
    }
}
