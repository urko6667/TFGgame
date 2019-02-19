package com.example.urko.gameproject.input;

public class Input {

    private boolean[] keys;
    public boolean up, down, left, right;
    public Input(){
        keys = new boolean [4];
    }
    public void tick(){
        up=keys[0];
        right=keys[1];
        down=keys[2];
        left=keys[3];
    }
    public void setDown(boolean k){
        keys[2]=k;
    }



}
