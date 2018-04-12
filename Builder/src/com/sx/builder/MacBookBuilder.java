package com.sx.builder;

/**
 * 具体的Builder类
 */
public class MacBookBuilder extends Builder {
    private Computer computer = new MacBook();

    @Override
    public void buildBoard(String board) {
        computer.setBoard(board);
    }

    @Override
    public void buildDisplay(String display) {
        computer.setDisplay(display);
    }

    @Override
    public void buildOS() {
        computer.setOs();
    }

    @Override
    public Computer creat() {
        return computer;
    }
}
