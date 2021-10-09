package com.zzd.bird;

import java.awt.*;

public class BackGround extends FlyingObject{

    int x2=-Config.FRAME_WIDTH;//第二图片初始位置

    public BackGround() {
        width=Config.FRAME_WIDTH;
        height=Config.FRAME_HEIGHT;
    }

    public void paintSelf(Graphics g){
        g.drawImage(back_img, x, y,width,height, null);
        g.drawImage(back_img2, x2, y, width,height,null);
    }

    public void moveAuto(){
        if(x>Config.FRAME_WIDTH){x=0;}
        if(x2>0){x2=-Config.FRAME_WIDTH;}
        this.x++;
        this.x2++;
    }
}
