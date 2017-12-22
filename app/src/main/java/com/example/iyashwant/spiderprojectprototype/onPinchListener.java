package com.example.iyashwant.spiderprojectprototype;

import android.view.ScaleGestureDetector;

import com.example.iyashwant.spiderprojectprototype.ZoomableRelativeLayout;

public class onPinchListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

    float currentSpan;
    float startFocusX;
    float startFocusY;
    ZoomableRelativeLayout mZoomableRelativeLayout;

    onPinchListener(ZoomableRelativeLayout layout)
    {
        mZoomableRelativeLayout = layout;
    }

    public boolean onScaleBegin(ScaleGestureDetector detector)
    {
        currentSpan = detector.getCurrentSpan();
        startFocusX = detector.getFocusX();
        startFocusY = detector.getFocusY();
        return true;
    }

    public boolean onScale(ScaleGestureDetector detector)
    {
        mZoomableRelativeLayout.relativeScale(detector.getCurrentSpan() / currentSpan, startFocusX, startFocusY);

        currentSpan = detector.getCurrentSpan();

        return true;
    }

    public void onScaleEnd(ScaleGestureDetector detector)
    {
        mZoomableRelativeLayout.release();
    }
}


