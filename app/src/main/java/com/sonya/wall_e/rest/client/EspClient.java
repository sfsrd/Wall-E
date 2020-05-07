package com.sonya.wall_e.rest.client;

import android.view.View;

import com.sonya.wall_e.async.task.MoveTask;

public class EspClient  {

    public void move(View view, char direction) {

        new MoveTask().execute(view, direction);
    }
}
