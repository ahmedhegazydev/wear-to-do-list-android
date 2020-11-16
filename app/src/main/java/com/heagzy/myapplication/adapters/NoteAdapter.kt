package com.heagzy.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.heagzy.myapplication.R
import com.heagzy.myapplication.room.models.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private var notes: List<Note> = ArrayList()


//    interface OnItemClickHandler {
//        fun onNoteClicked(view: View, note: Note);
//        fun onNoteClicked(holder: NoteHolder, note: Note);
//    }
//    private var onItemClickHandler: OnItemClickHandler? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_note_item, parent, false)
        return NoteHolder(itemView)
    }

//    override
//    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder =
//        NoteHolder(
//                    DataBindingUtil.inflate(
//                            LayoutInflater.from(parent.context),
//                            R.layout.layout_note_item,
//                            parent,
//                            false
//                    ))


    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]
//        holder.tvNotes.text = currentNote.description
        holder.tvNotes.text = currentNote.empployeName


//        holder.imageViewCheck.setOnClickListener {
//        holder.itemView.setOnClickListener {
//            if (position != RecyclerView.NO_POSITION) {
//                onItemClickHandler?.onNoteClicked(holder, currentNote)
//            }
//        }

//        if (currentNote.status.isEmpty()) {
//            holder.imageViewCheck.visibility = View.VISIBLE
//            holder.imageViewDone.visibility = View.GONE
////            holder.lottieDoneView.playAnimation()
////            currentNote.status = STATUS.COMPLETED.name
//        } else {
////            holder.imageViewCheck.visibility = View.GONE
////            holder.imageViewDone.visibility = View.VISIBLE
//        }

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
        var imageViewDone: ImageView = itemView.findViewById(R.id.image_view_done)
        var tvNotes: TextView = itemView.findViewById(R.id.tv_notes)
    }

//    class NoteHolder(val db: LayoutNoteItemBinding) :
//        RecyclerView.ViewHolder(db.root)


//    fun setOnItemClickHandler(onItemClickHandler: OnItemClickHandler){
//        this.onItemClickHandler = onItemClickHandler
//    }

}