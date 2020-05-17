package com.sonya.wall_e.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class BluetoothConnection {

    private static final String DEFAULT_DEVICE_NAME = "Wall-E";

    private static BluetoothAdapter _bluetooth = BluetoothAdapter.getDefaultAdapter();

    private static BluetoothSocket _connection = null;

    private static String _deviceName = null;

    BluetoothConnection() {
    }

    public static BluetoothSocket getConnection() throws IOException {

        if (_connection == null) {

            if (_deviceName == null || _deviceName.equals("")) {

                _connection = connect(DEFAULT_DEVICE_NAME);
            } else {

                _connection = connect(_deviceName);
            }

            if (_connection != null) {

                _connection.connect();
            }
        }
        return _connection;
    }

    public static Set<BluetoothDevice> getPairedDevices() {

        if (_bluetooth != null) {

            return _bluetooth.getBondedDevices();
        }
        return null;
    }

    private static BluetoothSocket connect(String deviceName) throws IOException {

        Set<BluetoothDevice> devices = BluetoothConnection.getPairedDevices();

        if (devices != null) {

            Iterator devicesIterator = devices.iterator();

            while (devicesIterator.hasNext()) {

                BluetoothDevice device = (BluetoothDevice) devicesIterator.next();

                if (device.getName().equals(deviceName)) {

                    if (device.getUuids().length > 0) {

                        UUID deviceUuid = device.getUuids()[0].getUuid();

                        return device.createRfcommSocketToServiceRecord(deviceUuid);
                    }
                }
            }
        }
        return null;
    }

    public static void setDeviceName(String deviceName) {
        _deviceName = deviceName;
    }
}