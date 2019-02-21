package com.example.urko.gameproject.gfx;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;

import com.example.urko.gameproject.R;

public class Assets {


    private static final int width = 96, height= 96;
    private static float rWidth, rHeight;
    public static Bitmap sheet1, player, dirt, grass, stone, dirt1, dirt2, dirt3, dirt4,water, water1,water2,water3,water4, pokeball;
    public static Bitmap[] player_down,masterSword_right,masterSword_down,goldSword_down,masterSword_left,goldSword_right,goldSword_left,ironSword_right,ironSword_down,ironSword_left,player_left,player_right,player_up, pokemon, rustSword_up, rustSword_left, rustSword_right, rustSword_down, ironSword_up, goldSword_up, masterSword_up;
    public static void init(Resources res,float vWidth,float vHeight) {
        //Uri path = Uri.parse("android.resource://com.example.urko.gameproject/" + R.mipmap.player);
        //String path2 = path.toString();
        rWidth=vWidth;
        rHeight=vHeight;
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage(res, R.drawable.player));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.loadImage(res,R.drawable.tileset));
        SpriteSheet sheetpok = new SpriteSheet(ImageLoader.loadImage(res,R.drawable.pokemon));
        SpriteSheet sheetball = new SpriteSheet(ImageLoader.loadImage(res,R.drawable.pokeball));
        SpriteSheet sheetsword = new SpriteSheet(ImageLoader.loadImage(res,R.drawable.sword));
        player_down = new Bitmap[4];
        player_left = new Bitmap[4];
        player_right = new Bitmap[4];
        player_up = new Bitmap[4];
        pokemon = new Bitmap[4];
        rustSword_up = new Bitmap[3];
        rustSword_left = new Bitmap[3];
        rustSword_right = new Bitmap[3];
        rustSword_down = new Bitmap[3];
        ironSword_up = new Bitmap[3];
        ironSword_left = new Bitmap[3];
        ironSword_right = new Bitmap[3];
        ironSword_down = new Bitmap[3];
        goldSword_up = new Bitmap[3];
        goldSword_left = new Bitmap[3];
        goldSword_right = new Bitmap[3];
        goldSword_down = new Bitmap[3];
        masterSword_up = new Bitmap[3];
        masterSword_left = new Bitmap[3];
        masterSword_right = new Bitmap[3];
        masterSword_down = new Bitmap[3];

        player_down[0] = sheet2.crop(0, 0, width, height + height/2);
        player_down[1] = sheet2.crop(width, 0, width, height + height/2);
        player_down[2] = sheet2.crop(width * 2, 0, width, height + height/2);
        player_down[3] = sheet2.crop(width * 3, 0, width, height + height/2);

        player_left[0] = sheet2.crop(0, (height + height/2), width, height + height/2);
        player_left[1] = sheet2.crop(width, (height + height/2), width, height + height/2);
        player_left[2] = sheet2.crop(width * 2, (height + height/2), width, height + height/2);
        player_left[3] = sheet2.crop(width * 3, (height + height/2), width, height + height/2);

        player_right[0] = sheet2.crop(0, (height + height/2) * 2, width, height + height/2);
        player_right[1] = sheet2.crop(width, (height + height/2) * 2, width, height + height/2);
        player_right[2] = sheet2.crop(width * 2, (height + height/2) * 2, width, height + height/2);
        player_right[3] = sheet2.crop(width * 3, (height + height/2) * 2, width, height + height/2);

        player_up[0] = sheet2.crop(0, (height + height/2) * 3, width, height + height/2);
        player_up[1] = sheet2.crop(width, (height + height/2) * 3, width, height + height/2);
        player_up[2] = sheet2.crop(width * 2, (height + height/2) * 3, width, height + height/2);
        player_up[3] = sheet2.crop(width * 3, (height + height/2) * 3, width, height + height/2);


        //pokeball = sheetball.crop(0, 0, width, height);
        pokemon[0] = sheetpok.crop(0, 0, width, height);
        pokemon[1] = sheetpok.crop(0, height, width, height);
        pokemon[2] = sheetpok.crop(0, height * 2, width, height);
        pokemon[3] = sheetpok.crop(0, height * 3, width, height);

        /*rustSword_up[0] = sheetsword.crop(width, 0, width, height);
        rustSword_up[1] = sheetsword.crop(width * 2, 0, width, height);
        rustSword_up[2] = sheetsword.crop(0, 0, width, height);

        rustSword_left[0] = sheetsword.crop(width * 6, width * 3, width, height);
        rustSword_left[1] = sheetsword.crop(width * 3, 0, width, height);
        rustSword_left[2] = sheetsword.crop(width * 5, width * 3, width, height);

        rustSword_right[0] = sheetsword.crop(0, 0, width, height);
        rustSword_right[1] = sheetsword.crop(width * 2, 0, width, height);
        rustSword_right[2] = sheetsword.crop(width, 0, width, height);

        rustSword_down[0] = sheetsword.crop(width * 5, width * 3, width, height);
        rustSword_down[1] = sheetsword.crop(width * 3, 0, width, height);
        rustSword_down[2] = sheetsword.crop(width * 6, width * 3, width, height);

        ironSword_up[0] = sheetsword.crop(0, 0, width, height);
        ironSword_up[1] = sheetsword.crop(0, 0, width, height);
        ironSword_up[2] = sheetsword.crop(0, 0, width, height);

        ironSword_left[0] = sheetsword.crop(0, 0, width, height);
        ironSword_left[1] = sheetsword.crop(0, 0, width, height);
        ironSword_left[2] = sheetsword.crop(0, 0, width, height);

        ironSword_right[0] = sheetsword.crop(0, 0, width, height);
        ironSword_right[1] = sheetsword.crop(0, 0, width, height);
        ironSword_right[2] = sheetsword.crop(0, 0, width, height);

        ironSword_down[0] = sheetsword.crop(0, 0, width, height);
        ironSword_down[1] = sheetsword.crop(0, 0, width, height);
        ironSword_down[2] = sheetsword.crop(0, 0, width, height);*/

        player = sheet2.crop(0, 0, width, height+height/2);

        grass = sheet3.crop(0, 0, width, height);
        dirt = sheet3.crop(width, 0, width, height);
        stone = sheet3.crop(width * 2, 0, width, height);
		/*dirt1 = sheet.crop(width*2, height, width, height);
		dirt2 = sheet.crop(width*3, height, width, height);
		dirt3 = sheet.crop(width*2, height*2, width, height);
		dirt4 = sheet.crop(width*3, height*2, width, height);
		water = sheet.crop(width*4, height*3, width, height);
		water1 = sheet.crop(width*4, height*4, width, height);
		water2 = sheet.crop(width*5, height*4, width, height);
		water3 = sheet.crop(width*4, height*5, width, height);
		water4 = sheet.crop(width*5, height*5, width, height);*/

    }
}