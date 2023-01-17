package com.example.goodtv

data class Movie(
    var Id: String="",
    var Name : String ?="",
    var Poster : String?="",
    var Rating: Float?=null,
    var Synopsis: String?="",
    var Watched : Boolean?=null,
    var ToWatch : Boolean?=null,
)
