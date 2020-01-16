package com.mufeng.sample.db.bean


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * 搜索热词
 * @property id Int?
 * @property link String?
 * @property name String?
 * @property order Int?
 * @property visible Int?
 * @constructor
 */
@Entity
data class HotKeywords(
    @PrimaryKey(autoGenerate = false)
    @SerializedName(value = "id")
    val id: Int? = 0,
    @SerializedName(value = "link")
    val link: String? = "",
    @SerializedName(value = "name")
    val name: String? = "",
    @SerializedName(value = "order")
    val order: Int? = 0,
    @SerializedName(value = "visible")
    val visible: Int? = 0
)