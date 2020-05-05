package com.sonya.wall_e.ui.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class HoldButton extends AppCompatButton {

    public HoldButton(Context context) {
        super(context);
    }

    public HoldButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HoldButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setPressed(true);
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            setPressed(false);
        }

        performClick();

        return true;
    }

    @Override
    public boolean performClick() {

        return super.performClick();
    }
}
