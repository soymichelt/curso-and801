package com.soymichel.recyclerviewexampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceAdapter(private val list: ArrayList<PlaceModel>, private val eventHandler: PlaceAdapterEventHandler? = null) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(place: PlaceModel) {
            var country = itemView.findViewById<TextView>(R.id.CountryTextView)
            var city = itemView.findViewById<TextView>(R.id.CityTextView)
            var image = itemView.findViewById<ImageView>(R.id.AvatarImageView)
            country.text = place.country
            city.text = place.city
            itemView.setOnClickListener{
                eventHandler?.onItemClick(place, itemView)
            }
            itemView.setOnLongClickListener{
                eventHandler?.onItemLongClick(place, itemView)
                return@setOnLongClickListener (eventHandler != null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)
        return PlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(list[position])
    }

}