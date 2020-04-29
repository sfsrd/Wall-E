package com.sonya.wall_e;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sonya.wall_e.api.client.EspClient;
import com.sonya.wall_e.constants.DirectionConstants;

public class MainActivity extends AppCompatActivity {

    private EspClient _espClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _espClient = new EspClient();
    }

    public void moveForward(View view) {
        _espClient.move(DirectionConstants.FORWARD);
    }

    public void moveBackward(View view) {
        _espClient.move(DirectionConstants.BACKWARD);
    }

    public void moveLeft(View view) {
        _espClient.move(DirectionConstants.LEFT);
    }

    public void moveRight(View view) {
        _espClient.move(DirectionConstants.RIGHT);
    }
}
