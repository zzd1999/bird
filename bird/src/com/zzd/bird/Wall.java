package com.zzd.bird;

import java.awt.*;
import java.util.Random;

public class Wall extends FlyingObject{

    int dir;//墙的位置方向 有两个值 0代表在上面 1代表在下面

    public Wall() {
        Random ran=new Random();
        height=ran.nextInt(150)+Config.FRAME_HEIGHT/4;
        width=Config.FOOD_SIZE;
        x=0;
        dir=ran.nextInt(2);
        if(dir==0){
            y=0;
        }else{
            y=Config.FRAME_HEIGHT-height;
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(wall_img, x, y, width,height,null);
    }

    @Override
    public void moveAuto() {
        this.x+=Config.WALL_SPEED*2;
    }
}
