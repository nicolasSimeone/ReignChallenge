package com.example.reignchallenge.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "noticesDeleted")
@Parcelize
data class NoticeDeleted (
    @ColumnInfo(name = "storyId")
    val storyId: String?
):Parcelable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}