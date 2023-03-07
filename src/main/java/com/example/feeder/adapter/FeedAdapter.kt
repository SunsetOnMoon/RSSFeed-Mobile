package com.example.feeder.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feeder.NewsActivity
import com.example.feeder.R
import com.example.feeder.databinding.FeedFragmentBinding
import com.example.feeder.model.Item

class FeedAdapter(rssFeed: List<Item>, internal var context: Context) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){
    var rssFeed: List<Item> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }
    init {
        this.rssFeed = rssFeed
    }
    class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageFeed: ImageView = view.findViewById(R.id.feed_image)
        var textTitle: TextView = view.findViewById(R.id.text_title)
        var textPubdate: TextView = view.findViewById(R.id.text_pubdate)
        var textContent: TextView = view.findViewById(R.id.text_content)
        var cardView: CardView = view.findViewById(R.id.cv_feed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.feed_fragment, parent, false)
        return FeedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rssFeed.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = rssFeed[position]

        holder.textContent.text = feed.description
        holder.textPubdate.text = feed.pubDate
        holder.textTitle.text = feed.title
        Glide.with(context).load(feed.thumbnail.url).into(holder.imageFeed)
        val context = holder.itemView.context
        holder.itemView.setOnClickListener{
            val intent = Intent(context, NewsActivity::class.java)
            intent.putExtra("URL", feed.link)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }


}