package com.example.urko.gameproject.Entities.statics;

import com.example.urko.gameproject.Entities.Entity;
import com.example.urko.gameproject.Handler;

public abstract class EstaticEntity extends Entity {
    public EstaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler,x,y,width,height);


    }
    @Override
    public  void die(){

    }
}
