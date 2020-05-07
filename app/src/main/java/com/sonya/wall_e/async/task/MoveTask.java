package com.sonya.wall_e.async.task;

import android.os.AsyncTask;
import android.view.View;

import com.sonya.wall_e.rest.connection.SocketConnection;

import java.io.DataOutputStream;
import java.io.IOException;

public class MoveTask extends AsyncTask<Object, Object, Void> {

    @Override
    protected Void doInBackground(final Object... objects) {

        final View view = (View) objects[0];

        final char direction = (char) objects[1];

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                try {
                    DataOutputStream dataOutputStream = SocketConnection.getOutputStream();

                    while (view.isPressed()) {

                        dataOutputStream.write(direction);
                        dataOutputStream.flush();

                        Thread.sleep(50);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        runnable.run();

        return null;
    }
}

