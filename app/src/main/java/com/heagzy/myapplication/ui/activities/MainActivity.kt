package com.heagzy.myapplication.ui.activities

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.SwipeDismissFrameLayout
import androidx.wear.widget.WearableLinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.heagzy.myapplication.CustomScrollingLayoutCallback
import com.heagzy.myapplication.R
import com.heagzy.myapplication.RecyclerItemClickListener
import com.heagzy.myapplication.adapters.NoteAdapter
import com.heagzy.myapplication.databinding.ActivityMainBinding
import com.heagzy.myapplication.room.Note
import com.heagzy.myapplication.room.STATUS
import com.heagzy.myapplication.viewmodels.NoteViewModel


class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider
//class MainActivity : AppCompatActivity(), AmbientModeSupport.AmbientCallbackProvider
//    ,NoteAdapter.OnItemClickHandler

{


    /*
     * Declare an ambient mode controller, which will be used by
     * the activity to determine if the current mode is ambient.
     */
    private lateinit var ambientController: AmbientModeSupport.AmbientController


    private lateinit var noteViewModel: NoteViewModel
    private val adapter = NoteAdapter()
    lateinit var db: ActivityMainBinding
    private val TAG = "MainActivity"
    private var notes: List<Note> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        db = DataBindingUtil.setContentView(this, R.layout.activity_main)
        db.visibility = View.GONE
        ambientController = AmbientModeSupport.attach(this)

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
            this.notes = notes
            adapter.setNotes(notes!!)
//            notes.size
            if (notes.isNotEmpty()) {
                db.rvNotes.visibility = View.VISIBLE
                db.fabAddTask.visibility = View.GONE
                db.viewBottomAddTask.visibility = View.VISIBLE

            } else {
                db.rvNotes.visibility = View.GONE
                db.fabAddTask.visibility = View.VISIBLE
                db.viewBottomAddTask.visibility = View.GONE


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
//                Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show()
                doActionOnItemClicked(v, position)
            }
        )

    }

    private fun doActionOnItemClicked(v: View?, position: Int) {
        val note = notes[position]
        note.status = STATUS.COMPLETED.name
        noteViewModel.updateNote(note)
        val lottieDoneView =
            v?.findViewById<LottieAnimationView>(R.id.lottie_done)
        lottieDoneView?.visibility = View.VISIBLE
        lottieDoneView?.playAnimation()
        lottieDoneView?.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
//                holder.imageViewCheck.visibility = View.GONE
//                holder.imageViewDone.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }


        })
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
            addNewTask(false)
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

        db.viewBottomAddTask.setOnClickListener {
            addNewTask(true)
        }


    }

    private fun addNewTask(bottomToTop: Boolean) {
//        this.toast("Adding new task")
//        db.layoutSwipe.visibility = View.VISIBLE
        db.visibility = View.VISIBLE


        var tech = Techniques.SlideInUp

        if (bottomToTop) {
//            YoYo.with(Techniques.SlideInDown)
//            YoYo.with(Techniques.SlideInUp)
//                .duration(350)
//                .playOn(db.layoutSwipe.root)
            tech = Techniques.SlideInUp
        } else {
//            YoYo.with(Techniques.SlideInRight)
//                .duration(350)
//                .playOn(db.layoutSwipe.root)
            tech = Techniques.SlideInRight

        }

        YoYo.with(tech)
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
//    doActionOnItemClicked()
//
//    }


}