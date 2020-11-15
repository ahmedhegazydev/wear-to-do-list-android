package com.heagzy.myapplication.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.SwipeDismissFrameLayout
import androidx.wear.widget.WearableLinearLayoutManager
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.heagzy.myapplication.CustomScrollingLayoutCallback
import com.heagzy.myapplication.R
import com.heagzy.myapplication.RecyclerItemClickListener
import com.heagzy.myapplication.adapters.NoteAdapter
import com.heagzy.myapplication.databinding.ActivityMainBinding
import com.heagzy.myapplication.room.Note
import com.heagzy.myapplication.viewmodels.NoteViewModel


class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider
//    ,NoteAdapter.OnItemClickHandler

{


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
//        noteViewModel.deleteAllNotes()
        refreshRvNotes()
//        insertDummyDataToRoomDb()

    }

    /**
     * insertDummyDataToRoomDb
     */
    private fun insertDummyDataToRoomDb() {
        noteViewModel.insert(Note("Title 1", "description 1"))
        noteViewModel.insert(Note("Title 2", "description 2"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
        noteViewModel.insert(Note("Title 3", "description 3"))
    }

    private fun refreshRvNotes() {
        noteViewModel.getAllNotes().observe(this, { notes ->
            adapter.setNotes(notes!!)
//            notes.size
            if (notes.isNotEmpty()) {
                db.rvNotes.visibility = View.VISIBLE
                db.fabAddTask.visibility = View.GONE
            } else {
                db.rvNotes.visibility = View.GONE
                db.fabAddTask.visibility = View.VISIBLE
            }

        })
    }

    private fun init() {
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    private fun initRvNotes() {

//        db.rvNotes.layoutManager = LinearLayoutManager(this)
//        db.rvNotes.setHasFixedSize(true)


        db.rvNotes.apply {
            // To align the edge children (first and last) with the center of the screen
//            isEdgeItemsCenteringEnabled = true
//            isCircularScrollingGestureEnabled = true
//            bezelFraction = 0.5f
//            scrollDegreesPerScreen = 90f
//            layoutManager = WearableLinearLayoutManager(this@MainActivity)
            layoutManager =
                WearableLinearLayoutManager(this@MainActivity, CustomScrollingLayoutCallback())
        }



        db.rvNotes.adapter = adapter

//        adapter.setOnItemClickHandler(this)
        db.rvNotes.addOnItemTouchListener(
            RecyclerItemClickListener(
                this@MainActivity
            ) { v, position ->
                Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show()


            }
        )

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

    //    override fun onNoteClicked(currentNote: Note) {
//    override fun onNoteClicked(view: View, note: Note) {
//    override fun onNoteClicked(holder: NoteAdapter.NoteHolder, note: Note) {

//        note.status = STATUS.COMPLETED.name
//        noteViewModel.updateNote(note)
//        holder.lottieDoneView.playAnimation()
//        holder.lottieDoneView.addAnimatorListener(object : Animator.AnimatorListener {
//            override fun onAnimationStart(animation: Animator?) {
//            }
//
//            override fun onAnimationEnd(animation: Animator?) {
////                holder.imageViewCheck.visibility = View.GONE
////                holder.imageViewDone.visibility = View.VISIBLE
//            }
//
//            override fun onAnimationCancel(animation: Animator?) {
//
//            }
//
//            override fun onAnimationRepeat(animation: Animator?) {
//
//            }
//
//
//        })
//
//    }


}