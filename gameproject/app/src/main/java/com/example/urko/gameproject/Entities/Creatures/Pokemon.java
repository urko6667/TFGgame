package com.example.urko.gameproject.Entities.Creatures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;

import com.example.urko.gameproject.Entities.Entity;
import com.example.urko.gameproject.Handler;
import com.example.urko.gameproject.gfx.Assets;

import static android.graphics.Rect.intersects;

public class Pokemon extends Creature{

    private long lastMoveTimer, moveCooldown = 400, moveTimer = moveCooldown;
    private Bitmap pokemonimg = Assets.pokemon[0];
    private int tileWidth, tileHeight;
    public static int marginTop, marginLeft, marginRight, marginBottom;
    private Paint paint;
    public Pokemon(Handler handler, float x, float y, int tileWidth, int tileHeight) {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT, marginTop, marginRight, marginBottom, marginLeft);
       /* bounds.x = 1;
        bounds.y = 1;
        bounds.width = 30;
        bounds.height = 30;*/
        marginBottom=tileHeight/10;
        marginLeft=tileWidth/10;
        marginRight=tileWidth/10;
        marginTop=tileHeight/5;
        setMarginBottom(marginBottom);
        setMarginLeft(marginLeft);
        setMarginRight(marginRight);
        setMarginTop(marginTop);
        health=1;
        this.x=x-handler.getGameCamera().getyOffset();
        this.y=y-handler.getGameCamera().getyOffset();

        this.tileHeight=tileHeight;
        this.tileWidth=tileWidth;
        paint = new Paint();
        this.paint.setColor(Color.RED);
    }


    @Override
    public void tick() {
        // TODO Auto-generated method stub
        RandomMove();
        bounds.set((int)x+marginLeft,(int)y+marginTop,(int)x+tileWidth-marginRight,(int)y+tileHeight-marginBottom);
        setBounds(bounds);
    }
    public void RandomMove() {
        moveTimer += System.currentTimeMillis() - lastMoveTimer;
        lastMoveTimer = System.currentTimeMillis();
        if(moveTimer < moveCooldown)
            return;
        float dir = (float) Math.random();
        dir=dir*4;
        Math.ceil(dir);
        switch((int)dir) {
            case 1:
                xMove=tileWidth;
                pokemonimg = Assets.pokemon[2];
                moveX();
                break;
            case 2:
                xMove=-tileWidth;
                pokemonimg = Assets.pokemon[1];
                moveX();
                break;
            case 3:
                yMove=tileHeight;
                pokemonimg = Assets.pokemon[0];
                moveY();
                break;
            case 4:
                yMove=-tileHeight;
                pokemonimg = Assets.pokemon[3];
                moveY();
                break;
        }

        moveTimer=0;


    }
    public void moveX(){
       // Rectangle cb = getCollisionBounds(xMove, 0);
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (intersects(e.getCollisionBounds(), bounds)) {
                //handler.getWorld().setGameover();
                //e.hurt(1);
                //hurt(1);
                return;
            }
        }

        if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + width - marginRight) / handler.getGame().getTileWidth();

            if(!collisionWithTile(tx, (int) (y + marginTop) / handler.getGame().getTileHeight()) &&
                    !collisionWithTile(tx, (int) (y + height - marginBottom) / handler.getGame().getTileHeight())){
                x += xMove;
            }
        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + marginLeft) / handler.getGame().getTileWidth();

            if(!collisionWithTile(tx, (int) (y + marginTop) / handler.getGame().getTileHeight()) &&
                    !collisionWithTile(tx, (int) (y + height - marginBottom) / handler.getGame().getTileHeight())){
                x += xMove;
            }
        }
    }
    public void moveY() {
        if (yMove > 0) {//Up
            int ty = (int) (y + yMove + height - marginBottom) / handler.getGame().getTileHeight();
            if (!collisionWithTile((int) (x + marginLeft) / handler.getGame().getTileWidth(), ty) &&
                    !collisionWithTile((int) (x + width - marginRight) / handler.getGame().getTileWidth(), ty)) {
                y += yMove;
            }
        } else if (yMove < 0) {//Down
            int ty = (int) (y + yMove - marginTop) / handler.getGame().getTileWidth();

            if (!collisionWithTile((int) (x + marginLeft) / handler.getGame().getTileWidth(), ty) &&
                    !collisionWithTile((int) (x + width - marginRight) / handler.getGame().getTileWidth(), ty)) {
                y += yMove;
            }
        }
    }

    @Override
    public void die() {
        handler.getWorld().addPokemons();
        active=false;

    }

    @Override
    public void render(Canvas g) {
        // TODO Auto-generated method stub

        g.drawBitmap(pokemonimg.createScaledBitmap(pokemonimg, tileWidth, tileHeight, false), (int) (x-handler.getGame().getGameCamera().getxOffset()), (int) (y-handler.getGame().getGameCamera().getyOffset()), null);
        //g.drawRect(bounds,paint);

        //g.drawBitmap(pokemonimg, (int) (x - handler.getGameCamera().getxOffset()),
                //(int) (y - handler.getGameCamera().getyOffset()));

		 /*g.setColor(Color.red);
		 g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int)
		 (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width,
		 bounds.height);*/
    }



}
