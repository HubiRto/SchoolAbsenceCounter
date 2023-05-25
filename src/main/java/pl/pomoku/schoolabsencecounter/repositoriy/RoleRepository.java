package pl.pomoku.schoolabsencecounter.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.schoolabsencecounter.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
