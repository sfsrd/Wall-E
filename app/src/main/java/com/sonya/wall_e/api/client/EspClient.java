package com.sonya.wall_e.api.client;

import com.sonya.wall_e.api.request.MoveRequest;

public class EspClient  {

    public void move(String direction) {
        new MoveRequest().execute(direction);
    }
}
