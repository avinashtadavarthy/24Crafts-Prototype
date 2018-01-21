package com.twenty.four.crafts.header;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.twenty.four.crafts.TailItemTransformer;
import com.twenty.four.crafts.TailLayoutManager;


/**
 * Implementation of {@link TailLayoutManager.PageTransformer}.
 */
public class HeaderTransformer implements TailLayoutManager.PageTransformer<HeaderItem> {

    private final TailItemTransformer mTransformer;

    public HeaderTransformer() {
        mTransformer = new TailItemTransformer();
    }

    /**
     * Constructor that specifies inner item transformer.
     * @param transformer inner item transformer.
     */
    public HeaderTransformer(@NonNull TailItemTransformer transformer) {
        mTransformer = transformer;
    }

    @Override
    public void transformPage(@NonNull HeaderItem item, float scrollPosition) {
        final View header = item.getHeader();
        final TailItemTransformer.TransformParams params = mTransformer.getParamsForPosition(
                scrollPosition, header.getWidth(), header.getHeight());

        ViewCompat.setPivotX(header, params.pivotX);
        ViewCompat.setScaleX(header, params.scale);
        ViewCompat.setScaleY(header, params.scale);
        ViewCompat.setAlpha(header, scrollPosition < 0 ? params.alphaLeft : params.alphaRight);
        ViewCompat.setTranslationY(header, params.offsetY);

        ViewCompat.setAlpha(item.getHeaderAlphaView(), 1 - params.alphaChild);

        mTransformer.transformPage(item, scrollPosition);
    }

}
