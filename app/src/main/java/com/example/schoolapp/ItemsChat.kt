package com.example.schoolapp

class ItemsChat {
    var userId: Int? = null
    var tempMes: String? = null
    var  unReadMes: String?
    var lastMesTime: String? = null

    constructor(id: Int, tempMes: String, numOfUnread: String?, lastTime:String){
        this.userId = id
        this.tempMes = tempMes
        this.unReadMes = numOfUnread
        this.lastMesTime = lastTime
    }
}