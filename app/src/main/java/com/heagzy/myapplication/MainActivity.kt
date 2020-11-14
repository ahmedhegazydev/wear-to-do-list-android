package com.heagzy.myapplication

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import com.heagzy.myapplication.databinding.ActivityMainBinding


class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {


    lateinit var db: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        setContentView(R.layout.activity_main)
        db = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setOnClickListener()


    }

    private fun setOnClickListener() {
        db.fabAddTask.setOnClickListener {
            addNewTask()
        }
    }

    private fun addNewTask() {

    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
        return MyAmbientCallback()
    }

    private class MyAmbientCallback : AmbientModeSupport.AmbientCallback() {

        override fun onEnterAmbient(ambientDetails: Bundle?) {
            super.onEnterAmbient(ambientDetails)

        }

        override fun onExitAmbient() {
            super.onExitAmbient()
        }
    }


}