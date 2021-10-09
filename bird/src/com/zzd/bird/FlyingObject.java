package com.zzd.bird;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;

/**
 * 所有的飞行物类的父类
 * 这里应该提前加载好所有子类应用的资源(图片资源，声音资源)
 */

public abstract class FlyingObject {

    static String skinPath="src/com/zzd/skin/"+Config.GAME_SKIN_NAME+"/";
    static Image back_img;
    static Image back_img2;
    static Image[] bird_imgs = new Image[7];
    static Image wall_img;

    static Image food_box;
    static Image food_fire;
    static Image food_money;
    static Image food_stone;


    static Image[] dollar_imgs=new Image[8];

    static Image boat_img;

    static AudioClip music_back;
    static AudioClip music_hit;
    static AudioClip music_death;
    static AudioClip music_keybord;
    static AudioClip music_dollar;

    int x;
    int y;
    int width;
    int height;

    int status = 0;

    static {
        try {
            back_img= ImageIO.read(new File(skinPath+"back.png"));
            back_img2=ImageIO.read(new File(skinPath+"back2.png"));
            for(int i=0;i<bird_imgs.length;i++){
                bird_imgs[i]=ImageIO.read(new File(skinPath+"bee"+i+".png"));
            }
            wall_img=ImageIO.read(new File(skinPath+"wall.jpg"));

            food_box=ImageIO.read(new File(skinPath+"food_box.png"));
            food_fire=ImageIO.read(new File(skinPath+"food_fire.png"));
            food_money=ImageIO.read(new File(skinPath+"food_money.png"));
            food_stone=ImageIO.read(new File(skinPath+"food_stone.png"));

            for(int i=0;i<dollar_imgs.length;i++){
                dollar_imgs[i]=ImageIO.read(new File(skinPath+"dollar"+i+".png"));
            }

            boat_img=ImageIO.read(new File(skinPath+"wall.jpg"));
            File file_back=new File(skinPath+"music_back.wav");
            music_back= Applet.newAudioClip(file_back.toURL());
            File file_hit=new File(skinPath+"music_hit.wav");
            music_hit=Applet.newAudioClip(file_hit.toURL());
            File file_death=new File(skinPath+"music_death.wav");
            music_death=Applet.newAudioClip(file_death.toURL());
            File file_keybord=new File(skinPath+"music_keybord.wav");
            music_keybord=Applet.newAudioClip(file_keybord.toURL());

            File file_dollar=new File(skinPath+"music_dollar.wav");
            music_dollar=Applet.newAudioClip(file_dollar.toURL());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不写方法体，让子类重写这个方法
     * 没有方法体的方法叫抽象方法
     */
    public abstract void paintSelf(Graphics g);
    public abstract void moveAuto();

    public boolean isClosed(FlyingObject w){
        if(w.x<this.x+this.width+Config.WALL_SPACE && this.x-Config.WALL_SPACE-w.width<w.x){
            return true;
        }

        return false;
    }

    public boolean isOut(){
        if(this.x > Config.FRAME_WIDTH){
            return true;
        }
        return false;
    }
}
