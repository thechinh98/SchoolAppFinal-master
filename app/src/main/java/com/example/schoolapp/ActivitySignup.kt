package com.example.schoolapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*

class ActivitySignup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var intent = Intent(this, ActivitySignin :: class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        btn_signin.setOnClickListener{
            startActivity(intent)
        }

        btn_signup.setOnClickListener{
            val emailView = findViewById<TextView>(R.id.etxt_email_signup)
            val passView = findViewById<TextView>(R.id.etxt_password_signin)

            var emailNew : String = emailView.text.toString()
            var passNew : String = passView.text.toString()
            if(emailNew != ""&& passNew != "") {
                intent.putExtra("new_email", emailNew)
                intent.putExtra("new_password", passNew)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please input correct information", Toast.LENGTH_LONG).show()
            }
        }
    }

}
