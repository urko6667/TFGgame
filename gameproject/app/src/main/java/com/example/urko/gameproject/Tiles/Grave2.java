package com.example.urko.gameproject.Tiles;

import com.example.urko.gameproject.gfx.Assets;

public class Grave2 extends Tile {
    public Grave2(int id) {
        super(Assets.textures[3],id);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
