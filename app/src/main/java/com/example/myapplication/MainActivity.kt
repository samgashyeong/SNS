package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn1.setOnClickListener {
            if(binding.nameEt.text.toString().isEmpty()){
                binding.nameEt.error = "이름칸이 비워졌습니다."
                if(binding.ageEt.text.toString().isEmpty())
                    binding.ageEt.error = "나이칸이 비워졌습니다."
            }
            else{
                savefirestore(binding.nameEt.text.toString(), binding.ageEt.text.toString())
            }
        }
    }

    fun savefirestore(name:String, age:String){
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["name"] = binding.nameEt.text.toString()
        user["age"] = binding.ageEt.text.toString()

        db.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "유저가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "오류가 발생하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
        readFireStoreData()
    }

    fun readFireStoreData(){
        val data = arrayListOf<Person>()
        val db = FirebaseFirestore.getInstance()
        db.collection("users").get()
                .addOnCompleteListener {
                    var result: StringBuffer= StringBuffer()
                    if(it.isSuccessful){
                        for(document in  it.result!!){
                            val name:String?;
                            val age:String?;
                            name= result.append(document.data.getValue("name")).append().toString()
                            result.delete(0,result.length)
                            age = result.append(document.data.getValue("age")).append().toString()
                            result.delete(0,result.length)
                            data.add(Person(name,age))
                            println("$name $age")
                        }
                    }
                    binding.recycler.apply{
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = PersonAdapter(data)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "데이터를 불러오는데에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
    }
}