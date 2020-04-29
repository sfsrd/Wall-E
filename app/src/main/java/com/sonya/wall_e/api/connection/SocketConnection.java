package com.sonya.wall_e.api.connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketConnection {

    private static Socket socket;

    private static DataOutputStream dataOutputStream;


    SocketConnection () {

    }

    public static Socket getInstance() throws IOException {

        if (socket == null) {

            socket = new Socket("192.168.4.1", 80);
        } else {
            if (socket.isClosed()) {

                socket = new Socket("192.168.4.1", 80);
            }
        }
        return socket;
    }

    public static DataOutputStream getOutputStream() throws IOException {

        if (dataOutputStream == null) {

            dataOutputStream = new DataOutputStream(SocketConnection.getInstance().getOutputStream());
        }
        return dataOutputStream;
    }
}
