package com.mufeng.mvvmlib.service

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author MuFeng-T
 * @createTime 2019-10-22
 * @details
 */
interface IUIService: IProvider {
    fun provideData(): String
}