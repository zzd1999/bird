package com.zzd.bird;

public class Config {
    /**窗口宽度*/
    public static final int FRAME_WIDTH=1200;
    /**窗口高度*/
    public static final int FRAME_HEIGHT=500;
    /**鸟出生的初始化位置X*/
    public static final int BIRD_POSTION_INIT_X=600;
    /**鸟出生的初始化位置Y*/
    public static final int BIRD_POSTION_INIT_Y=200;
    /**鸟出生的死亡时位置X*/
    public static final int BIRD_POSTION_DEAD_X=350;
    /**鸟出生的死亡时位置Y*/
    public static final int BIRD_POSTION_DEAD_Y=250;
    /**飞行物的状态 活着*/
    public static final int FLYING_STATUS_LIVING=0;
    /**飞行物的状态  撞墙(只有鸟才用这个)*/
    public static final int FLYING_STATUS_HITTING=1;
    /**飞行物的状态 死亡*/
    public static final int FLYING_STATUS_DEAD=2;
    /**鸟的宽度*/
    public static final int BIRD_WIDTH=80;
    /**鸟的高度*/
    public static final int BIRD_HEIGHT=80;
    /**鸟自动向下移动步进*/
    public static final int BIRD_STEP_AUTO=5;
    /**鸟撞墙后向下移动步进*/
    public static final int BIRD_STEP_HIT=25;
    /**鸟手动控制移动步进*/
    public static final int BIRD_STEP_HANDLE=80;
    /**障碍物方向*/
    public static final int WALL_UP=0;
    /**障碍物方向*/
    public static final int WALL_DOWN=1;
    /**障碍物步进*/
    public static final int WALL_SPEED=4;
    /**障碍物间距*/
    public static final int WALL_SPACE=150;
    /**背景步进*/
    public static final int BACKGROUND_SPEED=2;
    /**游戏状态*/
    public static final int GAME_STATUS_RUNING=0;
    /**游戏状态*/
    public static final int GAME_STATUS_OVER=1;
    /**游戏线程睡眠时间*/
    public static final int GAME_MOVE_SLEEP=60;
    /**游戏的皮肤*/
    public static final String GAME_SKIN_NAME="bee";
    /**食物图片的大小*/
    public static final int FOOD_SIZE=50;
    /**具有攻击型敌人势力范围*/
    public static final int ENEMY_SPACE = 5;
    public final static int HELPER_LIFE=10;
    /**船图片的大小*/
    public static final int BOAT_SIZE=80;
    /**飞行物做船的状态*/
    public static final int FLYING_STATUS_BOATING = 3;
    /**添加船的生命*/
    public static final int BOAT_LIFE = 3;
}