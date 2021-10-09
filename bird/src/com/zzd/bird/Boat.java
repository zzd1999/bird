package com.zzd.bird;

import java.awt.*;

public class Boat extends FlyingObject implements Helper,Hero {

    int life = Config.BOAT_LIFE;

    Bird bird;

    public Boat(){
        width = Config.BOAT_SIZE;
        height = Config.BOAT_SIZE/4;
        x = Config.FRAME_WIDTH;
        y = Config.BIRD_POSTION_INIT_Y;
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(boat_img,x,y,width,height,null);
    }

    @Override
    public void moveAuto() {
        if(bird==null){
            this.x-=Config.BACKGROUND_SPEED*2;
        }else{
            this.x=bird.x;//鸟的自动移动x本身就不动，所以这里也不会动
        }
    }

    @Override
    public void catchBird(Bird bird) {
        this.bird=bird;
        this.bird.status=Config.FLYING_STATUS_BOATING;
        //校准小鸟和船的位置
        this.x=bird.x;
        this.y=bird.y+bird.height;
    }

    @Override
    public void moveHandle() {
        if(bird!=null){//有鸟才能手动控制
            this.y-=Config.BIRD_STEP_HANDLE;
            bird.y-=Config.BIRD_STEP_HANDLE;//如果鸟状态为坐船就不受控制了，此处手动调用
        }
    }

    @Override
    public void moveHandleLeft() {
        if(bird!=null){//有鸟才能手动控制
            this.x-=Config.BIRD_STEP_HANDLE;
            bird.x-=Config.BIRD_STEP_HANDLE;//如果鸟状态为坐船就不受控制了，此处手动调用
        }
    }

    @Override
    public void moveHandleRight() {
        if(bird!=null){//有鸟才能手动控制
            this.x+=Config.BIRD_STEP_HANDLE;
            bird.x+=Config.BIRD_STEP_HANDLE;//如果鸟状态为坐船就不受控制了，此处手动调用
        }
    }

    @Override
    public void moveHandleDown() {
        if(bird!=null){//有鸟才能手动控制
            this.y+=Config.BIRD_STEP_HANDLE;
            bird.y+=Config.BIRD_STEP_HANDLE;//如果鸟状态为坐船就不受控制了，此处手动调用
        }
    }

    @Override
    public boolean hit(FlyingObject w) {
        if(this.x<w.x+w.width && this.x+this.width>w.x){
            if(this.y+this.height>w.y && this.y<w.y+w.height){
                return true;
            }
        }
        if(bird!=null){
            return bird.hit(w);
        }
        return false;
    }

    @Override
    public boolean isGround() {
        if(this.y+this.height>=Config.FRAME_HEIGHT){
            return true;
        }
        return false;
    }

    public void minusLife(){
        life--;
        System.out.println("减了一命还剩"+life);
    }

    public boolean hasLife(){
        if(life <= 0){
            return false;
        }
        return true;
    }

    public void outBird(){
        if(bird != null){
            bird.status = Config.FLYING_STATUS_LIVING;
            bird=null;
        }
    }
}
