package com.zzd.bird;

/**
 * 给GoldBox 和Money等 食物建立干爹，让这两个类
 * 都“实现”这个干爹，这样目的其实是为了标识对外宣称，
 * 这两个类不仅是飞行物，还是奖励
 * */
public interface Award {
    void moveAuto(int x,int y);
}
