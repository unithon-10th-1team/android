package com.paradise.flickspick.util

import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

object FileUtil {
    fun imageExternalSave(bitmap: Bitmap): String {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {
            val rootPath =
                "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)}/mbti"
            val fileName = "mbti${System.currentTimeMillis()}.png"
            val savePath = File(rootPath)
            savePath.mkdirs()

            val file = File(savePath, fileName)

            if (file.exists()) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    file.delete()
                } else {
                    File(file.toString()).delete()
                }
            }

            try {
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()

                return file.path
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return ""
    }
}
