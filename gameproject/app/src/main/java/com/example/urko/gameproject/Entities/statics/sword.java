package com.example.urko.gameproject.Entities.statics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.urko.gameproject.Handler;
import com.example.urko.gameproject.Tiles.Tile;
import com.example.urko.gameproject.gfx.Animation;
import com.example.urko.gameproject.gfx.Assets;

public class sword extends EstaticEntity {
    private int orientation = 1;
    private Animation animation;
    private int tileWidth, tileHeight;
    public sword(Handler handler, float x, float y, int orientation, int tileWidth, int tileHeight) {
        super(handler, x, y, tileWidth, tileHeight);
        // TODO Auto-generated constructor stub
        this.x = x;
        this.y = y;
        this.tileHeight=tileHeight;
        this.tileWidth=tileWidth;
        //this.orientation=orientation;
        bounds = new Rect((int) x, (int) y, (int) x + width, (int) y + height);
        /*bounds.x= 32 ;
        bounds.y= 32 ;
        bounds.width= 32 ;
        bounds.height= 32 ;*/
        System.out.println("x: " + x + "/y: " + y);
        this.orientation = orientation;
        switch (orientation) {
            case 1:
                animation = new Animation(100, Assets.rustSword_up);
                break;
            case 2:
                animation = new Animation(100, Assets.rustSword_down);
                break;
            case 3:
                animation = new Animation(100, Assets.rustSword_left);
                break;
            case 4:
                animation = new Animation(100, Assets.rustSword_right);
                break;
            default:
                animation = new Animation(100, Assets.rustSword_up);
                break;
        }

    }
    @Override
    public void tick() {
        // TODO Auto-generated method stub
        //animation.tick();

        active=animation.swordTick();
        if(!active) {
            handler.getWorld().contmov();
            handler.getInput().attack=false;

        }
    }

    @Override
    public void render(Canvas g) {
        // TODO Auto-generated method stub
       g.drawBitmap(getCurrentAnimationFrame().createScaledBitmap(getCurrentAnimationFrame(), tileWidth, tileHeight, false), (int) (x-handler.getGame().getGameCamera().getxOffset()) , (int) ((y)-handler.getGame().getGameCamera().getyOffset()), null);

       /* g.drawImage(getCurrentAnimationFrame(),(int)x,
                (int)y, 32, 32, null);*/
    }

    private Bitmap getCurrentAnimationFrame() {
        return animation.getCurrentFrame();
    }
}
