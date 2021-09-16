package com.zukxu.design.strategy;

/**
 * @author xupu
 * @Description 嘎嘎叫
 * @Date 2021-09-13 14:35
 */
public class Quack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
}
