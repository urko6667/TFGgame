package com.example.urko.gameproject.gfx;

import android.graphics.Bitmap;

public class SpriteSheet {
    private Bitmap sheetInput, sheetoutput;

    public SpriteSheet(Bitmap sheet) {
        this.sheetInput=sheet;
    }
    public Bitmap crop(int x, int y, int width, int height) {
        return sheetoutput= Bitmap.createBitmap(sheetInput,x,y,width,height);
    }
}
