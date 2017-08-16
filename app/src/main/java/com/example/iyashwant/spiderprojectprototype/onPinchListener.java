package com.example.iyashwant.spiderprojectprototype;

import android.view.ScaleGestureDetector;

/**
 * Created by rakesh on 16/8/17.
 */

public class onPinchListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

    float startingSpan;
    float endSpan;
    float startFocusX;
    float startFocusY;
    ZoomableRelativeLayout mZoomableRelativeLayout;

    onPinchListener(ZoomableRelativeLayout layout)
    {
        mZoomableRelativeLayout = layout;
    }

    public boolean onScaleBegin(ScaleGestureDetector detector) {
        startingSpan = detector.getCurrentSpan();
        startFocusX = detector.getFocusX();
        startFocusY = detector.getFocusY();
        return true;
    }


    public boolean onScale(ScaleGestureDetector detector) {
        mZoomableRelativeLayout.scale(detector.getCurrentSpan()/startingSpan, startFocusX, startFocusY);
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector detector) {
        mZoomableRelativeLayout.restore();
    }
}


