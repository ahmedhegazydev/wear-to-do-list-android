package com.heagzy.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.heagzy.myapplication.R
import com.heagzy.myapplication.room.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private var notes: List<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        holder.tvNotes.text = currentNote.description


//        holder.imageViewCheck.setOnClickListener {
        holder.itemView.setOnClickListener {
            holder.imageViewCheck.visibility = View.GONE
            holder.lottieDoneView.playAnimation()
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }


    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        var radio: RadioButton = itemView.findViewById(R.id.radio)
        var lottieDoneView: LottieAnimationView = itemView.findViewById(R.id.lottie_done)
        var imageViewCheck: ImageView = itemView.findViewById(R.id.image_view_check)
        var tvNotes: TextView = itemView.findViewById(R.id.tv_notes)

    }
}