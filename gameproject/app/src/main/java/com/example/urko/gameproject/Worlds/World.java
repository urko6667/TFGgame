package com.example.urko.gameproject.Worlds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.example.urko.gameproject.Entities.Creatures.Player;
import com.example.urko.gameproject.Entities.Creatures.Pokemon;
import com.example.urko.gameproject.Entities.EntityManager;
import com.example.urko.gameproject.Handler;
import com.example.urko.gameproject.Tiles.Tile;
import com.example.urko.gameproject.Utils.utils;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private int puntosint=0;
    private String puntostring="0",file;
    private long lastSpawnTimer, spawnCooldown = 800, spawnTimer = spawnCooldown;
    private EntityManager entityManager;
    private boolean gameover=false;
    private Context context;
    private int tileHeight, tileWidth;
    public World(Handler handler,String path,Context context,int tileWidth, int tileHeight) {
        this.handler=handler;
        loadWorld(path,context);
        this.context=context;
        this.tileHeight=tileHeight;
        this.tileWidth=tileWidth;
        entityManager = new EntityManager(handler, new Player(handler, 32,32,this.tileWidth,this.tileHeight));
        entityManager.getPlayer().setX(tileWidth);
        entityManager.getPlayer().setY(tileHeight);
        //entityManager.addEntity(new Pokeball(handler,handler.getGame().getTileWidth()*2,handler.getGame().getTileHeight()*3));
       // entityManager.addEntity(new Pokeball(handler,handler.getGame().getTileWidth()*3,handler.getGame().getTileHeight()*2));
        //entityManager.addEntity(new Pokemon(handler,25,25,tileWidth,tileHeight));

    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
    public void addPokemons() {
        int i=0;
        while( i<2) {
            float px=(float) Math.random();
            float py=(float) Math.random();
            px=(float) Math.ceil(px*40*handler.getGame().getTileWidth());
            py=(float) Math.ceil(py*40*handler.getGame().getTileHeight());
            if(!getTile((int)px,(int) py).isSolid()) {
                entityManager.addEntity(new Pokemon(handler,px,py,tileWidth,tileHeight));
                i++;
            }
        }
    }
    public void tick() {
        entityManager.tick();
    }
    public void contmov(){
        entityManager.getPlayer().movem();
    }
    public void contador() {
        if(!gameover) {
            puntosint=Integer.valueOf(puntostring);
            puntosint++;
            puntostring=Integer.toString(puntosint);
        }
    }
    public void render(Canvas g,Canvas g2) {
        int xStart =(int) Math.max(0, handler.getGameCamera().getxOffset() / handler.getGame().getTileWidth());
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / handler.getGame().getTileWidth() + 1);
        int yStart =(int) Math.max(0, handler.getGameCamera().getyOffset() / handler.getGame().getTileHeight());
        int yEnd =(int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / handler.getGame().getTileHeight() + 1);
        //entityManager.render(g);
        do {
            for (int y = yStart; y < yEnd; y++) {
                for (int x = xStart; x < xEnd; x++) {


                    getTile(x, y).render(g, (int) (x * handler.getGame().getTileWidth() - handler.getGameCamera().getxOffset()),
                            (int) (y * handler.getGame().getTileHeight() - handler.getGameCamera().getyOffset()), tileWidth, tileHeight);
                }
            }
        }while(gameover);
        entityManager.render(g2);
        int fontSize = 30;
/*
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

        g.setColor(Color.red);

        g.drawString(puntostring, 50, 50);
        if(gameover)
            g.drawString("game over", 300, 300);*/
    }
    public void setGameover() {
        gameover=true;
    }
    public Tile getTile(int x, int y) {
        if(x<0||y<0||x>=width||y>=height)
            return Tile.grasstile;
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.DirtTile;
        return t;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    private  void loadWorld(String path,Context context) {
         file = new utils().loadFileAsString(path,context);
        String[] tokens = file.split("\\s+");
        Log.d("mytag", ""+tokens.length);
        width =utils.parseInt(tokens[0]);
        height =utils.parseInt(tokens[1]);
        spawnX =utils.parseInt(tokens[2]);
        spawnY =utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0;y<height;y++) {
            for(int x =0;x<width;x++) {
                tiles[x][y] = utils.parseInt(tokens[(x + y *width)+4]);
            }
        }
    }
}
