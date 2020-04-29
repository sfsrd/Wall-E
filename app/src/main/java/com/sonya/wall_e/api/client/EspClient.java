package com.sonya.wall_e.api.client;

import android.view.View;

import com.sonya.wall_e.async.task.MoveTask;

public class EspClient  {

    public void move(View view, String direction) {
        new MoveTask().execute(view, direction);
    }
}
