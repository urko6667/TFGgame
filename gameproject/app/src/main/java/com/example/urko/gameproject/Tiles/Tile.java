package com.example.urko.gameproject.Tiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.urko.gameproject.gfx.Assets;

public class Tile {


    //static stuff
    public static Tile[] tiles = new Tile[256];
    public static Tile DirtTile = new DirtTile(0);
    public static Tile grasstile = new GrassTile(1);
    //public static Tile RockTile = new RockTile(2);


    public static Tile Grave1 = new Grave1(2);
    public static Tile Grave2 = new Grave2(3);
    public static Tile Grave3 = new Grave3(4);
    public static Tile Grave4 = new Grave4(5);
    public static Tile Grave5 = new Grave5(6);
    public static Tile Grave6 = new Grave6(7);
    public static Tile Grave7 = new Grave7(8);
    public static Tile Grave8 = new Grave8(9);



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
