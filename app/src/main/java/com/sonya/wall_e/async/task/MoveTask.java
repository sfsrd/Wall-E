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



        return null;
    }
}

