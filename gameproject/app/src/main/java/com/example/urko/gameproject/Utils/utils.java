package com.example.urko.gameproject.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class utils extends Activity {

    public  String loadFileAsString(String path, Context context) {
        StringBuilder builder = new StringBuilder();
        try {
            //BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("worlds/world1.txt"), "UTF-8"));
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(path), "UTF-8"));
            String line;
            while((line= br.readLine())!=null)
                builder.append(line+"\n");
            br.close();

        }catch(IOException e) {
            e.printStackTrace();
        }

        return builder.toString();

    }
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        }catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
