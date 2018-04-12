package com.sx.builder;

/**
 * 抽象的产品类
 */
public abstract class Computer {
    protected String board;//主板
    protected String display;//显示器
    protected String os;//操作系统

    public Computer() {

    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    /**
     * 设置操作系统
     */
    public abstract void setOs();

    @Override
    public String toString() {
        return "Computer{" +
                "board='" + board + '\'' +
                ", display='" + display + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
