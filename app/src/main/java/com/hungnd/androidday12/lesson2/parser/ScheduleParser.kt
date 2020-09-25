package com.hungnd.androidday12.lesson2.parser

import android.util.Log
import com.hungnd.androidday12.lesson1.model.FootballPlayerModel
import com.hungnd.androidday12.lesson2.model.ScheduleSoccerModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

class ScheduleParser {
    private lateinit var dataParserSchedule: ArrayList<ScheduleSoccerModel>
    fun parserSchedule(link: String): ArrayList<ScheduleSoccerModel> {
        dataParserSchedule = ArrayList<ScheduleSoccerModel>()
        try {
            val url = URL(link)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStream: InputStream = urlConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line = bufferedReader.readLine()
            val result = StringBuilder()
            while (line != null) {
                result.append(line)
                line = bufferedReader.readLine()
            }
            val jsonArr = JSONArray(result.toString())
            for (i in 0 until jsonArr.length()) {
                val objPlayer: JSONObject = jsonArr.getJSONObject(i)
                val scheduleId: Int = objPlayer.getInt("id")
                val team1: String = objPlayer.getString("doi1")
                val ensign1: String = objPlayer.getString("quocky1")
                val team2: String = objPlayer.getString("doi2")
                val ensign2: String = objPlayer.getString("quocky2")
                val date: String = objPlayer.getString("ngay")
                val time: String = objPlayer.getString("gio")
                val round: String = objPlayer.getString("vong")
                val channel: String = objPlayer.getString("kenh")
                val scheduleModel =
                    ScheduleSoccerModel(
                        scheduleId,
                        team1,
                        ensign1,
                        team2,
                        ensign2,
                        date,
                        time,
                        round,
                        channel
                    )
                dataParserSchedule.add(scheduleModel)
            }
        } catch (e: MalformedURLException) {
            Log.e("MalformedURLException", "${e.message}")

        } catch (e: IOException) {
            Log.e("IOException", "${e.message}")


        } catch (e: JSONException) {
            Log.e("JSONException", "${e.message}")


        }

        return dataParserSchedule
    }

}