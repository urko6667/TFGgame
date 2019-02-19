package com.example.urko.gameproject.gfx;

import android.graphics.Bitmap;

public class Animation {
    private int speed, index;
    private long lastTime, timer;
    private Bitmap[] frames;


    public Animation(int speed, Bitmap[] frames) {
        this.speed = speed;
        this.frames = frames;
        index=0;
        lastTime = System.currentTimeMillis();
    }
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer>speed) {
            index++;
            timer=0;
            if(index>=frames.length)
                index=0;

        }
    }

    public boolean swordTick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer>speed) {
            index++;
            timer=0;
            if(index>=frames.length) {
                index=0;
                return false;
            }
        }
        return true;
    }
    public Bitmap getCurrentFrame() {
        return frames[index];
    }
    public Bitmap getLastFrame() {
        return frames[0];
    }
}
