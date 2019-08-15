package com.example.schoolapp

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Space
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_new_feed.*
import kotlinx.android.synthetic.main.item_new_feed.view.*

class ActivityNewFeed : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        var  intent = Intent(this, ActivityChat :: class.java)
        var feedList :ArrayList<ItemsNewFeed> = createDummyFeedData()
        var userList :ArrayList<ItemsUserData> = createDummyUserData()
        var spacing = SpaceItemDecoration(12)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_feed)
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(feedList,userList)
        rcc_new_feed.layoutManager = viewManager
        rcc_new_feed.adapter = viewAdapter
        rcc_new_feed.addItemDecoration(spacing)
        viewAdapter.callBack = object : ItemClickHandler {
            override fun onItemClicked(tempUserId: Int) {
                intent.putExtra("USER_ID",tempUserId)
                startActivity(intent)
            }

        }
        img_chat.setOnClickListener{
            startActivity(intent)
        }
    }

    class MyAdapter(private val myFeedDataset : ArrayList<ItemsNewFeed>, private val myUserDataset: ArrayList<ItemsUserData>) :
            RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
        var callBack: ItemClickHandler? = null
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_feed, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return myFeedDataset.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            var tempUserId = myFeedDataset[position].userId
            holder.initView(myFeedDataset[position], myUserDataset[tempUserId], callBack)
        }


        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            fun initView(itemsNewFeed: ItemsNewFeed, itemsUserData: ItemsUserData, callBack: ItemClickHandler?){
                var tempUserId = itemsNewFeed.userId
                view.img_profile.setImageResource(itemsUserData.imageProfileResource!!)
                view.txt_username.text = itemsUserData.username
                view.txt_info.text = itemsNewFeed.postTime
                view.txt_status.text = itemsNewFeed.content
                view.img_status.setImageResource(itemsNewFeed.imageContentResource!!)
                view.txt_price.text = itemsNewFeed.price
                view.checkBox.setChecked(itemsNewFeed.checkHeart!!)
                view.setOnClickListener{
                    callBack?.onItemClicked(tempUserId)
                }

            }
        }

    }
    class SpaceItemDecoration : RecyclerView.ItemDecoration{
        var spaceVertical: Int
        constructor(spaceVer: Int){
            this.spaceVertical = spaceVer
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = spaceVertical
            if(parent.getChildLayoutPosition(view) == 0){
                outRect.top = spaceVertical
            } else {
                outRect.top == 0
            }
        }
    }
    fun createDummyUserData() : ArrayList<ItemsUserData>{
        var userList = ArrayList<ItemsUserData>()
        userList.add(ItemsUserData(0,R.drawable.profile, "Alex Pro"))
        userList.add(ItemsUserData(1,R.drawable.profile, "Cris Devil"))
        userList.add(ItemsUserData(2,R.drawable.profile, "Pew pew"))
        userList.add(ItemsUserData(3,R.drawable.profile, "Mixi Gaming"))
        userList.add(ItemsUserData(4,R.drawable.profile, "Xemesis"))
        userList.add(ItemsUserData(5,R.drawable.profile, "Viruss"))
        userList.add(ItemsUserData(6,R.drawable.profile, "Misthy"))
        return userList
    }
    fun createDummyFeedData() : ArrayList<ItemsNewFeed>{
        var feedList = ArrayList<ItemsNewFeed>()
        feedList.add(ItemsNewFeed(0, "Today, 03:24 PM", "What is the loop of Creation? How is there something from nothing? In spite of the fact that it is impossible to prove that anythin….", R.drawable.rectangle_copy,"$340.00",false))
        feedList.add(ItemsNewFeed(1,"Yesterday","I am looking for a chilled out roommate for a house on 17th floor of a XYZ appartment.", 0, "$290.00", true))
        feedList.add(ItemsNewFeed(2,"Yesterday","I am looking for a chilled out roommate for a house on 17th floor of a XYZ appartment.", 0, "$290.00", true))
        feedList.add(ItemsNewFeed(3,"Yesterday","I am looking for a chilled out roommate for a house on 17th floor of a XYZ appartment.", 0, "$290.00", true))
        feedList.add(ItemsNewFeed(4,"Yesterday","I am looking for a chilled out roommate for a house on 17th floor of a XYZ appartment.", 0, "$290.00", true))

        return feedList
    }

    interface ItemClickHandler{
        fun onItemClicked(tempUserId: Int)
    }

}
