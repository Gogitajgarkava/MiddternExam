package com.example.midd_exam.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.midd_exam.R
import com.example.midd_exam.api.Cities
import com.squareup.picasso.Picasso

class CitiesRecyclerAdapter(
    private val citiesList: List<Cities>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CitiesRecyclerAdapter.CityViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(city: Cities)
    }

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: TextView = itemView.findViewById(R.id.textView4)
        private val cityName: TextView = itemView.findViewById(R.id.textView)
        private val cityImage: ImageView = itemView.findViewById(R.id.imageView)
        private val location: TextView = itemView.findViewById(R.id.textView2)
        private val website: TextView = itemView.findViewById(R.id.textView3)

        fun bind(city: Cities) {
            cityName.text = city.cityname ?: "404"
            location.text = city.location ?: "404"
            website.text = city.website ?: "404"
            id.text = city.id?.toString() ?: "404"

            Picasso.get().load(city.imageUrl).into(cityImage)

            itemView.setOnClickListener {
                itemClickListener.onItemClick(city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cities, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(citiesList[position])
    }

    override fun getItemCount(): Int = citiesList.size
}
