package com.example.urko.gameproject.Tiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.urko.gameproject.gfx.Assets;

public class Tile {


    //static stuff
    public static Tile[] tiles = new Tile[256];
    public static Tile grasstile = new GrassTile(0);
    public static Tile RockTile = new RockTile(2);
    public static Tile DirtTile = new DirtTile(1);

    //class
    protected Bitmap texture;
    protected final int id;
    public Tile(Bitmap texture, int id) {
        this.texture=texture;
        this.id=id;

        tiles[id] = this;
    }

    public void tuck() {

    }

    public void render(Canvas g, int x, int y,int tileWidth ,int tileHeight) {


        g.drawBitmap(texture.createScaledBitmap(texture, tileWidth, tileHeight, false), x, y, null);

    }

    public boolean isSolid() {
        return false;
    }
    public int getId() {
        return id;

    }
}
