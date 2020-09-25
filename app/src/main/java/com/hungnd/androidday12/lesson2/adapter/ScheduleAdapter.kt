package com.hungnd.androidday12.lesson2.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hungnd.androidday12.R
import com.hungnd.androidday12.lesson2.model.ScheduleSoccerModel
import java.util.ArrayList

class ScheduleAdapter(var dataSchedule: ArrayList<ScheduleSoccerModel>) :
    RecyclerView.Adapter<ScheduleAdapter.HungHolder>() {
    inner class
    HungHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imvAvtTeam1 = view.findViewById<ImageView>(R.id.imv_Avt_team1)
        val imvAvtTeam2 = view.findViewById<ImageView>(R.id.imv_Avt_Team2)
        val tvDate = view.findViewById<TextView>(R.id.tv_Date)
        val tvNameTeam1 = view.findViewById<TextView>(R.id.tv_Team1_Name)
        val tvNameTeam2 = view.findViewById<TextView>(R.id.tv_Team2_Name)
        val tvTime = view.findViewById<TextView>(R.id.tv_Time)
        val tvRound = view.findViewById<TextView>(R.id.tv_Round)
        val tvChannel = view.findViewById<TextView>(R.id.tv_Channel)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HungHolder {
        return HungHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_rcv_list_schedule, parent, false)
        )
    }

    fun setData(dataSchedules: ArrayList<ScheduleSoccerModel>) {
        this.dataSchedule = dataSchedules
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HungHolder, position: Int) {
        val position = dataSchedule[position]
        Glide.with(holder.imvAvtTeam1).load(position.ensign1).placeholder(R.mipmap.ic_launcher)
            .into(holder.imvAvtTeam1)
        Glide.with(holder.imvAvtTeam2).load(position.ensign2)
            .placeholder(R.mipmap.ic_launcher_round).into(holder.imvAvtTeam2)
        holder.tvDate.text = position.date
        holder.tvTime.text = position.time
        holder.tvChannel.text = position.channel
        holder.tvRound.text = position.round
        holder.tvNameTeam1.text = position.team1
        holder.tvNameTeam2.text = position.team2

    }

    override fun getItemCount(): Int {

        return dataSchedule.size
    }
}
