package com.mufeng.mvvmlib.ext

import com.mufeng.mvvmlib.utils.*
import java.io.File
import java.nio.charset.Charset

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */


val File.canListFiles: Boolean
    get() = canRead() and isDirectory

/**
 * 文件总大小, 包含所有子文件
 */
val File.totalSize: Long
    get() = if (isFile) length() else getFolderSize()

/**
 * 格式化的总大小(包含所有子文件)
 */
val File.formatSize: String
    get() = totalSize.getFormatFileSize()

/**
 * 返回文件的mimeType
 */
val File.mimeType: String
    get() = getMimeType(extension, isDirectory)

/**
 * 输出文件列表
 * @receiver File
 * @param isRecursive Boolean 是否要递归地列出
 * @param filter Function1<[@kotlin.ParameterName] File, Boolean>?  文件过滤
 * @return Array<out File>
 */
fun File.listFiles(
    isRecursive: Boolean = false,
    filter: ((file: File) -> Boolean)? = null
): Array<out File> {
    val fileList = if (!isRecursive) listFiles() else getAllSubFile()
    var result: Array<File> = arrayOf()
    return if (filter == null) fileList
    else {
        for (file in fileList) {
            if (filter(file)) result = result.plus(file)
        }
        result
    }
}

/**
 * 文字写入文件
 * @receiver File
 * @param append Boolean    拼接还是覆盖
 * @param text String
 * @param charset Charset   字符编码 默认UTF-8
 */
fun File.writeText(append: Boolean = false, text: String, charset: Charset = Charsets.UTF_8) {
    if (append) appendText(text, charset) else writeText(text, charset)
}

/**
 * 写入文件
 * @receiver File
 * @param append Boolean
 * @param bytes ByteArray
 */
fun File.writeBytes(append: Boolean = false, bytes: ByteArray) {
    if (append) appendBytes(bytes) else writeBytes(bytes)
}

/**
 * 移动文件
 * @receiver File 源文件
 * @param destFile File 目标文件
 * @param overwrite Boolean
 * @param reserve Boolean
 * @return Boolean
 */
fun File.moveTo(destFile: File, overwrite: Boolean = true, reserve: Boolean = true): Boolean {
    val dest = copyRecursively(destFile, overwrite)
    if (!reserve) deleteRecursively()
    return dest
}

/**
 *
 * @receiver File
 * @param destFolder File
 * @param overwrite Boolean
 * @param reserve Boolean
 * @param func Function2<[@kotlin.ParameterName] File, [@kotlin.ParameterName] Int, Unit>?
 */
fun File.moveToWithProgress(
    destFolder: File,
    overwrite: Boolean = true,
    reserve: Boolean = true,
    func: ((file: File, i: Int) -> Unit)? = null
) {

    if (isDirectory) copyFolder(this, File(destFolder, name), overwrite, func)
    else copyFile(this, File(destFolder, name), overwrite, func)

    if (!reserve) deleteRecursively()
}

/** Rename to newName */
fun File.rename(newName: String) =
    rename(File("$parent${File.separator}$newName"))

/** Rename to newFile's name */
fun File.rename(newFile: File) =
    if (newFile.exists()) false else renameTo(newFile)