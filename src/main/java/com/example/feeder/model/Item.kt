package com.example.feeder.model

import java.util.*

data class Item(
    var title: String = "",
    var link: String = "",
    var pubDate: String = "",
    var description: String = "",
    var thumbnail: Thumbnail = Thumbnail("")
    )