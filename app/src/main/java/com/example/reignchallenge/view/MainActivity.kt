package com.example.reignchallenge.view

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.reignchallenge.R
import com.example.reignchallenge.model.NoticesSaved
import com.example.reignchallenge.utils.ConnectivityHelper
import com.example.reignchallenge.utils.TimeAgo
import com.example.reignchallenge.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var adapterNotice: NoticeListAdapter
    private var noticeList : MutableList<NoticesSaved> = arrayListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkConnectivity()
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            checkConnectivity()
        })


        adapterNotice = NoticeListAdapter(this, noticeList)

        findViewById<RecyclerView>(R.id.noticesAll).apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager (context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterNotice
            adapterNotice.onItemClick = {
                    notice ->
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("notice", notice)
                startActivity(intent)

            }

        }

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = noticesAll.adapter as NoticeListAdapter
                val notice = adapter.getItem(viewHolder.adapterPosition)
                viewModel.saveNoticeDeleted(notice.story_id)
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(noticesAll)

        viewModel.notice.observe(this, Observer {
            noticeList = it.hits
            swipeRefreshLayout.isRefreshing = false
            val noticesSaved = noticeList
            viewModel.deleteOfflineNotice()
            viewModel.savedNoticesOffline(noticesSaved)
            viewModel.getDeletedNotices()
        })

        viewModel.noticeOffline.observe(this, Observer {
            noticeList = it as MutableList<NoticesSaved>
            swipeRefreshLayout.isRefreshing = false
            viewModel.getDeletedNotices()
        })


        viewModel.noticeDeleted.observe(this, Observer {
            val notices = noticeList.filterNot {s -> it.any { it.storyId == s.story_id }  } as MutableList<NoticesSaved>
            adapterNotice.refreshList(notices)
        })

    }

    private fun checkConnectivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (ConnectivityHelper.getConnectionType(this)) {
                NetworkCapabilities.TRANSPORT_WIFI -> {
                    viewModel.getAllNotices()

                }
                NetworkCapabilities.TRANSPORT_CELLULAR -> {
                    viewModel.getAllNotices()
                }

                else -> {
                    viewModel.getAllNoticesOffline()
                }
            }
        } else {
            when (ConnectivityHelper.getConnectionTypeSDK21(this)) {
                ConnectivityManager.TYPE_WIFI -> {
                    viewModel.getAllNotices()
                }
                0 -> {
                    viewModel.getAllNotices()
                }

                else -> {
                    viewModel.getAllNoticesOffline()
                }
            }
        }
    }
}