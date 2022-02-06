package com.example.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
      var arraylist = ArrayList<User>()

      constructor(arr : ArrayList<User>){
            arraylist = arr
      }

      class MyViewHolder : RecyclerView.ViewHolder{
          var txtName : TextView? = null
          var txtId : TextView? = null
          var txtAge : TextView? = null
           constructor(view : View) : super(view){
               txtName = view.findViewById<TextView>(R.id.userName)
               txtId = view.findViewById<TextView>(R.id.userID)
               txtAge = view.findViewById<TextView>(R.id.userAge)
           }
      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.userlist, parent, false)
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         holder.txtName!!.text = "Name : "+arraylist.get(position).name
         holder.txtId!!.text  = "Age : "+arraylist.get(position).age.toString()
         holder.txtAge!!.text  = "Id : "+arraylist.get(position).id.toString()
    }

    override fun getItemCount(): Int = arraylist.size
}