package com.epacking.notes.controller;

import com.epacking.notes.model.Note;
import com.epacking.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes/create")
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @GetMapping("/notes")
//    @PreAuthorize("hasRole('USER')")
    public List<Note> getNotes() {
        return noteService.getAllNotes();
    }
    // todo jeszcze zrobic notes by user id i tylko jednego usera zwrocic

}
