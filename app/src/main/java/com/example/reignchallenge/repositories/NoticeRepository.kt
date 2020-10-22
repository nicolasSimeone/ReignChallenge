package com.example.reignchallenge.repositories

import com.example.reignchallenge.model.NoticeDeleted
import com.example.reignchallenge.model.NoticesSaved
import com.example.reignchallenge.model.hits
import com.example.reignchallenge.repositories.local.db.AppDatabase
import com.example.reignchallenge.repositories.remote.NoticeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.reignchallenge.utils.Result

class NoticeRepository(private val remoteDataSource: NoticeService, private val appDatabase: AppDatabase) {

    suspend fun getNotices(): hits = withContext(Dispatchers.IO)
    {
        val result = remoteDataSource.getNotices()

        when (result) {
            is Result.Success -> result.data
            is Result.Error -> throw result.exception
        }
    }

    suspend fun getNoticesResultsDb(): List<NoticeDeleted> = withContext(Dispatchers.IO) {

        return@withContext appDatabase.NoticeDeletedDAO().getAll()
    }

    suspend fun getNoticesResultsOffline(): List<NoticesSaved> = withContext(Dispatchers.IO) {

        return@withContext appDatabase.NoticeDeletedDAO().getAllOffline()
    }

    suspend fun insertNoticesDb(notices:MutableList<NoticesSaved>) = withContext(Dispatchers.IO) {
        return@withContext appDatabase.NoticeDeletedDAO().insertNoticesSaved(notices)
    }

    suspend fun deleteNoticesResultsDb() = withContext(Dispatchers.IO) {
       appDatabase.NoticeDeletedDAO().deleteNotices()
        return@withContext
    }



    suspend fun insertNoticesDeletedDb(storyId:String?) = withContext(Dispatchers.IO) {
        val noticeDeleted = NoticeDeleted(storyId)

        return@withContext appDatabase.NoticeDeletedDAO().insertNoticeDeleted(noticeDeleted)
    }
}