package com.sonya.wall_e.async.task;

import android.os.AsyncTask;
import android.view.View;

import com.sonya.wall_e.api.connection.SocketConnection;

import java.io.DataOutputStream;
import java.io.IOException;

public class MoveTask extends AsyncTask<Object, Object, Void> {

    @Override
    protected Void doInBackground(final Object... objects) {

        final View view = (View) objects[0];

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                try {
                    DataOutputStream dataOutputStream = SocketConnection.getOutputStream();

                    while (view.isPressed()) {

                        dataOutputStream.writeUTF(objects[1].toString());
                        dataOutputStream.flush();

                        Thread.sleep(200);
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

