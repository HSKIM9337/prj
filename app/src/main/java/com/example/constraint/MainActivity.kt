package com.example.constraint

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.example.constraint.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // add this

        auth = FirebaseAuth.getInstance()


           binding.loginbtn.setOnClickListener() {
            login()
        }
        binding.textView.setOnClickListener() {
            val intent = Intent(this, register::class.java)
            startActivity(intent);
        }


    }

    fun login() {
        var email = binding.ETID.text.toString()
        var password = binding.ETPWD.text.toString()
        if (email.length < 1 || password.length < 1) {
            var toast = Toast.makeText(this, "빈칸이 존재합니다.", Toast.LENGTH_SHORT)
            toast.show()

        } else {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this, register::class.java)
                        startActivity(intent);
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }
                }
        }
    }


}