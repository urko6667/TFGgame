package com.example.urko.gameproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import com.example.urko.gameproject.Worlds.World;
import com.example.urko.gameproject.gfx.Assets;
import com.example.urko.gameproject.gfx.GameCamera;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity implements Runnable{
    private String world1 = "file:///android_asset/" + "world1.txt";
    //classes
    private Handler handler;
    private GameCamera gameCamera;
    private World world;
private Canvas canvas;
private Bitmap bitmap;
private boolean running=false;
private Thread thread;
private ImageView imageView;
private int width, height, tileWidth, tileHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View decorView = getWindow().getDecorVie w();
// Hide the status bar.
       // int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        //decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        //ActionBar actionBar = getActionBar();
       // actionBar.hide();
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        width = size. x;
        height = size. y;
        tileWidth=width/16;
        tileHeight=height/9;
        Log.d("mytag","oncreate");
        init();
    }
    public void init(){
        Log.d("mytag","init");
        handler= new Handler(this);
        gameCamera= new GameCamera(handler,0,0);
        imageView=(ImageView) findViewById(R.id.myimageview);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        imageView.setImageBitmap(bitmap);
        canvas = new Canvas(bitmap);
        Assets.init(getResources(), width, height);

        world = new World(handler, world1,this,tileWidth,tileHeight);
        start();
    }

    public void run(){
        while(running) {
            tick();
            render();
            try {
                Thread.sleep(20);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();

            }
        }
        stop();
    }
    public void tick(){
        world.tick();
    }
    public void render(){
        Log.d("mytag","render");
        //canvas.drawColor(Color.BLACK);
       /* Bitmap grass = Bitmap.createScaledBitmap(Assets.grass, tileWidth, tileHeight, true);
        canvas.drawBitmap(grass, 0, 0, null);
        Bitmap dirt = Bitmap.createScaledBitmap(Assets.dirt, tileWidth, tileHeight, true);
        canvas.drawBitmap(dirt, tileWidth*2, 0, null);
        Bitmap stone = Bitmap.createScaledBitmap(Assets.stone, tileWidth, tileHeight, true);
        canvas.drawBitmap(stone, tileWidth*4, 0, null);*/
        world.render(canvas);
    }
    public synchronized void start(){
        Log.d("mytag","start");
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }
    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }



}
