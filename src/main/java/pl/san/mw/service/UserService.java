package pl.san.mw.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.san.mw.model.AppUser;
import pl.san.mw.model.UserRole;
import pl.san.mw.repositories.UserRepository;
import pl.san.mw.repositories.UserRoleRepository;

//autor Michał Wojda indeks:23512
@Service
public class UserService {

    private static final String DEFAULT_ROLE = "BASIC";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public AppUser createSimpleUser(AppUser user){
        UserRole role = userRoleRepository.findUserRoleByRoleName(DEFAULT_ROLE);
        user.getUserRoles().add(role);
        String hashedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPass);
        return userRepository.save(user);
    }
}
