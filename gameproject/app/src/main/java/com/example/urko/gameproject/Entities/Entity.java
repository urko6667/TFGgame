package com.example.urko.gameproject.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

import com.example.urko.gameproject.Handler;

public abstract class Entity {
    protected Handler handler;
    protected float x,y;
    protected int width, height;
    protected Rect bounds;
    protected int health;
    protected boolean active=true;
    private boolean movement=true;

    public static final int DEFAULT_HEALTH = 10;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler=handler;
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        health = DEFAULT_HEALTH;
        bounds= new Rect(0,0,width,height);
    }

    public abstract void tick();
    public abstract void render(Canvas g);
    public abstract void die();


    public void hurt(int amt) {
        health-=amt;
        if(health<=0) {
            active=false;
            die();
            handler.getWorld().contador();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {

       /* for(Entity e: handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset)))
                return true;
        }*/
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
       // return new Rectangle((int)(x+bounds.x+xOffset),(int) (y+bounds.y + yOffset), bounds.width, bounds.height);
        return new Rectangle();

    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
