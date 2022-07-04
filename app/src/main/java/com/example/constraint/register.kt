package com.example.constraint

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.constraint.databinding.ActivityMainBinding
import com.example.constraint.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirm.setOnClickListener() {
            signup()
        }

        auth = FirebaseAuth.getInstance()
    }

    fun signup() {

        var email = binding.ETID.text.toString()
        var password = binding.ETPw.text.toString()
        var passwordCheck = binding.ETPwCheck.text.toString()
        if ((email.endsWith("@knu.ac.kr"))) {
            if (email.length > 0 && password.length >= 5) {
                if (password.equals(passwordCheck)) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success")
                                val user = auth.currentUser
                                Toast.makeText(
                                    baseContext, "회원가입에 성공하였습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
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
                } else {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "공란이 있는지 확인해주세요,비밀번호는 10자리 이상만 설정 가능합니다", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            var Toast = Toast.makeText(this, "경북대학교 웹 메일만 사용 가능합니다", Toast.LENGTH_SHORT)
            Toast.show()
        }
    }
}