package com.example.kotlintut

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class FireBaseShortCuts {
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun readBanner(){
        val Ref = firebaseDatabase.getReference("Banner")


    }

}