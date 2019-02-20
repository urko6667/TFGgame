package com.example.urko.gameproject.gfx;

import android.content.Entity;
import android.util.Log;

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

            Log.d("mytagM","yOffset es " + yOffset);
            Log.d("mytagM","xOffset es " + xOffset);
            Log.d("mytagM","getgameheight es " + handler.getGame().getHeight());
            Log.d("mytagM","gettileheight es " + handler.getGame().getTileHeight());
            Log.d("mytagM","getheight es " + handler.getWorld().getHeight());

            xOffset = 0;
        }else if(xOffset > handler.getGame().getWidth() * handler.getGame().getTileWidth() - handler.getGame().getWidth()){
            xOffset = handler.getWidth() * handler.getGame().getTileWidth();

        }

        if(yOffset < 0){
            yOffset = 0;
        }
        else if(yOffset > (handler.getWorld().getHeight() * handler.getGame().getTileHeight()-handler.getGame().getHeight())){
            yOffset = (handler.getWorld().getHeight() * handler.getGame().getTileHeight()-handler.getGame().getHeight());
        }

        




    }
    public void centerOnEntity(com.example.urko.gameproject.Entities.Entity e) {
        xOffset=e.getX() - handler.getGame().getWidth()/2 +e.getWidth() /2;
        yOffset=e.getY() - handler.getGame().getHeight()/2 + e.getHeight() /2;
       checkBlankSpace();
    }
    public void move(float xAmt, float yAmt) {
        xOffset+= xAmt;
        yOffset+= yAmt;

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
