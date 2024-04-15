package com.example.imageloadpractical

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication(){
    override fun onCreate() {
        MultiDex.install(this);
        super.onCreate()
    }
}