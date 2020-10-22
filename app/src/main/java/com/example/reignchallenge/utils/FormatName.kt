package com.example.reignchallenge.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import com.example.reignchallenge.model.NoticesSaved
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun formatName(name: NoticesSaved) : String {

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    val date:Date? = sdf.parse(name.created_at!!)
    val timeAgo = TimeAgo.getTimeAgo(date)
    return name.author + " " + "-"+ " " + timeAgo
}