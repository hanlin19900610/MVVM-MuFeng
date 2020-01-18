package com.mufeng.roundview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.graphics.drawable.StateListDrawable
import android.widget.TextView


/**
 * @author MuFeng-T
 * @createTime 2019-10-18
 * @details
 */
class RoundViewDelegate(view: View, context: Context, attrs: AttributeSet?) {

    /**
     * 被代理的View
     */
    private val view = view
    /**
     * 上下文对象
     */
    private val context = context

    /**
     * 默认状态下的背景
     */
    private val gradientDrawable = GradientDrawable()
    /**
     * 按下状态的背景
     */
    private val gdBackgroundPress = GradientDrawable()

    /**
     * 背景色
     */
    private var backgroundColor: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 按下状态的背景色
     */
    private var backgroundPressColor: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 圆角半径 (统一设置)
     */
    var cornerRadius: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 左上角半径
     */
    private var cornerradiusTl: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 右上角半径
     */
    private var cornerradiusTr: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 左下角半径
     */
    private var cornerradiusBl: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 右下角半径
     */
    private var cornerradiusBr: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 边框宽度
     */
    private var strokeWidth: Int = 0
        set(value) {
            field = dp2px(value.toFloat())
            setBgSelector()
        }
    /**
     * 边框颜色
     */
    private var strokeColor: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 按下的边框颜色
     */
    private var strokePressColor: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 按下的文字颜色
     */
    private var textPressColor: Int = Int.MAX_VALUE
        set(value) {
            field = value
            setBgSelector()
        }

    var isRadiusHalfHeight: Boolean = false
        set(value) {
            field = value
            setBgSelector()
        }
    var isWidthHeightEqual: Boolean = false
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 是否产生涟漪 水波纹效果
     */
    private var isRippleEnable: Boolean = false
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 圆角数组
     */
    private val radiusArr = FloatArray(8)

    /**
     * 是否背景颜色渐变
     */
    private var isGradient: Boolean = false
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 渐变开始颜色
     */
    private var startColor: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 按下状态的渐变开始颜色
     */
    private var startColorPress: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 渐变中间颜色
     */
    private var centerColor: Int = Int.MAX_VALUE
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 按下状态的渐变中间颜色
     */
    private var centerColorPress: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 渐变结束颜色
     */
    private var endColor: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 按下状态的渐变结束颜色
     */
    private var endColorPress: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 渐变的类型  默认线性渐变
     */
    private var gradientType: Int = GradientDrawable.LINEAR_GRADIENT
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 渐变的方向  默认从上到下
     */
    private var gradientOrientation: GradientDrawable.Orientation =
        GradientDrawable.Orientation.TOP_BOTTOM
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 放射性渐变 X点
     */
    private var gradientCenterX: Float = 0.0f
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 放射性渐变 Y点
     */
    private var gradientCenterY: Float = 0.0f
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 放射渐变半径
     */
    private var gradientRadius: Float = 0.0f
        set(value) {
            field = value
            setBgSelector()
        }

