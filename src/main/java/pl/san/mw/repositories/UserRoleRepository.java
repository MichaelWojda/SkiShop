package pl.san.mw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.san.mw.model.UserRole;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    UserRole findUserRoleByRoleName(String roleName);
}
