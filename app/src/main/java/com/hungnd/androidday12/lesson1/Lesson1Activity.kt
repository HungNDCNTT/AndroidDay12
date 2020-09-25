package com.hungnd.androidday12.lesson1

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hungnd.androidday12.R
import com.hungnd.androidday12.lesson1.adapter.FootballPlayerAdapter
import com.hungnd.androidday12.lesson1.model.FootballPlayerModel
import com.hungnd.androidday12.lesson1.parser.FootballPlayerParser
import kotlinx.android.synthetic.main.activity_lesson1.*
import java.util.*


class Lesson1Activity : AppCompatActivity() {
    private var footballParser: FootballPlayerParser? = null
    private val dataPlayer = ArrayList<FootballPlayerModel>()
    private var footballPlayerAdapter: FootballPlayerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson1)
        rcv_List_FootballPlayer.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        footballPlayerAdapter = FootballPlayerAdapter(dataPlayer)
        rcv_List_FootballPlayer.adapter = footballPlayerAdapter
        LoadPlayer(object : SetData{
            override fun dataResult(result: ArrayList<FootballPlayerModel>?) {
                if (footballPlayerAdapter != null){
                    footballPlayerAdapter!!.setData(result!!)
                }
            }

        }).execute("https://obscure-gorge-93598.herokuapp.com/cauthu/all")
    }

    class LoadPlayer(val cb : SetData) : AsyncTask<String, Void, ArrayList<FootballPlayerModel>>() {
        override fun doInBackground(vararg p0: String?): ArrayList<FootballPlayerModel>? {
            val link: String? = p0[0]
            val parser = FootballPlayerParser()
            return link?.let { parser.parserPlayer(it) }
        }

        override fun onPostExecute(result: ArrayList<FootballPlayerModel>?) {
            super.onPostExecute(result)
            cb.dataResult(result)
        }
    }

    interface SetData{
        fun dataResult(result: ArrayList<FootballPlayerModel>?)
    }
}