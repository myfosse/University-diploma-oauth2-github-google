package by.bsu.oauth2.githubgoogle.controller;

import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import by.bsu.oauth2.githubgoogle.entity.Role;
import by.bsu.oauth2.githubgoogle.entity.User;
import by.bsu.oauth2.githubgoogle.repository.UserRepository;

@Controller
public class RegistrationController {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(String name, String username, String password) {
    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setActive(true);
    user.setRoles(Collections.singleton(Role.USER));

    userRepository.save(user);

    return "redirect:/login";
  }

  @GetMapping("/")
  public String index(Principal principal) {
    if (principal != null) {
      return "redirect:/notes";
    }
    return "index";
  }
}
