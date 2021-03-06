package com.vipul.marketmvvmapp.view.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView

class CustomMapView : MapView {

    constructor(context: Context) : super(context)

    constructor(context: Context, googleMapOptions: GoogleMapOptions) : super(
        context,
        googleMapOptions
    )


    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, int: Int) : super(
        context,
        attributeSet,
        int
    )

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_UP -> {
                parent.requestDisallowInterceptTouchEvent(false)
            }
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
            }
        }
        return super.dispatchTouchEvent(ev)
    }


}