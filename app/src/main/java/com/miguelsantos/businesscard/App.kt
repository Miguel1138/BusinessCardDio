package com.miguelsantos.businesscard

import android.app.Application
import com.miguelsantos.businesscard.data.AppDatabase
import com.miguelsantos.businesscard.data.BusinessCardRepository

class App : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { BusinessCardRepository(database.businessCardDao()) }
}