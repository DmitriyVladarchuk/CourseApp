package com.example.courseapp.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class CustomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        scaleType = ScaleType.MATRIX
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        recomputeImgMatrix()
    }

    override fun setFrame(l: Int, t: Int, r: Int, b: Int): Boolean {
        recomputeImgMatrix()
        return super.setFrame(l, t, r, b)
    }

    private fun recomputeImgMatrix() {
        drawable ?: return

        val matrix = imageMatrix

        val viewWidth = width - paddingLeft - paddingRight
        val viewHeight = height - paddingTop - paddingBottom
        val drawableWidth = drawable.intrinsicWidth
        val drawableHeight = drawable.intrinsicHeight
        val scale = viewWidth.toFloat() / drawableWidth

        matrix.setScale(scale, scale)

        val scaledHeight = drawableHeight * scale
        var translateY = (viewHeight - scaledHeight) / 2
        if (translateY < 0) translateY = 0f

        matrix.postTranslate(0f, translateY)

        imageMatrix = matrix
    }
}