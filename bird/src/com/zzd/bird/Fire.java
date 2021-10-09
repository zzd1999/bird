package com.zzd.bird;

import java.awt.*;
import java.util.Random;

public class Fire extends FlyingObject implements Enemy{
    /**
     * 需要一个状态属性，代表活着还是被撞了，
     * 把鸟的状态status扩大给父类，这样所有的FlyingObject就都有了
     * @param bird
     */

    Bird bird;

    public Fire(){
        //给x y  width 和height赋值
        width=Config.FOOD_SIZE;
        height=Config.FOOD_SIZE;
        Random ran=new Random();

        y=ran.nextInt(Config.FRAME_HEIGHT-height);
        x=0;
    }

    @Override
    public void catchBird(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(food_fire,x,y,width,height,null);
    }

    @Override
    public void moveAuto() {

        if(bird != null && bird.y > this.y && bird.y < this.y + this.height){
            this.x += Config.WALL_SPEED*5;
        }else {
            this.x += Config.WALL_SPEED*2;
        }
    }


}
