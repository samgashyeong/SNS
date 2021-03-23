package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class  PersonAdapter(val DataList:ArrayList<Person>): RecyclerView.Adapter<PersonAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //ex)val 변수명 = itemView.findViewById<xml이름>(아이디네임)
        val name = itemView.findViewById<TextView>(R.id.nameTv)
        val age = itemView.findViewById<TextView>(R.id.ageTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //ex)holder.(홀더클래스변수).text = DataList[position].name
        holder.name.text = DataList[position].name
        holder.age.text = DataList[position].age
    }
    override fun getItemCount() = DataList.size

}