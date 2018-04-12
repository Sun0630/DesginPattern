package com.sx.builder;

/**
 * 抽象的Builder类
 */
public abstract class Builder {
    public abstract void buildBoard(String board);

    public abstract void buildDisplay(String display);

    public abstract void buildOS();

    public abstract Computer creat();
}
