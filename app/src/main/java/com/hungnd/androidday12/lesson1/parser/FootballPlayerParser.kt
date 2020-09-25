package com.hungnd.androidday12.lesson1.parser

import android.util.Log
import com.hungnd.androidday12.lesson1.model.FootballPlayerModel
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
import java.util.*


class FootballPlayerParser {
    private lateinit var dataParserPlayer: ArrayList<FootballPlayerModel>
    fun parserPlayer(link: String): ArrayList<FootballPlayerModel> {
        dataParserPlayer = ArrayList<FootballPlayerModel>()
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
                val playerId: Int = objPlayer.getInt("id")
                val playerName: String = objPlayer.getString("ten")
                val playerTeam: String = objPlayer.getString("doi")
                val playerImage: String = objPlayer.getString("image")
                val playerSocial: String = objPlayer.getString("social")
                val footballModel =
                    FootballPlayerModel(playerId, playerName, playerTeam, playerImage, playerSocial)
                dataParserPlayer.add(footballModel)
            }
        } catch (e: MalformedURLException) {
            Log.e("MalformedURLException", "${e.message}")

        } catch (e: IOException) {
            Log.e("IOException", "${e.message}")


        } catch (e: JSONException) {
            Log.e("JSONException", "${e.message}")


        }
        Log.d("HungND", "size" + dataParserPlayer.size)
        return dataParserPlayer
    }
}