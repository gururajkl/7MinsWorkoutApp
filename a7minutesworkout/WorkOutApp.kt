package com.example.a7minutesworkout

import android.app.Application

class WorkOutApp: Application() {
    // (lazy) loads data only when needed
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}