package com.example.urko.gameproject.Tiles;

import com.example.urko.gameproject.gfx.Assets;

public class Grave6 extends Tile {
    public Grave6(int id) {
        super(Assets.textures[7],id);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
