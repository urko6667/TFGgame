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



private double[][] movecontrol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);



        //View decorView = getWindow().getDecorVie w();
// Hide the status bar.
       // int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        //decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        //ActionBar actionBar = getActionBar();
       // actionBar.hide();
        movecontrol = new double[4][4];
        movecontrol[0][0]=34.5;
        movecontrol[0][1]=0.5;
        movecontrol[0][2]=65;
        movecontrol[0][3]=29.8;

        movecontrol[1][0]=69.5;
        movecontrol[1][1]=35.5;
        movecontrol[1][2]=98.2;
        movecontrol[1][3]=65;

        movecontrol[2][0]=36.2;
        movecontrol[2][1]=69.6;
        movecontrol[2][2]=65;
        movecontrol[2][3]=98.2;

        movecontrol[3][0]=1.7;
        movecontrol[3][1]=34.5;
        movecontrol[3][2]=30.5;
        movecontrol[3][3]=63.7;

        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);

        width = size. x;
        height = size. y;
        Log.d("mytaga","tamaño X" + width);
        Log.d("mytaga","tamaño Y" + height);
        tileWidth=width/16;
        tileHeight=height/9;
        Log.d("mytag","oncreate");
        init();
    }
    public void init(){
        Log.d("mytag","init");

        handler= new Handler(this);
        gameCamera= new GameCamera(handler,0,0);
        input= new Input();


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


        control.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        float Tx=event.getX();
                        float Ty=event.getY();
                        int Cmx=control.getWidth();
                        int Cmy=control.getHeight();
                        if(Tx>(Cmx*(movecontrol[0][0]/100))&&Tx<(Cmx*(movecontrol[0][2]/100))&&Ty>(Cmy*(movecontrol[0][1]/100))&&Ty<(Cmy*(movecontrol[0][3]/100))){
                            getInput().setUp();}
                        if(Tx>(Cmx*(movecontrol[1][0]/100))&&Tx<(Cmx*(movecontrol[1][2]/100))&&Ty>(Cmy*(movecontrol[1][1]/100))&&Ty<(Cmy*(movecontrol[1][3]/100))){
                            getInput().setRight();}
                        if(Tx>(Cmx*(movecontrol[2][0]/100))&&Tx<(Cmx*(movecontrol[2][2]/100))&&Ty>(Cmy*(movecontrol[2][1]/100))&&Ty<(Cmy*(movecontrol[2][3]/100))){
                            getInput().setDown();}
                        if(Tx>(Cmx*(movecontrol[3][0]/100))&&Tx<(Cmx*(movecontrol[3][2]/100))&&Ty>(Cmy*(movecontrol[3][1]/100))&&Ty<(Cmy*(movecontrol[3][3]/100))){
                            getInput().setLeft();}
                        break;

                    }
                    case MotionEvent.ACTION_UP: {
                        handler.getInput().setFalse();
                        break;
                    }
                }

                return true;
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

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.invalidate(0,0,width,height);
                imageView2.invalidate(0,0,width,height);
            }
        });


    }
    public synchronized void start(){
        Log.d("mytag","start");
        world = new World(handler, world1, this, tileWidth, tileHeight);
        handler.setWorld(world);
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
