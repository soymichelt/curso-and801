package com.soymichel.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_listview.*

class ListviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        val listado:ArrayList<String> = ArrayList()

        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado)

        itemsListView.adapter = adaptador

        addItemButton.setOnClickListener{
            val todo = itemEditText.text.toString()
            if(todo.isBlank()) {
                Toast.makeText(this, "Complete información", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            listado.add(todo)
            adaptador.notifyDataSetChanged()

            itemEditText.setText("")
        }

        itemsListView.setOnItemLongClickListener{parent, view, position, id ->
            val elemento = listado.get(position)
            listado.remove(elemento)
            adaptador.notifyDataSetChanged()
            return@setOnItemLongClickListener(true)
        }
    }
}