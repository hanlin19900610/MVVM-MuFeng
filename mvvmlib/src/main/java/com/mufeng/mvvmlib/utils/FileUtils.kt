package com.mufeng.mvvmlib.utils

import com.mufeng.mvvmlib.ext.canListFiles
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.ByteBuffer
import java.text.DecimalFormat

/**
 * @author MuFeng-T
 * @createTime 2019-10-17
 * @details
 */

/**
 * 返回文件大小 包含子文件
 * @receiver File
 * @return Long
 */
fun File.getFolderSize(): Long {
    var total = 0L
    listFiles().forEach { subFile ->
        total += if (subFile.isFile) subFile.length()
        else subFile.getFolderSize()
    }
    return total
}

/**
 * 返回格式化的文件大小
 * @receiver Long
 * @param unit Int
 * @return String
 */
fun Long.getFormatFileSize(unit: Int = 1000): String {
    val formatter = DecimalFormat("####.00")
    return when {
        this < 0 -> "0 B"
        this < unit -> "$this B"
        this < unit * unit -> "${formatter.format(toDouble() / unit)} KB"
        this < unit * unit * unit -> "${formatter.format(toDouble() / unit / unit)} MB"
        else -> "${formatter.format(toDouble() / unit / unit / unit)} GB"
    }
}


fun File.getAllSubFile(): Array<File> {
    var fileList: Array<File> = arrayOf()
    if (!canListFiles) return fileList
    listFiles().forEach { subFile ->
        fileList = if (subFile.isFile) fileList.plus(subFile)
        else fileList.plus(subFile.getAllSubFile())
    }
    return fileList
}

/**
 * 复制文件 仅复制单个文件, 而非文件夹
 * @param sourceFile File   源文件
 * @param destFile File 目标文件
 * @param overwrite Boolean 如果目标文件已存在, 是否要覆盖它
 * @param func Function2<[@kotlin.ParameterName] File, [@kotlin.ParameterName] Int, Unit>?
 */
fun copyFile(sourceFile: File, destFile: File, overwrite: Boolean, func: ((file: File, i: Int) -> Unit)? = null) {

    if (!sourceFile.exists()) return

    if (destFile.exists()) {
        val stillExists = if (!overwrite) true else !destFile.delete()

        if (stillExists) {
            return
        }
    }

    if (!destFile.exists()) destFile.createNewFile()

    val inputStream = FileInputStream(sourceFile)
    val outputStream = FileOutputStream(destFile)
    val iChannel = inputStream.channel
    val oChannel = outputStream.channel


    val totalSize = sourceFile.length()
    val buffer = ByteBuffer.allocate(1024)
    var hasRead = 0f
    var progress = -1
    while (true) {
        buffer.clear()
        val read = iChannel.read(buffer)
        if (read == -1)
            break
        buffer.limit(buffer.position())
        buffer.position(0)
        oChannel.write(buffer)
        hasRead += read

        func?.let {
            val newProgress = ((hasRead / totalSize) * 100).toInt()
            if (progress != newProgress) {
                progress = newProgress
                it(sourceFile, progress)
            }
        }
    }

    inputStream.close()
    outputStream.close()
}

/**
 * 复制文件夹
 * @param sourceFolder File
 * @param destFolder File
 * @param overwrite Boolean
 * @param func Function2<[@kotlin.ParameterName] File, [@kotlin.ParameterName] Int, Unit>?
 */
fun copyFolder(sourceFolder: File, destFolder: File, overwrite: Boolean, func: ((file: File, i: Int) -> Unit)? = null) {
    if (!sourceFolder.exists()) return

    if (!destFolder.exists()) {
        val result = destFolder.mkdirs()
        if (!result) return
    }

    sourceFolder.listFiles().forEach { subFile ->
        if (subFile.isDirectory) {
            copyFolder(subFile, File("${destFolder.path}${File.separator}${subFile.name}"), overwrite, func)
        } else {
            copyFile(subFile, File(destFolder, subFile.name), overwrite, func)
        }
    }
}
