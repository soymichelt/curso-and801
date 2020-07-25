package com.soymichel.applistadoapi

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soymichel.applistadoapi.controllers.IAPIService
import com.soymichel.applistadoapi.controllers.PersonajeAdapter
import com.soymichel.applistadoapi.models.Personaje
import com.soymichel.applistadoapi.models.Personajes
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    var baseUrl: String = "https://rickandmortyapi.com/api/character/"

    var currentUrl: String = baseUrl

    var currentPage: Byte = 1

    var pages: Byte = 1

    lateinit var prevPageImageButton: ImageButton
    lateinit var nextPageImageButton: ImageButton

    private lateinit var refreshLayout: SwipeRefreshLayout

    private lateinit var pagesTextView: TextView

    private lateinit var dataList:ArrayList<Personaje>
    private lateinit var nextPage:String
    private lateinit var prevPage:String

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            prevPageImageButton = findViewById<ImageButton>(R.id.PrevImageButton)
            prevPageImageButton.isEnabled = false
            prevPageImageButton.setOnClickListener { v -> handlePrevPageLoad(v) }

            nextPageImageButton = findViewById<ImageButton>(R.id.NextImageButton)
            nextPageImageButton.isEnabled = false

            initRefresh()

            pagesTextView = findViewById(R.id.PagesCountTextView)

            this.getData()
        }
        catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }

    }

    private fun initRefresh() {
        refreshLayout = findViewById(R.id.RefreshLayout)
        refreshLayout.setOnRefreshListener {
            getData()
        }
    }

    public fun handlePrevPageLoad(v: View) {
        this.currentUrl = prevPage
        this.currentPage--
        this.getData()
    }
    private fun setPrevPage(url: String?) {
        if(url == null) {
            prevPageImageButton.isEnabled = false
            return
        }
        if(url.toString().trim() == "") {
            prevPageImageButton.isEnabled = false
            return
        }

        prevPage = url.toString()
        prevPageImageButton.isEnabled = true
    }

    public fun handleNextPageLoad(v: View) {
        this.currentUrl = nextPage
        this.currentPage++
        this.getData()
    }
    private fun setNextPage(url: String?) {
        if(url == null) {
            nextPage = ""
            nextPageImageButton.isEnabled = false
            return
        }
        if(url.toString().trim() == "") {
            nextPage = ""
            nextPageImageButton.isEnabled = false
            return
        }

        nextPage = url.toString()
        nextPageImageButton.isEnabled = true
    }

    private fun setPagesUI(pages: Byte) {
        pagesTextView.text = "$currentPage de $pages"
    }

    private fun setDataInUI(contract: Personajes) {

        dataList = contract.results
        setPrevPage(contract.info.prev)
        setNextPage(contract.info.next)
        setPagesUI(contract.info.pages)

        val listRecyclerView = findViewById<RecyclerView>(R.id.ListRecyclerView)
        listRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val personajeAdapter = PersonajeAdapter(dataList)
        listRecyclerView.adapter = personajeAdapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(this.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getData() {

        if(!refreshLayout.isRefreshing) refreshLayout.isRefreshing = true

        val context = this
        doAsync {
            try {
                val call = getRetrofit().create(IAPIService::class.java).getCharacters(currentUrl).execute()
                val puppies = call.body() as Personajes
                uiThread {
                    setDataInUI(puppies)
                    refreshLayout.isRefreshing = false
                }
            }
            catch (e: Exception) {
                uiThread {
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    refreshLayout.isRefreshing = false
                }
            }
        }
    }
}