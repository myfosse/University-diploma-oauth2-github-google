package by.bsu.oauth2.githubgoogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.bsu.oauth2.githubgoogle.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String aLong);

  User findByGoogleUsername(String name);

  User findByGoogleName(String name);
}
