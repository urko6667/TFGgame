package com.example.urko.gameproject.gfx;

import android.content.Entity;

import com.example.urko.gameproject.Handler;
import com.example.urko.gameproject.Tiles.Tile;

public class GameCamera {

    private float xOffset, yOffset;
    private Handler handler;
    public GameCamera(Handler handler, float xOffset, float yOffset ) {
        this.xOffset=xOffset;
        this.handler=handler;
        this.yOffset=yOffset;
    }
    public void checkBlankSpace() {
        if(xOffset < 0){
            xOffset = 0;
        }else if(xOffset > handler.getWorld().getWidth() * handler.getGame().getTileWidth() - handler.getWidth()){
            xOffset = handler.getWorld().getWidth() * handler.getGame().getTileWidth() - handler.getWidth();
        }

        if(yOffset < 0){
            yOffset = 0;
        }else if(yOffset > handler.getWorld().getHeight() * handler.getGame().getTileHeight() - handler.getHeight()){
            yOffset = handler.getWorld().getHeight() * handler.getGame().getTileHeight() - handler.getHeight();
        }
    }
    public void centerOnEntity(Object e) {
       /* xOffset=e.getX() - handler.getWidth()/2 +e.getWidth() /2;
        yOffset=e.getY() - handler.getHeight()/2 + e.getHeight() /2;
        checkBlankSpace();*/
    }
    public void move(float xAmt, float yAmt) {
        xOffset+= xAmt;
        yOffset+=yAmt;
        checkBlankSpace();
    }
    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }



}
