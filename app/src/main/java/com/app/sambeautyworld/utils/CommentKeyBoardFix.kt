package com.app.sambeautyworld.utils

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.widget.FrameLayout

class CommentKeyBoardFix(activity: Activity) {
    private val mChildOfContent: View
    private var usableHeightPrevious: Int = 0
    private val frameLayoutParams: FrameLayout.LayoutParams
    private val contentAreaOfWindowBounds = Rect()

    init {
        val content = activity.findViewById<FrameLayout>(android.R.id.content)
        mChildOfContent = content.getChildAt(0)
        mChildOfContent.viewTreeObserver.addOnGlobalLayoutListener { this.possiblyResizeChildOfContent() }
        frameLayoutParams = mChildOfContent.layoutParams as FrameLayout.LayoutParams
    }

    private fun possiblyResizeChildOfContent() {
        val usableHeightNow = computeUsableHeight()
        if (usableHeightNow != usableHeightPrevious) {
            val heightDifference = 0
            if (heightDifference > usableHeightNow) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightNow - heightDifference
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightNow
            }
            mChildOfContent.layout(contentAreaOfWindowBounds.left, contentAreaOfWindowBounds.top, contentAreaOfWindowBounds.right, contentAreaOfWindowBounds.bottom)
            mChildOfContent.requestLayout()
            usableHeightPrevious = usableHeightNow
        }
    }

    private fun computeUsableHeight(): Int {
        mChildOfContent.getWindowVisibleDisplayFrame(contentAreaOfWindowBounds)
        return contentAreaOfWindowBounds.height()
    }
}