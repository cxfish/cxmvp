package com.hcx.framework.cxmvplibrary.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mTopSpace;
    int mBottomSpace;
    int mLeftSpace;
    int mRightSpace;

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p>
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mBottomSpace;
        outRect.left = mLeftSpace;
        outRect.right = mRightSpace;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mTopSpace;
        }

    }

    public SpaceItemDecoration(int topSpace,int bottomSpace,int leftSpace,int rightSpace) {
        this.mTopSpace = topSpace;
        this.mBottomSpace = bottomSpace;
        this.mLeftSpace = leftSpace;
        this.mRightSpace = rightSpace;
    }
}
