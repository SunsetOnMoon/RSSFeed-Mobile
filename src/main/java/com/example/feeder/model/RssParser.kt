package com.example.feeder.model
import android.annotation.SuppressLint
import android.content.Context
import com.example.feeder.R
import org.xmlpull.v1.XmlPullParser
class RssParser(context: Context) {
    private lateinit var parser: XmlPullParser
    init {
        parser = context.resources.getXml(R.xml.feed)
    }
    @SuppressLint("Range")
    fun getRssFeed(): List<Item> {
        val rssFeed: ArrayList<Item> = ArrayList<Item>()
        while (parser.eventType != XmlPullParser.END_DOCUMENT) {
            val item: Item = Item()
            if (parser.eventType == XmlPullParser.START_TAG
                && parser.name.equals("item")) {
                do {
                    parser.next()
                    /*when (parser.name) {
                        "title" -> item.title = parser.text
                        "link" -> item.link = parser.text
                        "pubDate" -> item.pubDate = parser.text
                        "description" -> item.description = parser.text
                        "media:thumbnail" -> item.thumbnail.url = parser.getAttributeValue(0)
                    }*/
                    if (parser.name == "title") {
                        parser.next()
                        item.title = parser.text
                        parser.next()
                    }
                    else if (parser.name == "link")
                    {
                        parser.next()
                        item.link = parser.text
                        parser.next()
                    }
                    else if (parser.name == "pubDate")
                    {
                        parser.next()
                        item.pubDate = parser.text
                        parser.next()
                    }
                    else if (parser.name == "description")
                    {
                        parser.next()
                        item.description = parser.text
                        parser.next()
                    }
                    else if (parser.name == "thumbnail")
                    {
                        //parser.next()
                        item.thumbnail.url = parser.getAttributeValue(0)
                        //item.thumbnail.url = item.thumbnail.url.split("?")[0]
                        parser.next()
                    }
                    /*item.title = parser.getAttributeValue(0)
                            item . link = parser . getAttributeValue (1)
                            item . pubDate = parser . getAttributeValue (2)
                            item . description = parser . getAttributeValue (3)
                            item . thumbnail . url = parser . getAttributeValue ("media:thumbnail", "url")*/
                } while (parser.eventType != XmlPullParser.END_TAG || !parser.name.equals("item"))
                rssFeed.add(item)
            }
            parser.next()
        }
        return rssFeed
    }
}