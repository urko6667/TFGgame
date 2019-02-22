package com.example.urko.gameproject.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.urko.gameproject.Entities.Creatures.Player;
import com.example.urko.gameproject.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;

    public EntityManager(Handler handler, Player player) {
        this.handler=handler;
        this.player=player;
        entities = new ArrayList<Entity>();
        addEntity(player);

    }
    public void tick() {


        for(int i = 0; i< entities.size();i++) {
            Entity e = entities.get(i);
            e.tick();
            if(!e.isActive())
                entities.remove(e);
        }
        Collections.sort(entities, new renderSorter());
        for(int i = 0; i< entities.size();i++) {
            enemyCollision(player.bounds, entities.get(i).bounds);
            if(enemyCollision(player.bounds, entities.get(i).bounds)){
                Log.d("mytaga","" + i);
            }
        }
    }
    public void render(Canvas g) {
        for(Entity e : entities) {
            e.render(g);

        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public ArrayList<Entity> getEntities() {
        return entities;
    }
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    public class renderSorter implements Comparator<Entity>{

        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY()+a.getHeight() < b.getY()+b.getHeight() ) {
                return -1;
            }
            if(a.getY()+a.getHeight() == b.getY()+b.getHeight() ) {
                return 0;
            }
            return 1;
        }

    }
    public boolean enemyCollision(Rect playerrect, Rect enemyrect){
        if(Rect.intersects(playerrect,enemyrect)){
            player.die();
            return true;
        }else{return false;}
    }
}
