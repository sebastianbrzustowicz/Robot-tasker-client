package com.sebastianbrzustowicz.robottaskerclient.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;

public class TriStateButton extends AppCompatButton {

    private static final int STATE_SLOW = 0;
    private static final int STATE_NORMAL = 1;
    private static final int STATE_FAST = 2;

    private int currentState = STATE_NORMAL;
    private OnTriStateChangeListener triStateChangeListener;

    public TriStateButton(Context context) {
        super(context);
        init();
    }

    public TriStateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TriStateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        updateButtonState();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleState();
                notifyStateChanged();
            }
        });
    }

    private void toggleState() {
        switch (currentState) {
            case STATE_SLOW:
                currentState = STATE_NORMAL;
                break;
            case STATE_NORMAL:
                currentState = STATE_FAST;
                break;
            case STATE_FAST:
                currentState = STATE_SLOW;
                break;
        }
        updateButtonState();
    }

    private void updateButtonState() {
        switch (currentState) {
            case STATE_SLOW:
                setText("Slow");
                break;
            case STATE_NORMAL:
                setText("Normal");
                break;
            case STATE_FAST:
                setText("Fast");
                break;
        }
    }

    private void notifyStateChanged() {
        if (triStateChangeListener != null) {
            triStateChangeListener.onStateChanged(currentState);
        }
    }

    public void setOnTriStateChangeListener(OnTriStateChangeListener listener) {
        this.triStateChangeListener = listener;
    }

    public interface OnTriStateChangeListener {
        void onStateChanged(int newState);
    }
}