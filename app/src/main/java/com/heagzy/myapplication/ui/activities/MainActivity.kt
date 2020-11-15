package com.heagzy.myapplication.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.SwipeDismissFrameLayout
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.heagzy.myapplication.R
import com.heagzy.myapplication.adapters.NoteAdapter
import com.heagzy.myapplication.databinding.ActivityMainBinding
import com.heagzy.myapplication.room.Note
import com.heagzy.myapplication.viewmodels.NoteViewModel


class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {


    private lateinit var noteViewModel: NoteViewModel
    private val adapter = NoteAdapter()
    lateinit var db: ActivityMainBinding
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        db = DataBindingUtil.setContentView(this, R.layout.activity_main)
        db.visibility = View.GONE

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        init()
        setSwipeDismissCallBack()
        setOnClickListener()
        initRvNotes()
        refreshRvNotes()
        insertDummyDataToRoomDb()

    }

    private fun insertDummyDataToRoomDb() {

        noteViewModel.insert(Note("Title 1", "description 1"))
        noteViewModel.insert(Note("Title 2", "description 2"))
        noteViewModel.insert(Note("Title 3", "description 3"))


    }

    private fun refreshRvNotes() {
        noteViewModel.getAllNotes().observe(this, { t ->
            adapter.setNotes(t!!)
        })
    }

    private fun init() {
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    private fun initRvNotes() {

        db.rvNotes.layoutManager = LinearLayoutManager(this)
        db.rvNotes.setHasFixedSize(true)
        db.rvNotes.adapter = adapter

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