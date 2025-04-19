package com.example.midd_exam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midd_exam.adapters.CitiesRecyclerAdapter
import com.example.midd_exam.adapters.SecondActivity
import com.example.midd_exam.api.Cities
import com.example.midd_exam.api.ReqresObj
import com.example.midd_exam.api.RestClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CitiesRecyclerAdapter
    private lateinit var citiesList: MutableList<Cities>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RestClient.init()

        RestClient.getReqResService().getCities(1)
            .enqueue(object : Callback<ReqresObj<List<Cities>>> {
                override fun onResponse(
                    call: Call<ReqresObj<List<Cities>>>,
                    response: Response<ReqresObj<List<Cities>>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        citiesList = response.body()!!.data?.toMutableList() ?: mutableListOf()

                        adapter = CitiesRecyclerAdapter(citiesList, object : CitiesRecyclerAdapter.OnItemClickListener {
                            override fun onItemClick(city: Cities) {
                                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                                intent.putExtra("CITYNAME", city.cityname)
                                intent.putExtra("CITYIMAGE", city.imageUrl)
                                intent.putExtra("DESCRIPTION", city.description)
                                intent.putExtra("WEBSITE", city.website)
                                intent.putExtra("LOCATION", city.location)

                                startActivity(intent)
                            }
                        })

                        recyclerView.adapter = adapter

                        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                            override fun onMove(
                                recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder
                            ): Boolean = false

                            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                                val position = viewHolder.adapterPosition
                                val city = citiesList[position]

                                when (direction) {
                                    ItemTouchHelper.RIGHT -> {
                                        Snackbar.make(recyclerView, "${city.cityname} დაემატა ფავორიტებში", Snackbar.LENGTH_SHORT).show()
                                        adapter.notifyItemChanged(position)
                                    }

                                    ItemTouchHelper.LEFT -> {
                                        citiesList.removeAt(position) // MutableList-ის გამოყენება
                                        adapter.notifyItemRemoved(position)
                                        Snackbar.make(recyclerView, "${city.cityname} წაიშალა", Snackbar.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }

                        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
                    }
                }

                override fun onFailure(call: Call<ReqresObj<List<Cities>>>, t: Throwable) {
                    Snackbar.make(recyclerView, "ჩატვირთვა ვერ მოხერხდა: ${t.localizedMessage}", Snackbar.LENGTH_LONG).show()
                }
            })
    }
}