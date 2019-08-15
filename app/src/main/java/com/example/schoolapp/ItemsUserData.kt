package com.example.schoolapp

class ItemsUserData {
    var userId: Int? = null
    var imageProfileResource : Int? = null
    var username : String? = null

    constructor(id :Int, image: Int?, username: String){
        this.userId = id
        this.username = username
        this.imageProfileResource = image

    }
}