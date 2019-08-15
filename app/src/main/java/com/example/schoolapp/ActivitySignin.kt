package com.example.schoolapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signin.*

class ActivitySignin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var tempIntent = getIntent()
        val intent = Intent(this, ActivityNewFeed::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        var emailInput = findViewById<EditText>(R.id.etxt_email_signin)
        val passwordInput = findViewById<EditText>(R.id.etxt_password_signin)

        var tempEmail: String? = tempIntent.getStringExtra("new_email")
        var tempPassWord: String? = tempIntent.getStringExtra("new_password")
        btn_signin.setOnClickListener{
            startActivity(intent)
        }
        if(tempEmail != ""){
            emailInput.setText(tempEmail)
            passwordInput.setText(tempPassWord)
        }
        btn_signup.setOnClickListener {
            startActivity(intent)
        }
    }
}
