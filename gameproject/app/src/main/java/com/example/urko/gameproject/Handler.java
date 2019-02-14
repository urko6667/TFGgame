package com.example.urko.gameproject;

import com.example.urko.gameproject.Worlds.World;
import com.example.urko.gameproject.gfx.GameCamera;

public class Handler {
    private MainActivity game;
    private World world;

    public Handler(MainActivity game) {
        this.game=game;
    }


    public int getWidth() {
        return game.getWidth();
    }
    public int getHeight() {
        return game.getHeight();
    }

    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }



    public MainActivity getGame() {
        return game;
    }
    public void setGame(MainActivity game) {
        this.game = game;
    }
    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }

}
