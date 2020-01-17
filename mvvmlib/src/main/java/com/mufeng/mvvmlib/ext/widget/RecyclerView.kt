package com.mufeng.mvvmlib.ext.widget

import androidx.recyclerview.widget.RecyclerView

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/17 10:41
 * @描述
 */
/**
 * 移除所有差异性计算引发的默认更新动画.
 */
fun RecyclerView.removeAllAnimation() {
    val itemAnimator = DefaultItemNoAnimAnimator()
    this.itemAnimator = itemAnimator
    itemAnimator.supportsChangeAnimations = false
    itemAnimator.addDuration = 0L
    itemAnimator.changeDuration = 0L
    itemAnimator.removeDuration = 0L
}