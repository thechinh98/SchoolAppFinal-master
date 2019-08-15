package com.example.schoolapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signin.*

class ActivitySignin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        var signUpIntent = Intent(this, ActivitySignup:: class.java)
        var tempIntent = getIntent()
        val intent = Intent(this, ActivityNewFeed::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        var emailInput = findViewById<EditText>(R.id.etxt_email_signin)
        val passwordInput = findViewById<EditText>(R.id.etxt_password_signin)

        var tempEmail: String? = tempIntent.getStringExtra("new_email")
        var tempPassword: String? = tempIntent.getStringExtra("new_password")
        btn_signin.setOnClickListener{
            var emailLogIn = emailInput.text.toString()
            var passwordLogin = passwordInput.text.toString()
            if(emailLogIn.equals(tempEmail) && passwordLogin.equals(tempPassword)) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show()
            }
        }
        if(tempEmail != ""){
            emailInput.setText(tempEmail)
            passwordInput.setText(tempPassword)
        }
        btn_signup.setOnClickListener {
            startActivity(signUpIntent)
        }
        txt_fgt_pass.setOnClickListener{
            startActivity(browserIntent)
        }


    }
}
