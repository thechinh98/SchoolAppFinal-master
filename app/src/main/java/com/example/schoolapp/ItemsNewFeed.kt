package com.example.schoolapp

class ItemsNewFeed {
    var userId: Int
    var postTime : String? =null
    lateinit var content : String
    var imageContentResource : Int? = null
    lateinit var price : String
    var checkHeart : Boolean? = false

    constructor(id: Int, postTime : String, content: String, imageContentResource : Int,
                price : String, checkHeart : Boolean){
        this.imageContentResource = imageContentResource
        this.userId = id
        this.postTime = postTime
        this.content = content
        this.price = price
        this.checkHeart = checkHeart
    }
}