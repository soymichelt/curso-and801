package com.soymichel.recyclerviewexampleapp

import android.view.View

interface PlaceAdapterEventHandler {
    fun onItemClick(place:PlaceModel, view:View)
    fun onItemLongClick(place:PlaceModel, view:View)
}