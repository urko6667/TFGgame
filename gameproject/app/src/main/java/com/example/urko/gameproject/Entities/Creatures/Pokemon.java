package com.example.urko.gameproject.Entities.Creatures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.Rectangle;

import com.example.urko.gameproject.Handler;
import com.example.urko.gameproject.gfx.Assets;

public class Pokemon extends Creature{

    private long lastMoveTimer, moveCooldown = 400, moveTimer = moveCooldown;
    private Bitmap pokemonimg = Assets.pokemon;
    private int tileWidth, tileHeight;
    public Pokemon(Handler handler, float x, float y, int tileWidth, int tileHeight) {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
       /* bounds.x = 1;
        bounds.y = 1;
        bounds.width = 30;
        bounds.height = 30;*/
        health=1;
        this.x=x-handler.getGameCamera().getyOffset();
        this.y=y-handler.getGameCamera().getyOffset();

        this.tileHeight=tileHeight;
        this.tileWidth=tileWidth;

    }


    @Override
    public void tick() {
        // TODO Auto-generated method stub
        RandomMove();

    }
    public void RandomMove() {
        moveTimer += System.currentTimeMillis() - lastMoveTimer;
        lastMoveTimer = System.currentTimeMillis();
        if(moveTimer < moveCooldown)
            return;
        float dir = (float) Math.random();
        dir=dir*4;
        Math.ceil(dir);
        /*switch((int)dir) {
            case 1:
                xMove=32;
                pokemonimg = Assets.pokemon[2];
                moveX();
                break;
            case 2:
                xMove=-32;
                pokemonimg = Assets.pokemon[1];
                moveX();
                break;
            case 3:
                yMove=32;
                pokemonimg = Assets.pokemon[0];
                moveY();
                break;
            case 4:
                yMove=-32;
                pokemonimg = Assets.pokemon[3];
                moveY();
                break;
        }*/

        moveTimer=0;


    }
    public void moveX() {
       /* Rectangle cb = getCollisionBounds(xMove, 0);
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(cb)) {
                handler.getWorld().setGameover();
                e.hurt(1);
                return;
            }
        }*/
       // if (!checkEntityCollisions(xMove, 0f)) {
            if(xMove > 0){//Moving right
                /*int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

                if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                        !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){*/
                    x += xMove;
               /* }else{
                    x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                }*/

            }else if(xMove < 0){//Moving left
               /* int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

                if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                        !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){*/
                    x += xMove;
               /* }else{
                    x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                }*/

            }
        //}
    }
    public void moveY() {
        //Rectangle cb = getCollisionBounds(0, yMove);
       /* for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(cb)) {
                e.hurt(1);
                return;
            }
        }*/
        if (!checkEntityCollisions(0, yMove)) {
            /*if(yMove < 0){//Up
                int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

                if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                        !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){*/
                    y += yMove;
               /* }else{
                    y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                }*/

            /*}else if(yMove > 0){//Down
                int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

                if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                        !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){*/
                    y += yMove;
                /*}else{
                    y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                }*/

            }
        //}
    }

    @Override
    public void die() {
        handler.getWorld().addPokemons();
        active=false;

    }

    @Override
    public void render(Canvas g) {
        // TODO Auto-generated method stub

        g.drawBitmap(Assets.pokemon.createScaledBitmap(Assets.pokemon, tileWidth, tileHeight, false), x, y, null);

        //g.drawBitmap(pokemonimg, (int) (x - handler.getGameCamera().getxOffset()),
                //(int) (y - handler.getGameCamera().getyOffset()));

		 /*g.setColor(Color.red);
		 g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int)
		 (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width,
		 bounds.height);*/
    }



}
