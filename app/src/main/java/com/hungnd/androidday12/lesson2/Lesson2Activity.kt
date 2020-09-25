package com.hungnd.androidday12.lesson2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hungnd.androidday12.R
import com.hungnd.androidday12.lesson1.adapter.FootballPlayerAdapter
import com.hungnd.androidday12.lesson1.model.FootballPlayerModel
import com.hungnd.androidday12.lesson1.parser.FootballPlayerParser
import com.hungnd.androidday12.lesson2.adapter.ScheduleAdapter
import com.hungnd.androidday12.lesson2.model.ScheduleSoccerModel
import com.hungnd.androidday12.lesson2.parser.ScheduleParser
import kotlinx.android.synthetic.main.activity_lesson1.*
import kotlinx.android.synthetic.main.activity_lesson2.*
import java.util.ArrayList

class Lesson2Activity : AppCompatActivity() {
    private var scheduleParser: ScheduleParser? = null
    private val dataSchedule = ArrayList<ScheduleSoccerModel>()
    private var scheduleAdapter: ScheduleAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson2)
        rcv_Schedule.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        scheduleAdapter = ScheduleAdapter(dataSchedule)
        rcv_Schedule.adapter = scheduleAdapter
        LoadSchedule(object : SetData {
            override fun dataResult(result: ArrayList<ScheduleSoccerModel>?) {
                if (scheduleAdapter != null) {
                    scheduleAdapter!!.setData(result!!)
                }
            }

        }).execute("https://obscure-gorge-93598.herokuapp.com/lichthidau/all")
    }

    class LoadSchedule(val cb: SetData) :
        AsyncTask<String, Void, ArrayList<ScheduleSoccerModel>>() {
        override fun doInBackground(vararg p0: String?): ArrayList<ScheduleSoccerModel>? {
            val link: String? = p0[0]
            val parser = ScheduleParser()
            return link?.let { parser.parserSchedule(it) }
        }

        override fun onPostExecute(result: ArrayList<ScheduleSoccerModel>?) {
            super.onPostExecute(result)
            cb.dataResult(result)
        }
    }

    interface SetData {
        fun dataResult(result: ArrayList<ScheduleSoccerModel>?)
    }
}