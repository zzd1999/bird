package com.zzd.bird;

import java.awt.*;

public class Dollar extends FlyingObject{

    public Dollar(int x,int y){
        this.x = x;
        this.y = y;
    }

    int index = 0;

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(dollar_imgs[index],x,y,null);
        index++;
        if(index >= dollar_imgs.length){
            index = 0;
        }
    }

    @Override
    public void moveAuto() {
        this.y -= 20;
        this.x += 20;
    }
}
