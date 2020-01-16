package com.mufeng.sample.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

/**
 * @author MuFeng-T
 * @createTime 2019-09-25
 * @details 用于子类防止父类拦截子类的事件
 */
class DisInterceptNestedScrollView : NestedScrollView{

    constructor(context: Context): super(context) {
        requestDisallowInterceptTouchEvent(true)
    }

    constructor(context: Context,attrs: AttributeSet?): super(context, attrs){
        requestDisallowInterceptTouchEvent(true)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        requestDisallowInterceptTouchEvent(true)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> requestDisallowInterceptTouchEvent(true)
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> requestDisallowInterceptTouchEvent(false)
        }
        return super.onTouchEvent(event)
    }

}