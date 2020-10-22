package com.example.reignchallenge.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "noticesSaved")
@Parcelize
data class NoticesSaved (
    @ColumnInfo(name = "created_at")
    val created_at:String? = "" ,

    @ColumnInfo(name = "story_title")
    val story_title: String? = "",

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "objectID")
    val objectID: String? = "",

    @ColumnInfo(name = "author")
    val author:String? = "",

    @ColumnInfo(name = "story_url")
    val story_url: String? = "",

    @ColumnInfo(name = "story_id")
    val story_id:String? = ""
):Parcelable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}