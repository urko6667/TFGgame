package com.example.urko.gameproject.Tiles;

import com.example.urko.gameproject.gfx.Assets;

public class Grave8 extends Tile {
    public Grave8(int id) {
        super(Assets.textures[9],id);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
