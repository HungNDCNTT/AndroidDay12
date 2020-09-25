package com.hungnd.androidday12.lesson2.model

data class ScheduleSoccerModel(
    val scheduleId: Int,
    val team1: String,
    val ensign1: String,
    val team2: String,
    val ensign2: String,
    val date: String,
    val time: String,
    val round: String,
    val channel: String
) {
}