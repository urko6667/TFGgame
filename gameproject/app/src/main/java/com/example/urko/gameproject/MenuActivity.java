package com.example.urko.gameproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;


public class MenuActivity extends AppCompatActivity {
    MediaPlayer mp;
    ImageButton btnsalir;
    ImageButton btnnewgame;
    ImageButton btncontinuegame;
    ImageButton btnsettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btnsalir = (ImageButton)findViewById(R.id.exit);
        btnnewgame = (ImageButton)findViewById(R.id.newgame);
        btncontinuegame = (ImageButton)findViewById(R.id.continuegame);
        btnsettings = (ImageButton)findViewById(R.id.settings);

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                System.exit(0);
            }
        });
        btnnewgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent empezarjuego = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(empezarjuego);
            }
        });
//Ocultar barra de navegacion y estado al crear y al pasar un rato despues de haber deslizado
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);



        mp = MediaPlayer.create(this, R.raw.menu);
        mp.start();
        mp.setLooping(true);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        mp.pause();
    }
    @Override
    public void onPause(){
        super.onPause();
        mp.pause();
    }

    @Override
    public void onStart(){
        super.onStart();
        if(!mp.isPlaying()){mp.start();mp.setLooping(true);}
    }

}
