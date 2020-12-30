package com.example.jstpl_assignment.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jstpl_assignment.R
import com.example.jstpl_assignment.Utility
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val user: FirebaseUser? = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val u = Utility()
        Log.d(u.TAG, "Main Activity")
        startActivity(Intent(applicationContext, Login::class.java))
    }

    override fun onStart() {
        super.onStart()

        if (user == null) {
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }
    }
}