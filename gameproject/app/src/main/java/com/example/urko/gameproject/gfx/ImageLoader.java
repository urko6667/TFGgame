package com.example.urko.gameproject.gfx;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.urko.gameproject.R;

import java.io.IOException;

public class ImageLoader {
    public static Bitmap loadImage(Resources res, int res1) {
        System.out.println("intenta foto");
        Bitmap image;
        return  image = BitmapFactory.decodeResource(res, res1);
        }
        }

