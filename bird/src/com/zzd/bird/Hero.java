package com.zzd.bird;

public interface Hero {

    void moveHandle();
    void moveHandleLeft();
    void moveHandleRight();
    void moveHandleDown();

    boolean hit(FlyingObject w);
    boolean isGround();
}
