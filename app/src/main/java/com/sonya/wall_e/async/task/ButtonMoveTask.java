package com.sonya.wall_e.async.task;

import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.view.View;

import com.sonya.wall_e.bluetooth.BluetoothConnection;
import com.sonya.wall_e.constants.Direction;

import java.io.IOException;
import java.io.OutputStream;

public class ButtonMoveTask extends AsyncTask<Object, Object, Void> {

    @Override
    protected Void doInBackground(final Object... objects) {

        try {
            final View view = (View) objects[0];

            final Direction direction = (Direction) objects[1];

            BluetoothSocket bluetoothSocket = BluetoothConnection.getConnection();

            Runnable runnable = () -> {

                try {
                    OutputStream outputStream = bluetoothSocket.getOutputStream();

                    while (view.isPressed() && direction != null) {

                        outputStream.write(direction.getDirection());
                        outputStream.flush();

                        Thread.sleep(50);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            };

            if (bluetoothSocket != null) {

                runnable.run();
            }
        } catch (IOException ignored) {
        }

        return null;
    }
}

