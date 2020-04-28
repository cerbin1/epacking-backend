package com.epacking.notes.controller;

import com.epacking.authentication.models.User;
import com.epacking.authentication.repository.UserRepository;
import com.epacking.notes.model.Comment;
import com.epacking.notes.model.Note;
import com.epacking.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class NoteController {
    private final NoteService noteService;
    private final UserRepository userRepository;

    @Autowired
    public NoteController(NoteService noteService, UserRepository userRepository) {
        this.noteService = noteService;
        this.userRepository = userRepository;
    }

    @PostMapping("/notes/create")
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @PostMapping("/notes/createUserNote")
    public Note createUserNote(@RequestBody Note note, @RequestParam Long userId) {
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            note.setUser(userById.get());
        } else {
            throw new RuntimeException("Nie znaleziono uzytkownika o podanym id");
        }
        return noteService.createNote(note);
    }

    @GetMapping("/notes")
//    @PreAuthorize("hasRole('USER')")
    public List<Note> getNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/note")
    public ResponseEntity<Note> getNoteById(@RequestParam Long id) {
        Optional<Note> note = noteService.getNoteById(id);
        if (!note.isPresent()) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(note.get(), OK);
    }

    @GetMapping("/userNotes")
    public List<Note> getUserNotes(@RequestParam Long userId) {
        return noteService
                .getAllNotes()
                .stream()
                .filter(note -> note.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @PutMapping("addComment")
    public ResponseEntity updateNoteComment(@RequestParam Long id, @RequestBody Comment comment) {
        Optional<Note> noteOptional = noteService.getNoteById(id);
        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();
            note.setComment(comment.getValue());
            boolean success = noteService.updateComment(note);
            if (success) {
                return new ResponseEntity(OK);
            }
        }
        return new ResponseEntity(BAD_REQUEST);
    }
}
