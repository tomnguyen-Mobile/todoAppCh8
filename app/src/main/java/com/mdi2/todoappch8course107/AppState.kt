package com.mdi2.todoappch8course107

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log


@SuppressLint("StaticFieldLeak") // hide the warning for static field leak
object AppState  {
    private var _activityContext: Context? = null
    private var _appContext: Context? = null
    private val _leakActivities = mutableListOf<Context>()

    fun initialize(context: Context) {// creating the memory leak
        _appContext = context.applicationContext
//        Log.w("AppState", "safe context stored -- application context never leaks: ${_leakActivities.size}")



        // showing laked activities counter before the fix //
        _activityContext = context // stores the  activity context directly
        _leakActivities.add(context) // accumulates the leaked activities
        Log.w("AppState", "Context stored -- leaked activities:${_leakActivities.size}")

    } // end of initialize
} // end of appState