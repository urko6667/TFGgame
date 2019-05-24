package com.example.urko.gameproject.Tiles;

import com.example.urko.gameproject.gfx.Assets;

public class Grave7 extends Tile {
    public Grave7(int id) {
        super(Assets.textures[8],id);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
