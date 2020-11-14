package com.heagzy.myapplication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.heagzy.myapplication.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class NotesPreferences(private val context: Context) {

    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences> =
        context.createDataStore(
            name = "notes_pref"
        )

    companion object {
        val NOTE_KEY = preferencesKey<Note>(name = "NOTE_KEY")
    }

    suspend fun saveNote(note: Note) {
        dataStore.edit { preferences ->
            preferences[NOTE_KEY] = note
        }
    }

    val getNote: Flow<Note> = dataStore.data
        .map { preferences ->
            preferences[NOTE_KEY] ?: Note()
        }
}