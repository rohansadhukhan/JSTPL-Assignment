package com.example.jstpl_assignment.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.jstpl_assignment.R
import com.example.jstpl_assignment.Utility
import com.example.jstpl_assignment.fragment.LoginCredential
import com.example.jstpl_assignment.fragment.LoginVerification
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class Login : AppCompatActivity(), LoginCredential.OnRequestOTP, LoginVerification.OnVerifyOTP {

    private lateinit var loginCredential: LoginCredential
    private lateinit var loginVerification: LoginVerification
    private lateinit var code: String

    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val u = Utility()
        Log.d(u.TAG, "Login Activity")

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.login_fragment_container, LoginCredential())
        transaction.commit()
    }

    private fun verificationCallBacks() {
        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
//                TODO("Not yet implemented")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
//                TODO("Not yet implemented")
                Log.d("rohan", p0.message)
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                Log.d("rohan", p0)
            }
        }
    }

    override fun onRequestOTP(number: String) {
        verificationCallBacks()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$number",
            60,
            TimeUnit.SECONDS,
            this,
            callback
        )

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.login_fragment_container, LoginVerification(number))
        transaction.commit()
    }

    override fun onVerifyOTP(otp: String) {
//        TODO("Not yet implemented")
        Toast.makeText(applicationContext, "Done", Toast.LENGTH_SHORT).show()

        var credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(code, otp)
//        signInWithPhoneNumber(credential)
    }

//    private fun signInWithPhoneNumber(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this, OnCompleteListener {
//                if(it.isSuccessful) {
//                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(applicationContext, MainActivity::class.java))
//                    finish()
//                }
//            }) }
//    }
}