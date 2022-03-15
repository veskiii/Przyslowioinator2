package com.example.przyslowioinator2.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun addToClipboard(context: Context, label: String, content: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip: ClipData = ClipData.newPlainText(label, content)

    clipboardManager.setPrimaryClip(clip)
}