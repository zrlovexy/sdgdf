package com.example.geek.bean;

/**
 * @author xts
 *         Created by asus on 2019/4/22.
 */

public class Car {
    public String name;
    public String header;
    public Car(String name, String header) {
        this.name = name;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", header='" + header + '\'' +
                '}';
    }
}
