package com.zzd.bird;

import java.awt.*;

public class Bird extends FlyingObject implements Hero{

    public Bird(){
        width = Config.BIRD_WIDTH;
        height = Config.BIRD_HEIGHT;
    }

    public Bird(int x,int y){
        this.x = x;
        this.y = y;
        width = Config.BIRD_WIDTH;
        height = Config.BIRD_HEIGHT;
    }

    int beeIndex = 0;

    @Override
    public void paintSelf(Graphics g) {
        if(status==Config.FLYING_STATUS_LIVING || status==Config.FLYING_STATUS_BOATING ){
            g.drawImage(bird_imgs[beeIndex], x, y,width,height, null);
            beeIndex++;
            if(beeIndex>=5){
                beeIndex=0;
            }
        }else if(status==Config.FLYING_STATUS_HITTING){
            g.drawImage(bird_imgs[5], x, y,width,height, null);
        }else{
            g.drawImage(bird_imgs[6], x, y,width,height, null);
        }
    }

    /*封装鸟自动移动的逻辑，外面(移动引擎)想让鸟移动，直接调用本方法即可*/
    @Override
    public void moveAuto() {
        if(status==Config.FLYING_STATUS_LIVING){
            this.y+=Config.BIRD_STEP_AUTO;
        }else if(status==Config.FLYING_STATUS_HITTING){
            this.y+=Config.BIRD_STEP_HIT;
        }else if(status==Config.FLYING_STATUS_DEAD){
            this.y=Config.BIRD_POSTION_DEAD_Y;
            this.x=Config.BIRD_POSTION_DEAD_X;
        }
    }

    /*封装鸟被键盘控制的逻辑*/
    @Override
    public void moveHandle() {
        if(status==Config.FLYING_STATUS_LIVING){
            this.y-=Config.BIRD_STEP_HANDLE;
        }
    }

    /*封装鸟被键盘控制的逻辑*/
    @Override
    public void moveHandleLeft() {
        if(status==Config.FLYING_STATUS_LIVING){
            this.x-=Config.BIRD_STEP_HANDLE;
        }
    }

    @Override
    public void moveHandleRight() {
        if(status==Config.FLYING_STATUS_LIVING){
            this.x+=Config.BIRD_STEP_HANDLE;
        }
    }

    @Override
    public void moveHandleDown() {
        if(status==Config.FLYING_STATUS_LIVING){
            this.y +=Config.BIRD_STEP_HANDLE;
        }
    }

    @Override
    public boolean hit(FlyingObject w) {
        if(this.x<w.x+w.width && this.x+this.width>w.x){
            if(this.y+this.height>w.y && this.y<w.y+w.height){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isGround() {
        if(status==Config.FLYING_STATUS_BOATING){
            return false;
        }
        if(this.y+40>=Config.FRAME_HEIGHT){
            return true;
        }
        return false;
    }
}
