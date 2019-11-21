package com.mufeng.sample.db.converters

import androidx.room.TypeConverter
import com.mufeng.mvvmlib.ext.toJson
import com.mufeng.mvvmlib.ext.toJsonList
import com.mufeng.sample.db.bean.Tag

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/21 14:11
 * @描述
 */
class TagConverters {

    @TypeConverter
    fun stringToObject(value: String): List<Tag>?{
        return value.toJsonList()
    }
    @TypeConverter
    fun objectToString(list: List<Tag>): String{
        return list.toJson()
    }

}