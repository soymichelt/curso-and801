package com.soymichel.recyclerviewexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PlaceAdapterEventHandler {

    companion object {
        val MI_TAG = "LAB8"
    }

    private lateinit var adapter: PlaceAdapter
    private lateinit var countryList: ArrayList<PlaceModel>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var countryNameList:Array<String> = arrayOf("Canada","USA","Mexico","Columbia","Malaysia","Singapore","Turkey","Nicaragua","India","Italy","Tunisia","Chile","Argentina","Spain","Peru")
        var citiesNameList:Array<String> = arrayOf("Ottawa","Washington, D.C.","Mexico City","Bogotá","Kuala Lumpur","Singapore","Ankara","Managua","New Delhi","Rome","Tunis","Santiago","Buenos Aires","Madrid","Lima")

        countryList = ArrayList()

        for (i in 0..countryNameList.lastIndex) {
            var place=PlaceModel(countryNameList[i], citiesNameList[i])
            countryList?.add(place)
        }

        adapter = PlaceAdapter(countryList, this)

        layoutManager = LinearLayoutManager(this)

        CountryRecyclerView.adapter = adapter
        CountryRecyclerView.layoutManager = layoutManager

        adapter!!.notifyDataSetChanged()

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onItemClick(place: PlaceModel, view: View) {
        TODO("Not yet implemented")
    }

    override fun onItemLongClick(place: PlaceModel, view: View) {
        countryList.remove(place)
        adapter!!.notifyDataSetChanged()
    }
}