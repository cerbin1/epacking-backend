package com.epacking.notes.service;

import com.epacking.notes.model.Note;

import java.util.List;

public interface NoteService {
    Note createNote(Note note);

    List<Note> getAllNotes();
}
