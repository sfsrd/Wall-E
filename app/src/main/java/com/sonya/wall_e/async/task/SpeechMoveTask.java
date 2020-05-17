package com.sonya.wall_e.async.task;

import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;

import com.sonya.wall_e.bluetooth.BluetoothConnection;
import com.sonya.wall_e.service.DirectionParser;

import java.io.IOException;
import java.io.OutputStream;

public class SpeechMoveTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            BluetoothSocket bluetoothSocket = BluetoothConnection.getConnection();

            Runnable runnable = () -> {

                try {
                    OutputStream outputStream = bluetoothSocket.getOutputStream();

                    while (DirectionParser.getGlobalDirection() != null) {

                        outputStream.write(DirectionParser.getGlobalDirection().getDirection());
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
