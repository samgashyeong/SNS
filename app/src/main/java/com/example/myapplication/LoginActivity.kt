package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    var firebase : FirebaseAuth? = null
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebase = FirebaseAuth.getInstance()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        binding.signin.setOnClickListener {
            firebase!!.signInWithEmailAndPassword(binding.emailAddrass.text.toString(), binding.password.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                }
                else if(binding.emailAddrass.text.toString().isEmpty()){
                    Toast.makeText(this, "이메일 부분을 제대로 입력해주세요", Toast.LENGTH_SHORT).show()
                }
                else if(binding.password.text.toString().isEmpty()){
                    Toast.makeText(this, "비밀번호 부분을 제대로 입력해주세요", Toast.LENGTH_SHORT).show()
                }
                else if(binding.password.text.toString().isEmpty()  && binding.emailAddrass.text.toString().isEmpty()){
                    Toast.makeText(this, "둘 다 제대로 입력해주세요", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "로그인에 실패하였습니다. 다시시도해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.startRegistarTv.setOnClickListener {
            startActivity(Intent(this, RegistarAcitivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
    }
}