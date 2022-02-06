package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var arr = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            var id   = editTextTextPersonName.text.toString().trim()
            var name = editTextTextPersonName2.text.toString().trim()
            var age  = editTextTextPersonName3.text.toString().trim()

            if( id.isEmpty() || name.isEmpty() || age.isEmpty() ){
                Toast.makeText(this, "Please fill missing fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var user = User(name, age.toInt())
            UserDatabase(this).getDao().addUser(user)
            Toast.makeText(this, "Successfully Added.", Toast.LENGTH_SHORT).show()
            editTextTextPersonName.text.clear()
            editTextTextPersonName2.text.clear()
            editTextTextPersonName3.text.clear()
            arr = ArrayList(UserDatabase(this).getDao().getUsers())
            setAdapter()
        }

        getBtn.setOnClickListener(){
            arr = ArrayList(UserDatabase(this).getDao().getUsers())
            setAdapter()
        }

        Dlt.setOnClickListener(){
            var id   = editTextTextPersonName.text.toString().trim()
            var name = editTextTextPersonName2.text.toString().trim()
            var age  = editTextTextPersonName3.text.toString().trim()

            if( name.isEmpty() || age.isEmpty() ){
                Toast.makeText(this, "Please fill missing fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var user = User( name, age.toInt())
            user.id = id.toInt()
            UserDatabase(this).getDao().deleteUser(user)
            Toast.makeText(this, "Successfully Deleted.", Toast.LENGTH_SHORT).show()
            editTextTextPersonName.text.clear()
            editTextTextPersonName2.text.clear()
            editTextTextPersonName3.text.clear()
            arr = ArrayList(UserDatabase(this).getDao().getUsers())
            setAdapter()
        }

        update.setOnClickListener(){
            var id   = editTextTextPersonName.text.toString().trim()
            var name = editTextTextPersonName2.text.toString().trim()
            if( name.isEmpty() || id.isEmpty() ){
                Toast.makeText(this, "Please fill missing fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            UserDatabase(this).getDao().update(id.toInt(), name)
            Toast.makeText(this, "Successfully Update", Toast.LENGTH_SHORT).show()
            arr = ArrayList(UserDatabase(this).getDao().getUsers())
            setAdapter()
        }

        dltUser.setOnClickListener(){
            UserDatabase(this).getDao().DeleteAll()
            Toast.makeText(this, "Seccessfully Deleted", Toast.LENGTH_SHORT)
            arr = ArrayList(UserDatabase(this).getDao().getUsers())
            setAdapter()
        }
    }

    private fun setAdapter(){
        var recycler = RecyclerAdapter(arr)
        var layout = LinearLayoutManager(applicationContext)
        recyclerView1.layoutManager = layout
        recyclerView1.adapter = recycler
        recyclerView1.itemAnimator = DefaultItemAnimator()
    }
}