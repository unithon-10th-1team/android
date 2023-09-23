package com.paradise.flickspick.util

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import java.io.File

object ShareUtil {
    fun shareInstagram(context: Context, path: String) {
        val intent = Intent("com.instagram.share.ADD_TO_STORY")
        val file = File(path)
        intent.type = "image/*"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("interactive_asset_uri", file.toUri())
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        context.startActivity(intent)
    }
}