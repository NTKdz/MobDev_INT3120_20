package com.example.slide04

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSpinner

class CustomSpinner : AppCompatSpinner {
    private var mListener: OnSpinnerEventsListener? = null
    private var mOpenInitiated = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, mode: Int) : super(context, mode)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, mode: Int) : super(context, attrs, defStyleAttr, mode)

    interface OnSpinnerEventsListener {
        fun onSpinnerOpened(spin: CustomSpinner?)
        fun onSpinnerClosed(spin: CustomSpinner?)
    }

    override fun performClick(): Boolean {
        mOpenInitiated = true
        mListener?.onSpinnerOpened(this)
        return super.performClick()
    }

    fun setSpinnerEventsListener(onSpinnerEventsListener: OnSpinnerEventsListener?) {
        mListener = onSpinnerEventsListener
    }

    fun performClosedEvent() {
        mOpenInitiated = false
        mListener?.onSpinnerClosed(this)
    }

    fun hasBeenOpened(): Boolean {
        return mOpenInitiated
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasBeenOpened() && hasWindowFocus) {
            performClosedEvent()
        }
    }
}
