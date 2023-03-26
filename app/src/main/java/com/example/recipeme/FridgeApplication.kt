package com.example.recipeme

import android.app.Application

class FridgeApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this)}
}