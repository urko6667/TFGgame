package com.example.urko.gameproject.Tiles;

import com.example.urko.gameproject.gfx.Assets;

public class Grave3 extends Tile {
    public Grave3(int id) {
        super(Assets.textures[4],id);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
