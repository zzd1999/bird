package com.zzd.bird;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        MyPanel panel=new MyPanel();
        frame.setSize(Config.FRAME_WIDTH, Config.FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        //启动移动引擎
        panel.moveAction();
        //启动键盘监听引擎
        panel.keyAction();
        //启动生成障碍物引擎
        panel.createWallAction();
        //启动生船引擎
        panel.createBoatAction();
        frame.setVisible(true);
    }
}
