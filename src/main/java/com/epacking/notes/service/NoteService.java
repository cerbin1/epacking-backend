package com.epacking.notes.service;

import com.epacking.notes.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Note createNote(Note note);

    List<Note> getAllNotes();

    Optional<Note> getNoteById(Long id);

    boolean updateComment(Note note);
}
