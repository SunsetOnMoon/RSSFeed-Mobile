package com.example.feeder

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.feeder.adapter.FeedAdapter
import com.example.feeder.databinding.ActivityMainBinding
import com.example.feeder.model.Item
import com.example.feeder.model.RssParser
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: FeedAdapter
    lateinit var rssFeed: List<Item>
    lateinit var rssParser: RssParser
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initial()
        fetchList()
    }

    private fun initial()
    {
        rssParser = RssParser(applicationContext)
        rssFeed = emptyList()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchList() {
        try {
            rssFeed = rssParser.getRssFeed()
        }
        catch (t: Throwable)
        {
            Toast.makeText(this, "Error in reading XML$t", Toast.LENGTH_LONG)
                .show()
        }
        recyclerView = binding.rvFeed
        adapter = FeedAdapter(rssFeed, applicationContext)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}