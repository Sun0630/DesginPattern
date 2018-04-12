package com.sx.builder;

public class Main {
    public static void main(String[] args) {
        Builder builder = new MacBookBuilder();
        Director director = new Director(builder);
        director.construct("Inter mainBoard", "Retina display");

        System.out.println("Computer Info:" + builder.creat().toString());
    }
}
