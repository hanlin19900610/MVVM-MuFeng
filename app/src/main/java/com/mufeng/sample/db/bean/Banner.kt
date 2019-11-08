package com.mufeng.sample.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Banner
 * @property desc String?
 * @property id Int?
 * @property imagePath String?
 * @property isVisible Int?
 * @property order Int?
 * @property title String?
 * @property type Int?
 * @property url String?
 * @constructor
 */
@Entity
data class Banner(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = 0,
    val desc: String? = "",
    val imagePath: String? = "",
    val isVisible: Int? = 0,
    val order: Int? = 0,
    val title: String? = "",
    val type: Int? = 0,
    val url: String? = ""
)