package pl.pomoku.schoolabsencecounter.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.schoolabsencecounter.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
