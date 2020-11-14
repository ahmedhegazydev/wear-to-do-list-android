package com.heagzy.myapplication.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.SwipeDismissFrameLayout
import com.heagzy.myapplication.R
import com.heagzy.myapplication.databinding.ActivityMainBinding


class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {


    lateinit var db: ActivityMainBinding
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        setContentView(R.layout.activity_main)
        db = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setSwipeDismissCallBack()

        setOnClickListener()


    }

    private fun setSwipeDismissCallBack() {
        db.swipeToDismiss.addCallback(object : SwipeDismissFrameLayout.Callback() {
            override fun onDismissed(layout: SwipeDismissFrameLayout?) {
                Log.e(TAG, "onDismissed: ")
                db.swipeToDismiss.visibility = View.GONE

            }

            override fun onSwipeCanceled(layout: SwipeDismissFrameLayout?) {
                super.onSwipeCanceled(layout)
                Log.e(TAG, "onSwipeCanceled: ")

            }

            override fun onSwipeStarted(layout: SwipeDismissFrameLayout?) {
                super.onSwipeStarted(layout)
                Log.e(TAG, "onSwipeStarted: ")
            }
        })
    }

    private fun setOnClickListener() {
        db.fabAddTask.setOnClickListener {
            addNewTask()
        }

        db.tvCancel.setOnClickListener {

        }


    }

    private fun addNewTask() {
//        this.toast("Adding new task")
        db.swipeToDismiss.visibility = View.VISIBLE

    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
        return MyAmbientCallback()
    }

    private class MyAmbientCallback : AmbientModeSupport.AmbientCallback() {

    }


}