package pl.san.mw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.san.mw.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByUsername(String userName);
}
