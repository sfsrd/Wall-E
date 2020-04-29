package com.sonya.wall_e;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

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

        final Button button = findViewById(R.id.button);

        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    button.setPressed(false);
                }

                button.performClick();

                return true;
            }
        });

        final Button button5 = findViewById(R.id.button5);

        button5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button5.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    button5.setPressed(false);
                }

                button5.performClick();

                return true;
            }
        });

        final Button button2 = findViewById(R.id.button2);

        button2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button2.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    button2.setPressed(false);
                }

                button2.performClick();

                return true;
            }
        });

        final Button button3 = findViewById(R.id.button3);

        button3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button3.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    button3.setPressed(false);
                }

                button3.performClick();

                return true;
            }
        });
    }

    public void moveForward(View view) {
        _espClient.move(view, DirectionConstants.FORWARD);
    }

    public void moveBackward(View view) {
        _espClient.move(view, DirectionConstants.BACKWARD);
    }

    public void moveLeft(View view) {
        _espClient.move(view, DirectionConstants.LEFT);
    }

    public void moveRight(View view) {
        _espClient.move(view, DirectionConstants.RIGHT);
    }
}
