package com.example.reignchallenge.repositories.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.reignchallenge.model.NoticeDeleted
import com.example.reignchallenge.model.NoticesSaved

@Dao
interface NoticeDeletedDAO {

    @Insert
    fun insertNoticeDeleted(noticeDeleted: NoticeDeleted)

    @Query("SELECT * FROM noticesDeleted")
    fun getAll(): List<NoticeDeleted>

    @Insert
    fun insertNoticesSaved(notices: MutableList<NoticesSaved>)

    @Query("SELECT * FROM noticesSaved")
    fun getAllOffline(): List<NoticesSaved>


    @Query("DELETE FROM noticesSaved")
    fun deleteNotices()
}