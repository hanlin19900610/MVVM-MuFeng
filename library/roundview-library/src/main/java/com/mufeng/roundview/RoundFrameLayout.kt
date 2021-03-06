package com.mufeng.roundview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * @author MuFeng-T
 * @createTime 2019-10-18
 * @details
 */
class RoundFrameLayout : FrameLayout {

    var delegate: RoundViewDelegate

    /**
     * 是否显示阴影
     */
    private var isShadowed: Boolean = false
    /**
     * 设置阴影的距离
     */
    private var shadowDistance: Int = 0
    /**
     * 设置阴影的角度
     */
    private var shadowAngle: Int = 0
    /**
     * 设置阴影的半径
     */
    private var shadowRadius: Int = 0
    /**
     * 设置阴影颜色
     */
    private var shadowColor: Int = 0

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
            super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundFrameLayout)
        delegate = RoundViewDelegate(this, context, attrs)

        typedArray.recycle()
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