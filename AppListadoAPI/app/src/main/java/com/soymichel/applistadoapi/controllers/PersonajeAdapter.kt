package com.soymichel.applistadoapi.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soymichel.applistadoapi.R
import com.soymichel.applistadoapi.models.Personaje
import kotlinx.android.synthetic.main.card_item.view.*
import com.soymichel.applistadoapi.views.fromUrl
import com.squareup.picasso.Picasso

class PersonajeAdapter(var dataList : ArrayList<Personaje>) : RecyclerView.Adapter<PersonajeAdapter.ViewHolder>() {

    class ViewHolder (personajeView: View) : RecyclerView.ViewHolder(personajeView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.NameTextView)
        val captionTextView = itemView.findViewById<TextView>(R.id.CaptionTextView)
        val statusTextView = itemView.findViewById<TextView>(R.id.StatusTextView)
        val genderTextView = itemView.findViewById<TextView>(R.id.GenderTextView)
        fun bind(image: String) {
            itemView.AvatarImageView.fromUrl(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView?.text = dataList[position].name
        holder.captionTextView?.text = dataList[position].caption
        holder.statusTextView?.text = dataList[position].status
        holder.genderTextView?.text = dataList[position].gender
        holder.bind(dataList[position].image)
    }
}