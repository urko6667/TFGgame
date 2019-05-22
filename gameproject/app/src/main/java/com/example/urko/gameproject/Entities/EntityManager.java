package com.example.urko.gameproject.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;

import com.example.urko.gameproject.Entities.Creatures.Player;
import com.example.urko.gameproject.Handler;
import com.example.urko.gameproject.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Rect.intersects;

public class EntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private boolean invuln = false;


    public EntityManager(Handler handler, Player player) {

        this.handler=handler;
        this.player=player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }
    public void tick() {

        for(int i = 0; i< entities.size();i++) {
            Entity e = entities.get(i);
            Log.d("mytag","" + player.health);
            e.tick();
            if(!e.isActive() && player.health == 0) {
                Log.d("mytaga","FUCKING RIP " + e); //Player desaparece
                entities.remove(e);
            }
        }
        Collections.sort(entities, new renderSorter());
        for(int i = 1; i< entities.size();i++) {

           enemyCollision(player.bounds, entities.get(i).bounds);
            //if(enemyCollision(player.bounds, entities.get(i).bounds)){}
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
    public void iFrame(){
        invuln = true;
        Log.d("mytagaa","" + handler.getGame().getHp());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                invuln = false;
            }
        }, 2000);

        //handler.getGame().getHp().getLayoutParams().width -= 100;






        if(handler.getGame().getHp().getLayoutParams().width <= 0){
            //Es 1 porque si se deja a 0 se le va la pinza
            handler.getGame().getHp().getLayoutParams().width = 1;
        }
        //Es necesario esto para refrescar la UI
            handler.getGame().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    handler.getGame().getHp().getLayoutParams().width -= 100;
                    switch (player.health){
                        /*case 3:
                            handler.getGame().getHp().setImageResource(R.drawable.healthbar3);
                            break;*/
                        case 2:
                            handler.getGame().getHp().setImageResource(R.drawable.healthbar2);
                            break;
                        case 1:
                            handler.getGame().getHp().setImageResource(R.drawable.healthbar1);
                            break;
                        case 0:
                            handler.getGame().getHp().getLayoutParams().width = 1;
                            break;

                    }
                    handler.getGame().getHp().requestLayout();
                }
            });

    }
    public boolean enemyCollision(Rect playerrect, Rect enemyrect){

        if(intersects(playerrect,enemyrect) && !invuln){
            Log.d("mytaga","Enemigo tocado");
            player.health--;
            iFrame();
/*
            Log.d("mytaga","player X" + playerrect.exactCenterX());
            Log.d("mytaga","player Y" + playerrect.exactCenterY());
            Log.d("mytaga","enemy X" + enemyrect.exactCenterX());
            Log.d("mytaga","enemy Y" + enemyrect.exactCenterY());

            float wy = (playerrect.width() + enemyrect.width()) * (playerrect.centerY() - enemyrect.centerY());
            float hx = (playerrect.height() + enemyrect.height()) * (playerrect.centerX() - enemyrect.centerX());

            Log.d("mytaga","wy " + wy);
            Log.d("mytaga","hx " + hx);

            if (wy > hx){
                if (wy > -hx)Log.d("mytaga","TOP");
                else Log.d("mytaga","LEFT");
            }

            else{
                if (wy > -hx) Log.d("mytaga","RIGHT");
                else Log.d("mytaga","BOTTOM");
            }
*/
            return true;
        }
        else{
            return false;
        }
    }
}
