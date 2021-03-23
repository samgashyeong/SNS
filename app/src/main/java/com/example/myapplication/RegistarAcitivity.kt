package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityRegistarAcitivityBinding
import com.google.firebase.auth.FirebaseAuth

class RegistarAcitivity : AppCompatActivity() {
    var firebase : FirebaseAuth? = null
    private lateinit var binding: ActivityRegistarAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar_acitivity)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registar_acitivity)
        firebase = FirebaseAuth.getInstance()


        binding.btn1.setOnClickListener {
            firebase!!.createUserWithEmailAndPassword(
                binding.emailAddrassEt.text.toString(),
                binding.passwordEt.text.toString(),
            ).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                    overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                }
                else if(binding.emailAddrassEt.text.toString().isEmpty()){
                    Toast.makeText(this, "제대로 다시입력", Toast.LENGTH_SHORT).show()
                }
                else if(binding.passwordEt.text.toString().isEmpty()){
                    Toast.makeText(this, "제대로 다시입력", Toast.LENGTH_SHORT).show()
                }
                else if(binding.emailAddrassEt.text.toString().isEmpty() && binding.passwordEt.text.toString().isEmpty()){
                    Toast.makeText(this, "제대로 다시입력!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "다시시도", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}