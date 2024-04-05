package com.example.kotlintut.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintut.model.SliderModel
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.kotlintut.R

class SliderAdapter(
    sliderItems:List<SliderModel>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    private lateinit var context:Context
    private val modifiedSliderItems:List<SliderModel> =  listOf(sliderItems.last()) + sliderItems + listOf(sliderItems.first())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        context = parent.context
        val view=LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container,parent,false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(modifiedSliderItems[position],context)
    }

    override fun getItemCount(): Int = modifiedSliderItems.size

    class SliderViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        private val imageView:ImageView=itemView.findViewById(R.id.imageSlide)

        fun setImage(sliderItems: SliderModel,context: Context){
            val requestOptions=RequestOptions().transform(CenterInside())

            Glide.with(context)
                .load(sliderItems.url)
                .apply(requestOptions)
                .into(imageView)
        }
    }

}