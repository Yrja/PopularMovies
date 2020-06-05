package com.example.movies.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.movies.R

class CustomView:FrameLayout {

    init {
        inflate(context, R.layout.custom_view_layout, this)
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}