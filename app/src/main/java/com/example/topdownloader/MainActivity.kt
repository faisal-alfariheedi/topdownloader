package com.example.topdownloader

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var fbut: Button
    var entr = arrayListOf<Entry>()
    var base="10"
    lateinit var cl : ConstraintLayout
    lateinit var ad : AnimationDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        fbut.setOnClickListener{
            refresh()
        }

    }
    fun init() {
        rv = findViewById(R.id.rvm)
        rv.adapter=RVAdapter(entr,this)
        rv.layoutManager= LinearLayoutManager(this)
        fbut = findViewById(R.id.feed)
        cl =findViewById(R.id.main)
        ad =cl.background as AnimationDrawable
        ad.setEnterFadeDuration(1000)
        ad.setExitFadeDuration(2000)
        ad.start()
    }


    fun refresh() {
        entr.clear()
        CoroutineScope(Dispatchers.IO).launch {
            var apif =client().getClient(base)?.create(API::class.java)
            apif!!.getfeed!!.enqueue(object : Callback<Feed?> {
                override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                    Log.d("feed", "onResponse: feed: " + response.body().toString())
                    Log.d("response", "onResponse: Server Response: $response")
                    val entries = response.body()!!.entrys
                    for (entry in entries!!) {
                        entr.add(entry)
                    }
                    rv.adapter?.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "done refreshing", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Feed?>, t: Throwable) {
                    Log.d("failurerere", "$t")
                    Toast.makeText(
                        this@MainActivity,
                        "An Error Occured",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }


    /////////////////////menu////////////////////////////

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.men, menu)
        return true
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item1 = menu!!.getItem(0)
        return super.onPrepareOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.m1 -> {
                base="10"
                refresh()

                return true
            }
            R.id.m2 -> {
                base="50"
                refresh()

                return true
            }
            R.id.m3 -> {
                base="100"
                refresh()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}