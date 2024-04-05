package com.example.kotlintut.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlintut.adapter.SliderAdapter
import com.example.kotlintut.ViewModel.MainViewModel
import com.example.kotlintut.databinding.ActivityHomeBinding
import com.example.kotlintut.model.SliderModel
import kotlin.math.log

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val tag = "HomeActivity"
    // Using viewModels delegate to obtain an instance of MainViewModel
    private val viewModel=MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.w(tag,"initBanner")
        initBanner()

    }

    private fun initBanner() {
        binding.progressBarBanner.visibility= View.VISIBLE
        viewModel.banners.observe(this, Observer { items->
            banners(items)
            binding.progressBarBanner.visibility=View.GONE
            binding.viewPageSlider.currentItem = 1
        })
        viewModel.loadBanners()
    }
    private fun banners(images:List<SliderModel>){

        binding.viewPageSlider.adapter= SliderAdapter(images,binding.viewPageSlider)
        binding.viewPageSlider.clipToPadding= false
        binding.viewPageSlider.clipChildren= false
        binding.viewPageSlider.offscreenPageLimit= 3
        binding.viewPageSlider.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER
        binding.viewPageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (binding.viewPageSlider.currentItem) {
                        images.size + 2 - 1 -> binding.viewPageSlider.setCurrentItem(1, false)
                        0 -> binding.viewPageSlider.setCurrentItem(images.size + 2 - 2, false)
                    }
                }
            }
        })

        val compositePageTransformer=CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewPageSlider.setPageTransformer(compositePageTransformer)
        if (images.size>1){
            binding.dotsIndicator.visibility=View.VISIBLE

        }

    }

    /* TODO: When the code inside this fun is called, the fun does work as intended but if put outside, it works. Find out why and solve issue */
    /*fun onInfinitePageChangeCallback(listSize: Int) {
        binding.viewPageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (binding.viewPageSlider.currentItem) {
                        listSize -1 -> binding.viewPageSlider.setCurrentItem(1, false)
                        0 -> binding.viewPageSlider.setCurrentItem(listSize - 2, false)
                    }
                }
            }
        })
    }*/
}