package `in`.com.moengagetask

import android.net.Uri


class Model{
    lateinit var author:String
    lateinit var title:String
    lateinit var description:String
    lateinit var date:String
    lateinit var url:Uri
    lateinit var urlToImage:Uri

    constructor(author: String, title: String, description:String, url: Uri, urlToImage: Uri, date: String) {
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.date = date
        this.urlToImage = urlToImage
    }

    constructor()
}