package com.example.schoolapp

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.item_chat.view.*

class ActivityChat : AppCompatActivity() {
    private lateinit var recycleView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        var intent = Intent(this, ActivityNewFeed :: class.java)
        var userDataset : ArrayList<ItemsUserData> = createDummyUserData()
        var chatDataset : ArrayList<ItemsChat> = createDummyChatData()
        var comeIntent = getIntent()

        var tempUserId = comeIntent.getIntExtra("USER_ID", 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        var tempChatIndex = getIndexById(tempUserId, chatDataset)
        var tempChatData : ItemsChat = chatDataset.get(tempChatIndex)
        chatDataset.removeAt(tempChatIndex)

        chatDataset.add(0, tempChatData)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(chatDataset, userDataset)
        rcc_chat.layoutManager = viewManager
        rcc_chat.adapter = viewAdapter

        img_home.setOnClickListener{
            startActivity(intent)
        }
    }

    class MyAdapter(
        private val myMesDataset: ArrayList<ItemsChat>,
        private val myUserDataSet: ArrayList<ItemsUserData>
    ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return myMesDataset.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            var tempUserId = myMesDataset[position].userId
            holder.initView(myUserDataSet[tempUserId!!], myMesDataset[position])
        }

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(Build.VERSION_CODES.M)
            fun initView(
                itemsUserData: ItemsUserData,
                itemChatData: ItemsChat
            ) {
                view.img_profile.setImageResource(itemsUserData.imageProfileResource!!)
                view.txt_username.text = itemsUserData.username
                view.txt_mes.text = itemChatData.tempMes
                view.txt_unread_mes.text = itemChatData.unReadMes
                view.txt_last_mes.text = itemChatData.lastMesTime
                if(itemChatData.unReadMes == null){
                    view.txt_username.setTextAppearance(R.style.fontNoNewMes)
                    view.txt_unread_mes.setBackgroundResource(R.drawable.oval_no_unread_mes)
                } else {
                    view.txt_username.setTextAppearance(R.style.fontNewMes)
                    view.txt_unread_mes.setBackgroundResource(R.drawable.oval_unread_mes)
                }
            }
        }

    }

    fun createDummyUserData(): ArrayList<ItemsUserData> {
        var userList = ArrayList<ItemsUserData>()
        userList.add(ItemsUserData(0, R.drawable.profile, "Alex Pro"))
        userList.add(ItemsUserData(1, R.drawable.profile, "Cris Devil"))
        userList.add(ItemsUserData(2, R.drawable.profile, "Pew pew"))
        userList.add(ItemsUserData(3, R.drawable.profile, "Mixi Gaming"))
        userList.add(ItemsUserData(4, R.drawable.profile, "Xemesis"))
        userList.add(ItemsUserData(5, R.drawable.profile, "Viruss"))
        userList.add(ItemsUserData(6, R.drawable.profile, "Misthy"))
        return userList
    }

    fun createDummyChatData(): ArrayList<ItemsChat> {
        var chatList = ArrayList<ItemsChat>()
        chatList.add(ItemsChat(0, "Shall we meet today?", "1", "5 : 45 PM"))
        chatList.add(ItemsChat(1, "Hahahaha… \uD83D\uDE02", "2", "11 : 12 AM"))
        chatList.add(ItemsChat(2,"Sounds perfect to me. 😎", null, "10 : 45 AM"))
        chatList.add(ItemsChat(3,"Sounds perfect to me. 😎", null, "10 : 45 AM"))
        chatList.add(ItemsChat(4,"Sounds perfect to me. 😎", null, "10 : 45 AM"))
        chatList.add(ItemsChat(5,"Sounds perfect to me. 😎", null, "10 : 45 AM"))
        return chatList
    }
    fun getIndexById(tempId : Int, myChatDataset: ArrayList<ItemsChat>): Int{
        var index : Int = 0
        for(item : ItemsChat in myChatDataset){
            if(item.userId == tempId){
                return index
            } else {
                index++
            }
        }
        return 0
    }

}
