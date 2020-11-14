package com.heagzy.myapplication.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.SwipeDismissFrameLayout
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.heagzy.myapplication.R
import com.heagzy.myapplication.databinding.ActivityMainBinding


class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {


    lateinit var db: ActivityMainBinding
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        setContentView(R.layout.activity_main)
        db = DataBindingUtil.setContentView(this, R.layout.activity_main)
        db.visibility = View.GONE
        setSwipeDismissCallBack()
        setOnClickListener()

    }

    private fun setSwipeDismissCallBack() {
        db.layoutSwipe.swipeToDismiss.addCallback(object : SwipeDismissFrameLayout.Callback() {
            override fun onDismissed(layout: SwipeDismissFrameLayout?) {
                Log.e(TAG, "onDismissed: ")
                db.layoutSwipe.swipeToDismiss.visibility = View.GONE
//                db.visibility = View.GONE

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


        db.onWidgetClicked = View.OnClickListener {
            when (it.id) {
                R.id.layout_type -> {
                    Log.e(TAG, "setOnClickListener: ")

                }
                R.id.layout_speak -> {
                    Log.e(TAG, "setOnClickListener: ")

                }
            }
        }


    }

    private fun addNewTask() {
//        this.toast("Adding new task")
//        db.layoutSwipe.visibility = View.VISIBLE
        db.visibility = View.VISIBLE

        YoYo.with(Techniques.SlideInRight)
            .duration(350)
            .playOn(db.layoutSwipe.root)


    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
        return MyAmbientCallback()
    }

    private class MyAmbientCallback : AmbientModeSupport.AmbientCallback()


}