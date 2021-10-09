package com.zzd.bird;

import java.awt.*;
import java.util.Random;

public class GoldBox extends FlyingObject implements Award{

    /**
     * 需要一个状态属性，代表活着还是被撞了
     * 把鸟的状态status扩大给父类，这样所有的FlyingObject就都有了
     */

    public GoldBox(){
        //给x y width和 height
        width = Config.FOOD_SIZE;
        height = Config.FOOD_SIZE;
        Random ran = new Random();

        y = ran.nextInt(Config.FRAME_HEIGHT - height);
        x = 0;
    }

    @Override
    public void moveAuto() {
        this.x += Config.WALL_SPEED * 2;
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(food_box, x, y, width, height, null);
    }

    @Override
    public void moveAuto(int x2,int y2) {
        if(this.x>x2){
            this.x-=Config.WALL_SPEED*5;
        }
        if(this.x<x2){
            this.x+=Config.WALL_SPEED*5;
        }
        if(this.y>y2){
            this.y-=Config.WALL_SPEED*5;
        }
        if(this.y<y2){
            this.y+=Config.WALL_SPEED*5;
        }
    }
}
