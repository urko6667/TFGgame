package com.example.urko.gameproject.Entities.Creatures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;

import com.example.urko.gameproject.Entities.Entity;
import com.example.urko.gameproject.Handler;
import com.example.urko.gameproject.IA.AStar;
import com.example.urko.gameproject.gfx.Assets;

import static android.graphics.Rect.intersects;
import static java.lang.Math.abs;

public class Pokemon extends Creature{

    private long lastMoveTimer, moveCooldown = 400, IACooldown=2000, moveTimer = moveCooldown, lastIATimer, IATimer = IACooldown;
    private Bitmap pokemonimg = Assets.pokemon[0];
    private int tileWidth, tileHeight;
    private float playerX, playerY;
    public static int marginTop, marginLeft, marginRight, marginBottom;
    private Paint paint;
    private int[] Dir;
    private int[] path;
    private int indice;
    private AStar astar;
    public Pokemon(Handler handler, float x, float y, int tileWidth, int tileHeight) {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT, marginTop, marginRight, marginBottom, marginLeft);

       /* bounds.x = 1;
        bounds.y = 1;
        bounds.width = 30;
        bounds.height = 30;*/
        Dir = new int[2];
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
        path = new int[30];
        astar = handler.getAStar();
    }


    @Override
    public void tick() {
        // TODO Auto-generated method stub
        //RandomMove();
        IAMove();
        move();
        bounds.set((int)x+marginLeft,(int)y+marginTop,(int)x+tileWidth-marginRight,(int)y+tileHeight-marginBottom);
        setBounds(bounds);
    }
    public void IAMove(){
        IATimer += System.currentTimeMillis() - lastIATimer;
        lastIATimer = System.currentTimeMillis();
        if(IATimer < IACooldown)
            return;
        System.out.println("estra IAMOVE");
        path=astar.getpath((int)x/tileWidth,(int)y/tileHeight,(int)handler.getWorld().getEntityManager().getPlayer().getX()/tileWidth,(int)handler.getWorld().getEntityManager().getPlayer().getY()/tileHeight);
        indice=0;
        IATimer=0;
        for(int i=0; i<path.length;i++) {
            System.out.println("path: ");
            System.out.print(path[i]);
        }
        //astar = handler.getAStar();
        /*Dir=handler.getAStar().getResult((int)x/handler.getGame().getTileWidth(),(int)y/handler.getGame().getTileHeight(),(int)handler.getWorld().getEntityManager().getPlayer().getX()/handler.getGame().getTileWidth(),(int)handler.getWorld().getEntityManager().getPlayer().getY()/handler.getGame().getTileHeight());
        Dir[0]=-Math.abs((int)x/handler.getGame().getTileWidth());
        Dir[1]=-Math.abs((int)y/handler.getGame().getTileHeight());*/
        Log.d("mytag","IA x: "+Dir[0]+"  IA Y: "+Dir[1]);
    }
    public void move(){
        moveTimer += System.currentTimeMillis() - lastMoveTimer;
        lastMoveTimer = System.currentTimeMillis();
        if(moveTimer < moveCooldown||path[indice]==0)
            return;
        System.out.println("idice: "+ indice+", movimiento: "+ path[indice]);
        if(path[indice]==1){
            yMove=-tileHeight;
            indice++;
            moveY();
        }else if(path[indice]==2){
            xMove=tileWidth;
            indice++;
            moveX();
        }
        else if(path[indice]==3){
            yMove=tileHeight;
            indice++;
            moveY();
        }else if(path[indice]==4){
            xMove=-tileWidth;
            indice++;
            moveX();
        }
        moveTimer=0;
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
            case 1://DERECHA

                xMove=tileWidth;

                pokemonimg = Assets.pokemon[2];
                moveX();
                break;
            case 2://IZQUIERDA

                xMove=-tileWidth;

                pokemonimg = Assets.pokemon[1];
                moveX();
                break;
            case 3://ARRIBA

                yMove=tileHeight;

                pokemonimg = Assets.pokemon[0];
                moveY();
                break;
            case 4://ABAJO

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
            if (intersects(e.getCollisionBounds(0,0), bounds)) {
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

           // if(!collisionWithTile(tx, (int) (y + marginTop) / handler.getGame().getTileHeight()) &&
                    //!collisionWithTile(tx, (int) (y + height - marginBottom) / handler.getGame().getTileHeight())){
                x += xMove;
            //}
        }
    }
    public void moveY() {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (intersects(e.getCollisionBounds(0,0), bounds)) {
                //handler.getWorld().setGameover();
                //e.hurt(1);
                //hurt(1);
                return;
            }
        }
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
