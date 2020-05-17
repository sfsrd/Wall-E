package com.sonya.wall_e.constants;

public enum Direction {

    FORWARD ('f'), BACKWARD('b'), LEFT('l'), RIGHT('r');

    private char direction;

    Direction(char direction) {

        this.direction = direction;
    }

    public char getDirection() {

        return direction;
    }
}