package com.mufeng.sample.db.converters

import androidx.room.TypeConverter
import com.mufeng.mvvmlib.ext.fromJsonToList
import com.mufeng.mvvmlib.ext.toJson
import com.mufeng.sample.db.bean.Tag

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/21 14:11
 * @描述
 */
class TagConverters {

    @TypeConverter
    fun stringToObject(value: String): List<Tag>?{
        return value.fromJsonToList()
    }
    @TypeConverter
    fun objectToString(list: List<Tag>): String{
        return list.toJson()
    }

}