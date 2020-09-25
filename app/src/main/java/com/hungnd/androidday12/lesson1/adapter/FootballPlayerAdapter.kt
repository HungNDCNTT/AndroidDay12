package com.hungnd.androidday12.lesson1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hungnd.androidday12.R
import com.hungnd.androidday12.lesson1.model.FootballPlayerModel
import java.util.ArrayList

class FootballPlayerAdapter(var dataPlayer: ArrayList<FootballPlayerModel>) :
    RecyclerView.Adapter<FootballPlayerAdapter.HungHolder>() {
    inner class
    HungHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imvPlayerAvt = view.findViewById<ImageView>(R.id.circle_Player_Avt)
        val tvPlayerName = view.findViewById<TextView>(R.id.tv_PlayerName)
        val tvPlayerTeam = view.findViewById<TextView>(R.id.tv_PlayerTeam)
        val tvPlayerSocial = view.findViewById<TextView>(R.id.tv_PlayerSocial)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HungHolder {
        return HungHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_rcv_list_player, parent, false)
        )
    }

    fun setData( dataPlayer: ArrayList<FootballPlayerModel>){
        this.dataPlayer = dataPlayer
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: HungHolder, position: Int) {
        val position = dataPlayer[position]
        holder.tvPlayerName.text = position.playerName
        holder.tvPlayerTeam.text = position.playerTeam
        holder.tvPlayerSocial.text = position.playerSocial
        Glide
            .with(holder.imvPlayerAvt)
            .load(position.playerImage)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imvPlayerAvt);
    }

    override fun getItemCount(): Int {
        Log.d("HungND", "sizeAdapter" + dataPlayer.size)
        return dataPlayer.size
    }
}