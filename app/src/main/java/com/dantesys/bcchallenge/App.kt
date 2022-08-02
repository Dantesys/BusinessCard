package com.dantesys.bcchallenge

import android.app.Application
import com.dantesys.bcchallenge.data.CardDB
import com.dantesys.bcchallenge.data.CardRepository

class App : Application() {
    private val db by lazy {
        CardDB.getDB(this)
    }
    val cardRepository by lazy {
        CardRepository(db.cardDao())
    }
}