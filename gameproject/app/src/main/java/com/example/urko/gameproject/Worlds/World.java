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
    private int spawnX, spawnY, lvl;
    private int[][] tiles;
    private int puntosint=0;
    private String puntostring="0",file;
    private long lastSpawnTimer, spawnCooldown = 800, spawnTimer = spawnCooldown;
    private EntityManager entityManager;
    private boolean gameover=false;
    private int[][] blocks;
    private Context context;
    private int tileHeight, tileWidth;
    public World(Handler handler,String path,Context context,int tileWidth, int tileHeight,int lvl) {
        this.handler=handler;
        this.lvl=lvl;
        loadWorld(path,context);
        this.context=context;
        this.tileHeight=tileHeight;
        this.tileWidth=tileWidth;
        if(lvl==1){
            startLvl1();
        }else if(lvl==2){
            startLvl1();
        }else if(lvl==3){
            startLvl1();
        }else{
            entityManager = new EntityManager(handler, new Player(handler, 32,32,this.tileWidth,this.tileHeight));
        }


        //entityManager = new EntityManager(handler, new Player(handler, 32,32,this.tileWidth,this.tileHeight));
       // entityManager.getPlayer().setX(tileWidth);
        //entityManager.getPlayer().setY(tileHeight);
        //entityManager.addEntity(new Pokeball(handler,handler.getGame().getTileWidth()*2,handler.getGame().getTileHeight()*3));
       // entityManager.addEntity(new Pokeball(handler,handler.getGame().getTileWidth()*3,handler.getGame().getTileHeight()*2));
        //entityManager.addEntity(new Pokemon(handler,5*tileWidth,5*tileHeight,tileWidth,tileHeight));
       // entityManager.addEntity(new Pokemon(handler,tileWidth*4,tileHeight*4,tileWidth,tileHeight));

    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
    public void addPokemons() {

        entityManager.addEntity(new Pokemon(handler,tileWidth*4,tileHeight*4,tileWidth,tileHeight));
        entityManager.addEntity(new Pokemon(handler,tileWidth*3,tileHeight*7,tileWidth,tileHeight));
        entityManager.addEntity(new Pokemon(handler,tileWidth*7,tileHeight*3,tileWidth,tileHeight));
        int i=0;
        while( i<2) {
            float px=(float) Math.random();
            float py=(float) Math.random();
            px=(float) Math.ceil(px*width);
            py=(float) Math.ceil(py*height);
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
        int xStart =(int) Math.max(0, handler.getGame().getGameCamera().getxOffset() / handler.getGame().getTileWidth());
        int xEnd = (int) Math.min(width, (handler.getGame().getGameCamera().getxOffset() + handler.getWidth()) / handler.getGame().getTileWidth() + 1);
        int yStart =(int) Math.max(0, handler.getGame().getGameCamera().getyOffset() / handler.getGame().getTileHeight());
        int yEnd =(int) Math.min(height, (handler.getGame().getGameCamera().getyOffset() + handler.getGame().getHeight()) / handler.getGame().getTileHeight() + 1);
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
            return Tile.Grave4;
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
        blocks = new int[width*height][2];
        int b=0;
        tiles = new int[width][height];
        for(int y = 0;y<height;y++) {
            for(int x =0;x<width;x++) {
                tiles[x][y] = utils.parseInt(tokens[(x + y *width)+4]);
                System.out.println(tiles[x][y]);
                if(getTile(x,y).isSolid()){
                   blocks[b][0]=x;
                   blocks[b][1]=y;
                   b++;
                }
            }
        }
        handler.setAStar(width,height,blocks);



    }

    public void startLvl1(){
        entityManager = new EntityManager(handler, new Player(handler, spawnX*tileWidth,spawnY*tileHeight,this.tileWidth,this.tileHeight));

    }
    public void startLvl2(){

    }
    public void startLvl3(){

    }


}
