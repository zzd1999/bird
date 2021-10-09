package com.zzd.bird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class MyPanel extends JPanel {

    int status = Config.GAME_STATUS_RUNING;//进行中  1为游戏结束

    BackGround back = new BackGround();
    FlyingObject[] walls = new FlyingObject[0];
    Bird bird = new Bird(Config.BIRD_POSTION_INIT_X,Config.BIRD_POSTION_INIT_Y);

    Dollar[] dollars = new Dollar[0];


    int score;//游戏的分数
    long time1;//游戏开始的时间
    long time2;//游戏结束的时间

    Boat boat;

    public void paint(Graphics g) {
        super.paint(g);//调用父类的paint的方法
        back.paintSelf(g);
        for(FlyingObject wall:walls){
            wall.paintSelf(g);
        }

        bird.paintSelf(g);

        for(Dollar d:dollars){//移动墙
            d.paintSelf(g);
        }

        if(boat!=null){
            boat.paintSelf(g);
        }
    }

    /*移动引擎*/
    public void moveAction(){
        new Thread(){
            public void run() {
                time1=System.currentTimeMillis();
                FlyingObject.music_back.loop();
                for(;;){
                    if(status==Config.GAME_STATUS_RUNING){
                        checkBoatHit();//这里设置了死障碍物，但是没有过滤掉
                        checkWallOut();//检测墙越界，里面有过滤死障碍物代码
                        checkDollarOut();//检测金币越界
                        if(checkBirdHit()){//检测鸟撞墙
                            bird.status=Config.FLYING_STATUS_HITTING;
//							bird.status=0;//共测试
                            FlyingObject.music_hit.play();
                        }
                        if(bird.isGround()){////检测鸟撞地
                            bird.status=Config.FLYING_STATUS_DEAD;
                            status=Config.GAME_STATUS_OVER;//游戏状态为结束
                            FlyingObject.music_death.play();
                        }
                        back.moveAuto();//移动背景
                        bird.moveAuto();//移动鸟
                        for(FlyingObject wall:walls){//移动墙
                            wall.moveAuto();
                        }
                        for(Dollar d:dollars){//移动金币
                            d.moveAuto();
                        }
                        if(boat!=null){//船移动
                            boat.moveAuto();
                        }
                        repaint();//重画
                        sleepThread();//睡觉
                    }else{
                        FlyingObject.music_back.stop();
                        time2=System.currentTimeMillis();//记录结束时间
                        score +=(time2-time1)/1000;// 走几秒得几分
                        int result=JOptionPane.showConfirmDialog(null, "您得了"+score+"分，重新开始吗","提示",JOptionPane.YES_NO_OPTION);
                        if(result==JOptionPane.YES_OPTION){
                            //初始化所有数据
                            back=new BackGround();
                            walls=new FlyingObject[0];
                            bird=new Bird(Config.BIRD_POSTION_INIT_X,Config.BIRD_POSTION_INIT_Y);
                            status=Config.GAME_STATUS_RUNING;
                            FlyingObject.music_back.loop();
                            score=0;//清空分数
                            time1=System.currentTimeMillis();//重新计时
                        }else{
                            System.exit(0);
                        }
                    }
                }
            };
        }.start();
    }

    /*键盘监听引擎*/
    public void keyAction(){
        KeyAdapter key=new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    bird.moveHandle();
                    if(boat!=null){
                        boat.moveHandle();
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    bird.moveHandleDown();
                    if(boat!=null){
                        boat.moveHandleDown();
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    bird.moveHandleLeft();
                    if(boat!=null){
                        boat.moveHandleLeft();
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    bird.moveHandleRight();
                    if(boat!=null){
                        boat.moveHandleRight();
                    }
                }
                FlyingObject.music_keybord.play();
                repaint();
            }
        };

        this.setFocusable(true);
        this.addKeyListener(key);
    }


    /**创建障碍物引擎*/
    public void createWallAction(){
        new Thread(){
            public void run() {
                for(;;){
                    //控制外循环的变量
                    boolean isOk=true;
                    FlyingObject  w=null;
                    Random ran=new Random();
                    int r=ran.nextInt(5);//一共五种 stone fire box money wall
                    switch(r){
                        case 0:w=new Stone();break;
                        case 1:w=new Fire();break;
                        case 2:w=new GoldBox();break;
                        case 3:w=new Money();break;
                        case 4:w=new Wall();break;
                    }

                    for(FlyingObject ws:walls){//遍历已经存在的wall们
                        //判断生成的w是否在已经生成的wall的附近
                        if(ws.isClosed(w)){
                            isOk=false;
                            break;//只要发现有靠近就不必再寻找了
                        }
                    }
                    //
                    if(!isOk){
                        continue;//结束本次循环，进行下次循环
                    }

                    walls=Arrays.copyOf(walls, walls.length+1);
                    walls[walls.length-1]=w;
                }
            };
        }.start();
    }

    /**创建船的引擎*/
    public void createBoatAction(){
        new Thread(){
            public void run() {
                for(;;){
                    if(boat==null){
                        boat=new Boat();
                        try {
                            Thread.sleep(15000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }.start();
    }




    /**将睡眠的代码封装到一个方法内部，对移动引擎代码部分来说看着精简*/
    private void sleepThread(){
        try {
            Thread.sleep(Config.GAME_MOVE_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*判断障碍物越界逻辑，在移动引擎当中调用**/
    private void checkWallOut(){
        //创建一个数组，用来保存没越界的墙
        FlyingObject[] tmp=new FlyingObject[0];
        for(FlyingObject wall:walls){
            //过滤掉被小鸟撞到的奖品
            if(!wall.isOut() && wall.status!=Config.FLYING_STATUS_DEAD){
                tmp=Arrays.copyOf(tmp, tmp.length+1);
                tmp[tmp.length-1]=wall;
            }
        }
        walls=tmp;//把活着的给walls
    }


    private void checkDollarOut(){
        //创建一个数组，用来保存没越界的
        Dollar[] tmp=new Dollar[0];
        for(Dollar d:dollars){
            if(!d.isOut()){
                tmp=Arrays.copyOf(tmp, tmp.length+1);
                tmp[tmp.length-1]=d;
            }
        }
        dollars=tmp;//把活着的留下
    }


    /*判断墙撞了小鸟，在移动引擎当中调用**/
    private boolean checkBirdHit(){

        if(boat!=null && bird.hit(boat)&& bird.status==Config.FLYING_STATUS_LIVING){
            boat.catchBird(bird);
        }

        //检测如果是敌人，自动飘向鸟，先抓鸟
        for(FlyingObject w:walls){
            if(w instanceof Enemy){
                ((Enemy)w).catchBird(bird);
            }
        }


        for(FlyingObject w:walls){
            if(bird.hit(w)){
                if(w instanceof Award){
                    //生成金币对象，
                    dollars=Arrays.copyOf(dollars,dollars.length+1);
                    dollars[dollars.length-1]=new Dollar(w.x,w.y);
                    FlyingObject.music_dollar.play();
                    //算钱
                    score += 1000;
                    //改变当前障碍物的状态
                    w.status=Config.FLYING_STATUS_DEAD;

                }else{
                    return true;
                }
            }
        }
        return false;
    }

    private void checkBoatHit(){

        if(boat!=null && boat.bird!=null){
            //遍历所有的奖品自动流向鸟
            for(FlyingObject w:walls){
                if(w instanceof Award){
                    ((Award)w).moveAuto(bird.x,bird.y);
                }
            }
        }

        for(FlyingObject w:walls){
            //有船，船上有鸟，同时船撞了障碍才算
            if(boat!=null && boat.bird!=null && boat.hit(w)&&boat.hasLife()){
                System.out.println("船撞了障碍物");
                if(w instanceof Award){
                    w.status=Config.FLYING_STATUS_DEAD;
                    //算钱
                    Dollar d=new Dollar(w.x,w.y);
                    dollars= Arrays.copyOf(dollars, dollars.length+1);
                    dollars[dollars.length-1]=d;
                    score+=1000;
                    FlyingObject.music_dollar.play();
                }else{
                    //将障碍消失
                    w.status=Config.FLYING_STATUS_DEAD;
                    boat.minusLife();
                    if(!boat.hasLife()){
                        boat.outBird();//扔鸟逻辑
                        boat=null;
                    }
                }

            }
        }
    }

}
