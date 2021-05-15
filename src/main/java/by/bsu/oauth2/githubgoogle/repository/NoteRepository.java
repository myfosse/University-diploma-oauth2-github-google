package by.bsu.oauth2.githubgoogle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsu.oauth2.githubgoogle.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  List<Note> findByUserId(Long userId);
}