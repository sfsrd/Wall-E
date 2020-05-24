package com.sonya.wall_e.service;

import com.sonya.wall_e.constants.Direction;

public class DirectionParser {

    private static volatile Direction _direction = null;

    DirectionParser() {
    }

    public static Direction parseDirection(String direction) {

        switch (direction) {

            case "forward": {

                return Direction.FORWARD;
            }
            case "backward": {

                return Direction.BACKWARD;
            }
            case "left": {

                return Direction.LEFT;
            }
            case "right": {

                return Direction.RIGHT;
            }
            case "stop": {

                return null;
            }
            default: {

                return _direction;
            }
        }
    }

    public static Direction getGlobalDirection() {

        return _direction;
    }

    public static void setGlobalDirection(Direction direction) {

        _direction = direction;
    }
}
