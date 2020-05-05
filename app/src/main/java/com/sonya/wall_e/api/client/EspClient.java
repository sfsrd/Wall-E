package com.sonya.wall_e.api.client;

import android.view.View;

import com.sonya.wall_e.api.connection.SocketConnection;

import java.io.DataOutputStream;
import java.io.IOException;

public class EspClient  {

    public void move(final View view, final char direction) {

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
    }
}