    /**
     * 虚线宽度
     */
    private var dashWidth: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }
    /**
     * 虚线间隔
     */
    private var dashGap: Int = 0
        set(value) {
            field = value
            setBgSelector()
        }

    init {
        obtainAttributes(context, attrs)
    }

    private fun obtainAttributes(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView)

        backgroundColor =
            ta.getColor(R.styleable.RoundTextView_rv_backgroundColor, Color.TRANSPARENT)
        backgroundPressColor =
            ta.getColor(R.styleable.RoundTextView_rv_backgroundPressColor, Int.MAX_VALUE)
        cornerRadius = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius, 0)
        strokeWidth = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_strokeWidth, 0)
        strokeColor = ta.getColor(R.styleable.RoundTextView_rv_strokeColor, Color.TRANSPARENT)
        strokePressColor =
            ta.getColor(R.styleable.RoundTextView_rv_strokePressColor, Int.MAX_VALUE)
        textPressColor = ta.getColor(R.styleable.RoundTextView_rv_textPressColor, Integer.MAX_VALUE)
        isRadiusHalfHeight = ta.getBoolean(R.styleable.RoundTextView_rv_isRadiusHalfHeight, false)
        isWidthHeightEqual = ta.getBoolean(R.styleable.RoundTextView_rv_isWidthHeightEqual, false)
        cornerradiusTl = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TL, 0)
        cornerradiusTr = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_TR, 0)
        cornerradiusBl = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BL, 0)
        cornerradiusBr = ta.getDimensionPixelSize(R.styleable.RoundTextView_rv_cornerRadius_BR, 0)
        isRippleEnable = ta.getBoolean(R.styleable.RoundTextView_rv_isRippleEnable, true)

        isGradient = ta.getBoolean(R.styleable.RoundTextView_rv_isGradient, false)
        startColor = ta.getColor(R.styleable.RoundTextView_rv_startColor, Int.MAX_VALUE)
        startColorPress = ta.getColor(R.styleable.RoundTextView_rv_startColorPress, Int.MAX_VALUE)
        centerColor = ta.getColor(R.styleable.RoundTextView_rv_centerColor, Int.MAX_VALUE)
        centerColorPress = ta.getColor(R.styleable.RoundTextView_rv_centerColorPress, Int.MAX_VALUE)
        endColor = ta.getColor(R.styleable.RoundTextView_rv_endColor, Int.MAX_VALUE)
        endColorPress = ta.getColor(R.styleable.RoundTextView_rv_endColorPress, Int.MAX_VALUE)
        gradientType =
            ta.getInt(R.styleable.RoundTextView_rv_gradientType, GradientDrawable.LINEAR_GRADIENT)
        val orientation = ta.getInt(R.styleable.RoundTextView_rv_gradientOrientation, 0)
        gradientOrientation = when (orientation) {
            1 -> GradientDrawable.Orientation.TR_BL
            2 -> GradientDrawable.Orientation.RIGHT_LEFT
            3 -> GradientDrawable.Orientation.BR_TL
            4 -> GradientDrawable.Orientation.BOTTOM_TOP
            5 -> GradientDrawable.Orientation.BL_TR
            6 -> GradientDrawable.Orientation.LEFT_RIGHT
            7 -> GradientDrawable.Orientation.TL_BR
            else -> GradientDrawable.Orientation.TOP_BOTTOM
        }

        gradientCenterX = ta.getFloat(R.styleable.RoundTextView_rv_gradientCenterX, 0.0f)
        gradientCenterY = ta.getFloat(R.styleable.RoundTextView_rv_gradientCenterY, 0.0f)
        gradientRadius = ta.getFloat(R.styleable.RoundTextView_rv_gradientRadius, 0.0f)

        // 虚线
        dashGap = ta.getDimensionPixelOffset(R.styleable.RoundTextView_rv_dashGap,0)
        dashWidth = ta.getDimensionPixelOffset(R.styleable.RoundTextView_rv_dashWidth,0)

        ta.recycle()
    }


    private fun sp2px(sp: Float): Int {
        val scale = context.resources.displayMetrics.scaledDensity
        return (sp * scale + 0.5f).toInt()
    }

    private fun dp2px(dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun setBgSelector() {
        val bg = StateListDrawable()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && isRippleEnable) {
            setDrawable(
                gradientDrawable,
                backgroundColor,
                strokeColor,
                isGradient,
                startColor,
                centerColor,
                endColor,
                gradientType,
                gradientOrientation
            )
            val rippleDrawable = RippleDrawable(
                getPressedColorSelector(backgroundColor, backgroundPressColor),
                gradientDrawable,
                view.background
            )

            view.background = rippleDrawable
        } else {
            setDrawable(
                gradientDrawable,
                backgroundColor,
                strokeColor,
                isGradient,
                startColor,
                centerColor,
                endColor,
                gradientType,
                gradientOrientation
            )
            bg.addState(IntArray(1) { -android.R.attr.state_pressed }, gradientDrawable)
            setDrawable(
                gdBackgroundPress,
                if (backgroundPressColor == Int.MAX_VALUE) backgroundColor else backgroundPressColor,
                if (strokePressColor == Int.MAX_VALUE) strokeColor else strokePressColor,
                isGradient,
                if (startColorPress == Int.MAX_VALUE) startColor else startColorPress,
                if (centerColorPress == Int.MAX_VALUE) centerColor else centerColorPress,
                if (endColorPress == Int.MAX_VALUE) endColor else endColorPress,
                gradientType,
                gradientOrientation
            )
            bg.addState(IntArray(1) { android.R.attr.state_pressed }, gdBackgroundPress)

            view.background = bg
        }

        if (view is TextView) {
            if (textPressColor != Int.MAX_VALUE) {
                val textColors = view.textColors
                val colorStateList = ColorStateList(
                    arrayOf(
                        intArrayOf(-android.R.attr.state_pressed),
                        intArrayOf(android.R.attr.state_pressed)
                    ),
                    intArrayOf(textColors.defaultColor, textPressColor)
                )
                view.setTextColor(colorStateList)
            }
        }
    }

    private fun setDrawable(gd: GradientDrawable, color: Int, strokeColor: Int) {
        gd.setColor(color)

        if (cornerradiusTl > 0 || cornerradiusTr > 0 || cornerradiusBr > 0 || cornerradiusBl > 0) {
            radiusArr[0] = cornerradiusTl.toFloat()
            radiusArr[1] = cornerradiusTl.toFloat()
            radiusArr[2] = cornerradiusTr.toFloat()
            radiusArr[3] = cornerradiusTr.toFloat()
            radiusArr[4] = cornerradiusBr.toFloat()
            radiusArr[5] = cornerradiusBr.toFloat()
            radiusArr[6] = cornerradiusBl.toFloat()
            radiusArr[7] = cornerradiusBl.toFloat()
            gd.cornerRadii = radiusArr
        } else {
            gd.cornerRadius = cornerRadius.toFloat()
        }

        gd.setStroke(strokeWidth, strokeColor)
    }

    private fun setDrawable(
        gd: GradientDrawable, color: Int, strokeColor: Int,
        isGradient: Boolean, startColor: Int,centerColor: Int, endColor: Int,
        gradientType: Int, gradientOrientation: GradientDrawable.Orientation
    ) {

        if (isGradient) {
            gd.orientation = gradientOrientation
            gd.gradientType = gradientType
            if (centerColor == Int.MAX_VALUE){
                gd.colors = intArrayOf(startColor, endColor)
            }else {
                gd.colors = intArrayOf(startColor, centerColor, endColor)
            }
            if (gradientType == GradientDrawable.RADIAL_GRADIENT) {
                gd.setGradientCenter(gradientCenterX, gradientCenterY)
                gd.gradientRadius = gradientRadius
            }
        } else {
            gd.setColor(color)
        }

        if (cornerradiusTl > 0 || cornerradiusTr > 0 || cornerradiusBr > 0 || cornerradiusBl > 0) {
            radiusArr[0] = cornerradiusTl.toFloat()
            radiusArr[1] = cornerradiusTl.toFloat()
            radiusArr[2] = cornerradiusTr.toFloat()
            radiusArr[3] = cornerradiusTr.toFloat()
            radiusArr[4] = cornerradiusBr.toFloat()
            radiusArr[5] = cornerradiusBr.toFloat()
            radiusArr[6] = cornerradiusBl.toFloat()
            radiusArr[7] = cornerradiusBl.toFloat()
            gd.cornerRadii = radiusArr
        } else {
            gd.cornerRadius = cornerRadius.toFloat()
        }

        gd.setStroke(strokeWidth, strokeColor, dashWidth.toFloat(), dashGap.toFloat())

    }

    private fun getPressedColorSelector(normalColor: Int, pressedColor: Int): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_focused),
                intArrayOf(android.R.attr.state_activated),
                intArrayOf()
            ),
            intArrayOf(pressedColor, pressedColor, pressedColor, normalColor)
        )
    }
}