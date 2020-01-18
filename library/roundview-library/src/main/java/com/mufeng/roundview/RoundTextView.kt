package com.mufeng.roundview

import android.content.Context
import android.widget.TextView
import android.util.AttributeSet

/**
 * @author MuFeng-T
 * @createTime 2019-10-18
 * @details  用于需要圆角矩形框背景的TextView的情况,减少直接使用TextView时引入的shape资源文件
 */
class RoundTextView : TextView {


    var delegate: RoundViewDelegate

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
            super(context, attrs, defStyleAttr) {
        delegate = RoundViewDelegate(this, context, attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (delegate.isWidthHeightEqual && width > 0 && height > 0) {
            val max = width.coerceAtLeast(height)
            val measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY)
            super.onMeasure(measureSpec, measureSpec)
            return
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (delegate.isRadiusHalfHeight) {
            delegate.cornerRadius = height / 2
        } else {
            delegate.setBgSelector()
        }
    }
}