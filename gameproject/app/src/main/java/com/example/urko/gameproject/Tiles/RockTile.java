package com.example.urko.gameproject.Tiles;

import com.example.urko.gameproject.gfx.Assets;

public class RockTile extends Tile {
    public RockTile( int id) {
        super(Assets.stone, id);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean isSolid() {
        return true;
    }
}
