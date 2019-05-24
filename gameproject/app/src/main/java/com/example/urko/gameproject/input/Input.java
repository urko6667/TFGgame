package com.example.urko.gameproject.input;

import android.util.Log;

public class Input {

    private boolean[] keys;
    public boolean up, down, left, right, attack;
    public int attackDir=3;
    public Input(){
        keys = new boolean [4];
    }
    public void tick(){
        up=keys[0];
        right=keys[1];
        down=keys[2];
        left=keys[3];

    }
public void setAttack(boolean b){
        attack=b;
}
    public void setUp(){
        keys[0]=true;
        attackDir=0;
        Log.d("mytag","entra up");
    }
    public void setRight(){
        keys[1]=true;
        attackDir=1;
        Log.d("mytag","entra rigth");
    }
    public void setDown(){
        keys[2]=true;
        attackDir=2;
        Log.d("mytag","entra down");
    }
    public void setLeft(){
        keys[3]=true;
        attackDir=3;
        Log.d("mytag","entra left");

    }
    public void setFalse(){
        keys[0]=false;
        keys[1]=false;
        keys[2]=false;
        keys[3]=false;
    }


}
