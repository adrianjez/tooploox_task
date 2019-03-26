package com.hqapps.sample_app.presentation.search.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hqapps.domain.model.SearchEntity
import com.hqapps.sample_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_search_view_holder.view.*

class SearchAdapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    companion object {
        const val ADAPTER_DATA_BUNDLE_KEY = "ADAPTER_DATA_BUNDLE_KEY"
    }

    private var data: ArrayList<SearchEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(inflater.inflate(R.layout.adapter_search_view_holder, parent, false))

    override fun getItemCount(): Int =
            data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.fillCell(data[position])

    fun replaceData(data: List<SearchEntity>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun saveState(outState: Bundle){
        outState.putParcelableArrayList(ADAPTER_DATA_BUNDLE_KEY, data)
    }

    fun restoreState(outState: Bundle?){
        data = outState?.getParcelableArrayList<SearchEntity>(ADAPTER_DATA_BUNDLE_KEY) as ArrayList<SearchEntity>
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun fillCell(item: SearchEntity){
            Picasso.get().load(item.artworkUrl100).into(itemView.cell_image)
            itemView.cell_title.text = item.trackName
            itemView.cell_subtitle.text = item.artistName
        }
    }
}