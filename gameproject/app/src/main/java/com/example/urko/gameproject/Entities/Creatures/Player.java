package com.example.urko.gameproject.Entities.Creatures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.urko.gameproject.Entities.Entity;
import com.example.urko.gameproject.Entities.statics.sword;
import com.example.urko.gameproject.Handler;
import com.example.urko.gameproject.gfx.Animation;
import com.example.urko.gameproject.gfx.Assets;

public class Player extends Creature{


    private Animation animDown, animLeft, animRight, animUp, lastAnimation;
    private static int width = 32, height = 48;
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    //private Sword sword;
    private int orientation=1;
    private boolean movement=true;
    private int tileWidth, tileHeight;
    public static int marginTop, marginLeft, marginRight, marginBottom;
    private Rect ar, cb;
    private Paint paint;
    public Player(Handler handler, float x, float y, int tileWidth, int tileHeight) {
        super(handler, x, y, tileWidth, tileHeight, tileHeight/10, tileWidth/10, tileHeight/10, tileWidth/10);
        /*bounds.x = 5;
        bounds.y = 24;
        bounds.width = 22;
        bounds.height = 18;*/
        marginBottom=tileHeight/10;
        marginLeft=tileWidth/10;
        marginRight=tileWidth/10;
        marginTop=tileHeight/10;
        health=1;
        speed=tileWidth/5;
        this.tileHeight=tileHeight;
        this.tileWidth=tileWidth;
        animDown = new Animation(200, Assets.player_down);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);
        animUp = new Animation(200, Assets.player_up);
        lastAnimation = animDown;
        paint = new Paint();
        this.paint.setColor(Color.RED);

    }

    @Override
    public void tick() {
        animDown.tick();
        animLeft.tick();
        animRight.tick();
        animUp.tick();
        Log.d("mytaga","entra player tick");

        getInput();
        //if(movement)
            move();
        bounds.set((int)x+marginLeft,(int)y+marginTop,(int)x+tileWidth-marginRight,(int)y+tileHeight-marginBottom);
        setBounds(bounds);
        handler.getGameCamera().centerOnEntity(this);

        checkAttacks();
    }

    public void checkAttacks() {
        if(!handler.getInput().attack)
            return;
        cb= new Rect();
        cb=getCollisionBounds(0,0);
        Rect ar = new Rect();
        int arSize = 32;
       /* Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 32;
        ar.width = arSize;
        ar.height = arSize;*/

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;
        Log.d("ataque","entra pre ataque");
        if(handler.getInput().attackDir==0){
            ar = new Rect((int)x,(int)y-height/3,(int)x+width,(int)y+height/3);
            Log.d("ataque","entra pre ataque == 0");
           /* ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;*/
            //ar.x-=handler.getGameCamera().getxOffset();
            //ar.y-=handler.getGameCamera().getyOffset();
            attackAnim(x,y,1);
        }
        if(handler.getInput().attackDir==1){
            ar = new Rect((int)x+width,(int)y+height/3,(int)x+width*2,(int)y+height/3*2);
            Log.d("ataque","entra pre ataque == 1");
           /* ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;*/
            //ar.x-=handler.getGameCamera().getxOffset();
            //ar.y-=handler.getGameCamera().getyOffset();
            attackAnim(x+width,y+height/3,4);
        }
        if(handler.getInput().attackDir==2){
            ar = new Rect((int)x,(int)y+height,(int)x+width,(int)y+height/3*2);
            Log.d("ataque","entra pre ataque == 2");
           /* ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;*/
            //ar.x-=handler.getGameCamera().getxOffset();
            //ar.y-=handler.getGameCamera().getyOffset();
            attackAnim(x,y+height,2);
        }
        if(handler.getInput().attackDir==3){
            ar = new Rect((int)x-width,(int)y+height/3,(int)x,(int)y+height/3*2);
            Log.d("ataque","entra pre ataque == 3");
           /* ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;*/
            //ar.x-=handler.getGameCamera().getxOffset();
            //ar.y-=handler.getGameCamera().getyOffset();
            attackAnim(x-width,y+height/3,3);
        }else{
            attackTimer = 0;
            return;
        }
        attackTimer = 0;
        /*if(handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
            //ar.x-=handler.getGameCamera().getxOffset();
            //ar.y-=handler.getGameCamera().getyOffset();
            attackAnim(ar.x-handler.getGameCamera().getxOffset(),ar.y-handler.getGameCamera().getyOffset(),1);
        }else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
            //ar.x-=handler.getGameCamera().getxOffset();
            //ar.y-=handler.getGameCamera().getyOffset();
            attackAnim(ar.x-handler.getGameCamera().getxOffset(),ar.y-handler.getGameCamera().getyOffset(),2);
        }else if(handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            //ar.x-=handler.getGameCamera().getxOffset();
            //ar.y-=handler.getGameCamera().getyOffset();
            attackAnim(ar.x-handler.getGameCamera().getxOffset(),ar.y-handler.getGameCamera().getyOffset(),3);
        }else if(handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            //ar.x-=handler.getGameCamera().getxOffset();
            //ar.y-=handler.getGameCamera().getyOffset();
            attackAnim(ar.x-handler.getGameCamera().getxOffset(),ar.y-handler.getGameCamera().getyOffset(),4);
        }else{
            return;

        }
        attackTimer = 0;
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }*/

    }
    public void attackAnim(float x, float y,int ori){
        movement=false;
        handler.getWorld().getEntityManager().addEntity(new sword(handler,(int)x ,(int)y,ori,tileWidth,tileHeight));

    }

    public void movem() {
        movement=true;
    }
    private void getInput() {
        Log.d("mytag","entra getInput");

        xMove = 0;
        yMove = 0;
        if (handler.getInput().up) {
            yMove = -speed;
            Log.d("mytag","movement: arriba");
        }
        if (handler.getInput().down) {
            yMove = speed;
            Log.d("mytag","movement: abajo");
        }
        if (handler.getInput().left) {
            xMove = -speed;
            Log.d("mytag","movement: izquierda");
        }
        if (handler.getInput().right) {
            xMove = speed;
            Log.d("mytag","movement: derecha");
        }

		/*if (handler.getMouseManager().isLeftPressed()) {
			System.out.println("pulsa");

			attackTimer += System.currentTimeMillis() - lastAttackTimer;
			lastAttackTimer = System.currentTimeMillis();
			if (attackTimer < attackCooldown)
				return;
			Rectangle cb = getCollisionBounds(0, 0);
			Rectangle ar = new Rectangle();
			int arSize = 20;
			ar.width = arSize;
			ar.height = arSize;
			int cpx=cb.x+cb.width/2;
			int cpy=cb.y+cb.height/2;
			if(cpx<handler.getMouseManager().getMouseX()&&cpy>handler.getMouseManager().getMouseY()) {
				if(handler.getMouseManager().getMouseX()-cpx<=cpy-handler.getMouseManager().getMouseY()) {
					ar.x = cb.x + cb.width / 2 - arSize / 2;
					ar.y = cb.y - arSize;
					System.out.println("1-1");
					orientation=1;
				}else {
					ar.x = cb.x + cb.width;
					ar.y = cb.y + cb.height / 2 - arSize / 2;
					System.out.println("1-2");
					orientation=2;
				}

			}else if(cpx<handler.getMouseManager().getMouseX()&&cpy<handler.getMouseManager().getMouseY()) {
				if(handler.getMouseManager().getMouseX()-cpx>=handler.getMouseManager().getMouseY()-cpy) {
					ar.x = cb.x + cb.width;
					ar.y = cb.y + cb.height / 2 - arSize / 2;
					System.out.println("2-1");
					orientation=2;
				}else {
					ar.x = cb.x + cb.width / 2 - arSize / 2;
					ar.y = cb.y + cb.height;
					orientation=3;
				}
			}else if(cpy<handler.getMouseManager().getMouseY()) {
				if(cpx-handler.getMouseManager().getMouseX()<=handler.getMouseManager().getMouseY()-cpy) {
					ar.x = cb.x + cb.width / 2 - arSize / 2;
					ar.y = cb.y + cb.height;
					System.out.println("3-1");
					orientation=3;
							}else {
					ar.x = cb.x - arSize;
					ar.y = cb.y + cb.height / 2 - arSize / 2;
					System.out.println("3-2");
					orientation=4;
				}
			}else if(cpy>handler.getMouseManager().getMouseY()) {
				if(cpx-handler.getMouseManager().getMouseX()>=cpy-handler.getMouseManager().getMouseY()) {
					ar.x = cb.x - arSize;
					ar.y = cb.y + cb.height / 2 - arSize / 2;
					System.out.println("4-1");
					orientation=4;
				}else {
					ar.x = cb.x + cb.width / 2 - arSize / 2;
					ar.y = cb.y - arSize;
					System.out.println("4-2");
					orientation=1;
				}
			}else {
				return;
			}
			handler.getWorld().getEntityManager().addEntity(new Sword(handler, (int) x-(int)handler.getGameCamera().getxOffset(), (int) y-(int)handler.getGameCamera().getyOffset(), orientation));
			*///handler.getWorld().getEntityManager().addEntity(new Pokeball(handler,300,300));


			/*if (cpy<handler.getMouseManager().getMouseY()&&((cpy-handler.getMouseManager().getMouseY())>=(cpx-handler.getMouseManager().getMouseX()))) {
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y - arSize;
			} else if (handler.getKeyManager().aDown) {
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y + cb.height;
			} else if (handler.getKeyManager().aLeft) {
				ar.x = cb.x - arSize;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			} else if (handler.getKeyManager().aRight) {
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			} else {
				return;
			}*/
			/*attackTimer = 0;
			for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
				if (e.equals(this))
					continue;
				if (e.getCollisionBounds(0, 0).intersects(ar)) {
					e.hurt(1);
					// return;
				}*/
        //}
        //}
        /*if (handler.getMouseManager().isRightPressed()) {

        }*/

    }

    @Override
    public void render(Canvas g) {
        g.drawBitmap(getCurrentAnimationFrame().createScaledBitmap(getCurrentAnimationFrame(), tileWidth, tileHeight+tileHeight/2, false), (int) (x-handler.getGame().getGameCamera().getxOffset()) , (int) ((y-tileHeight/2)-handler.getGame().getGameCamera().getyOffset()), null);
        //g.drawRect(bounds,paint);
       // g.drawBitmap(Assets.player, (int) (x - handler.getGameCamera().getxOffset()),
               // (int) (y - handler.getGameCamera().getyOffset()));

		/* g.setColor(Color.red);
		 g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int)
		 (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width,
		 bounds.height);*/
    }

    private Bitmap getCurrentAnimationFrame() {
        if (xMove < 0) {
            lastAnimation = animLeft;
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastAnimation = animRight;
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastAnimation = animUp;
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastAnimation = animDown;
            return animDown.getCurrentFrame();
        } else {
            return getLastFrame(lastAnimation);
        }
    }

    private Bitmap getLastFrame(Animation lastAnimation) {
        return lastAnimation.getLastFrame();
    }
}
