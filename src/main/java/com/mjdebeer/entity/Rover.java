package com.mjdebeer.entity;

public class Rover {
    public int xAxisPosition;
    public int yAxisPosition;
    public Orientation currentOrientation;
    AreaMap grid;

    public Rover(AreaMap areaMap) {
        grid = areaMap;
        xAxisPosition = 0;
        yAxisPosition = 0;
        currentOrientation = Orientation.N;
    }

    public enum Orientation {
        N,
        E,
        S,
        W
    }

    public void turn(String direction) {
        if (direction.equals("L")) {
            if (currentOrientation.ordinal() > 0) {
                currentOrientation = Orientation.values()[currentOrientation.ordinal() - 1];
            } else if (currentOrientation.ordinal() == 0) {
                currentOrientation = Orientation.W;
            }
        } else if (direction.equals("R")) {
            if (currentOrientation.ordinal() < 3) {
                currentOrientation = Orientation.values()[currentOrientation.ordinal() + 1];
            } else if (currentOrientation.ordinal() == 3) {
                currentOrientation = Orientation.N;
            }
        };
    }

    public void moveNorth() {
        if (yAxisPosition < grid.ySize) {
            yAxisPosition ++;
        } 
    }

    public void moveEast() {
        if (xAxisPosition < grid.xSize) {
            xAxisPosition ++;
        } 
    }

    public void moveSouth() {
        if (yAxisPosition > 0) {
            yAxisPosition --;
        } 
    }

    public void moveWest() {
        if (xAxisPosition > 0) {
            xAxisPosition --;
        } 
    }
}
