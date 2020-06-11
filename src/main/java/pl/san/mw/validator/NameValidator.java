package pl.san.mw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.san.mw.validator.UniqueName;
import pl.san.mw.model.AppUser;
import pl.san.mw.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class NameValidator implements ConstraintValidator<UniqueName, String> {


   private UserRepository userRepository;



   @Autowired
   public void setUserRepository(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public void initialize(UniqueName constraint) {
   }

   public boolean isValid(String username, ConstraintValidatorContext context) {
      List<AppUser> list = userRepository.findAll();
      if(list==null || list.isEmpty()){
         return true;
      }
      return list.stream()
              .filter(u->u.getUsername().equals(username))
              .collect(Collectors.toList())
              .isEmpty();


   }



}
