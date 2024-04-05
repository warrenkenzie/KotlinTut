package com.example.kotlintut.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlintut.model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel(): ViewModel() {
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val _banner = MutableLiveData<List<SliderModel>>()
    private val tag : String = "MainViewModel"
    val banners: LiveData<List<SliderModel>> = _banner

    fun loadBanners(){
        val bannerRef = firebaseDatabase.getReference("Banner")
        val bannerListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val lists = mutableListOf<SliderModel>()
                for(childSnapshot in dataSnapshot.children){
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if(list!=null){
                        Log.w("MainViewModel", list.url)
                        lists.add(list)
                    }
                    _banner.value = lists
                }
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(tag, "loadPost:onCancelled", databaseError.toException())
            }
        }

        bannerRef.addValueEventListener(bannerListener)
    }
}