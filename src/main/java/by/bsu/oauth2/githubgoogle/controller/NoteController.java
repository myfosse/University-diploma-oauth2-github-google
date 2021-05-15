package by.bsu.oauth2.githubgoogle.controller;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import by.bsu.oauth2.githubgoogle.entity.Note;
import by.bsu.oauth2.githubgoogle.entity.User;
import by.bsu.oauth2.githubgoogle.repository.NoteRepository;
import by.bsu.oauth2.githubgoogle.repository.UserRepository;
import by.bsu.oauth2.githubgoogle.service.UserService;

@Controller
public class NoteController {

  @Autowired private NoteRepository noteRepository;
  @Autowired private UserService userService;
  @Autowired private UserRepository userRepository;

  @GetMapping("/notes")
  public String notes(Principal principal, Model model)
  {

    User user = null;

    user = (User) userService.loadUserByUsername(principal.getName());
    if( Objects.isNull(user)) {
      user = (User) userRepository.findByGoogleName(principal.getName());
    }

    List<Note> notes = noteRepository.findByUserId(user.getId());
    model.addAttribute("notes", notes);
    model.addAttribute("user", user);

    return "notes";
  }

  @PostMapping("/addnote")
  public String addNote(Principal principal, String title, String note)
  {
    User user = (User) userService.loadUserByUsername(principal.getName());
    if( Objects.isNull(user)) {
      user = (User) userRepository.findByGoogleName(principal.getName());
    }

    Note newNote = new Note();
    newNote.setTitle(title);
    newNote.setNote(note);
    newNote.setUserId(user.getId());

    noteRepository.save(newNote);

    return "redirect:/notes";
  }
}
