package com.example.urko.gameproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.urko.gameproject.Worlds.World;
import com.example.urko.gameproject.gfx.Assets;
import com.example.urko.gameproject.gfx.GameCamera;
import com.example.urko.gameproject.input.Input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity implements Runnable{
    private String world1 = "file:///android_asset/" + "world1.txt";
    //classes
    private Handler handler;
    private GameCamera gameCamera;
    private World world;
    private Input input;
private Canvas canvas, canvas2;
private Bitmap bitmap, bitmap2;
private boolean running=false;
private Thread thread;
private ImageView imageView,imageView2, control;
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

        gameCamera= new GameCamera(handler,0,0);
        input= new Input();
        handler= new Handler(this);

        control = (ImageView) findViewById(R.id.move);
        imageView=(ImageView) findViewById(R.id.myimageview);
        imageView2=(ImageView) findViewById(R.id.myimageview2);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        imageView.setImageBitmap(bitmap);
        bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        imageView2.setImageBitmap(bitmap2);
        canvas = new Canvas(bitmap);
        canvas2 = new Canvas(bitmap2);
        Assets.init(getResources(), width, height);

        gameCamera = new GameCamera(handler,0,0);


        world = new World(handler, world1,this, tileWidth, tileHeight);
        handler.setWorld(world);

        control.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        getInput().setDown(true);
                        Log.d("mytag","move down");
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        handler.getInput().setDown(false);
                        Log.d("mytag","move up");
                        break;
                    }
                }

                return false;
            }
        });

        start();
    }

    public void run(){
        while(running) {
            tick();
            render();
            try {
                Thread.sleep(1000/30);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();

            }
        }
        stop();
    }
    public void tick(){
        input.tick();
        world.tick();
        if(getInput().down)
        Log.d("mytag","es true");
    }
    public void render(){
        //canvas.drawColor(Color.BLACK);
       /* Bitmap grass = Bitmap.createScaledBitmap(Assets.grass, tileWidth, tileHeight, true);
        canvas.drawBitmap(grass, 0, 0, null);
        Bitmap dirt = Bitmap.createScaledBitmap(Assets.dirt, tileWidth, tileHeight, true);
        canvas.drawBitmap(dirt, tileWidth*2, 0, null);
        Bitmap stone = Bitmap.createScaledBitmap(Assets.stone, tileWidth, tileHeight, true);
        canvas.drawBitmap(stone, tileWidth*4, 0, null);*/

        canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
        canvas2.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
        world.render(canvas,canvas2);
        imageView.invalidate(0,0,width,height);
        imageView2.invalidate(0,0,width,height);

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
    public Input getInput(){
        return input;
    }


}
