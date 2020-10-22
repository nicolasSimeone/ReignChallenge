package com.example.reignchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reignchallenge.model.NoticeDeleted
import com.example.reignchallenge.model.NoticesSaved
import com.example.reignchallenge.model.hits
import com.example.reignchallenge.repositories.NoticeRepository
import kotlinx.coroutines.launch

class HomeViewModel (private val noticeRepository: NoticeRepository): ViewModel() {

    private val _notice = MutableLiveData<hits>()
    val notice : LiveData<hits>
        get() = _notice

    private val _noticeDeleted = MutableLiveData<List<NoticeDeleted>>()
    val noticeDeleted: LiveData<List<NoticeDeleted>>
        get() = _noticeDeleted

    private val _noticeOffline = MutableLiveData<List<NoticesSaved>>()
    val noticeOffline : LiveData<List<NoticesSaved>>
        get() = _noticeOffline


    fun getAllNotices(){
        viewModelScope.launch {
            _notice.value = noticeRepository.getNotices()
        }
    }

    fun getAllNoticesOffline(){
        viewModelScope.launch {
            _noticeOffline.value = noticeRepository.getNoticesResultsOffline()
        }
    }

    fun getDeletedNotices(){
        viewModelScope.launch {
            _noticeDeleted.value = noticeRepository.getNoticesResultsDb()
        }
    }


    fun saveNoticeDeleted(storyId:String?){
        viewModelScope.launch {
            noticeRepository.insertNoticesDeletedDb(storyId)
        }
    }

    fun savedNoticesOffline(notices:MutableList<NoticesSaved>){
        viewModelScope.launch {
            noticeRepository.insertNoticesDb(notices)
        }
    }

    fun deleteOfflineNotice(){
        viewModelScope.launch {
            noticeRepository.deleteNoticesResultsDb()
        }
    }
}