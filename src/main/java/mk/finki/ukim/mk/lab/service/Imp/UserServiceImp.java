package mk.finki.ukim.mk.lab.service.Imp;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullname;
import mk.finki.ukim.mk.lab.model.exception.InvalidUsernameException;
import mk.finki.ukim.mk.lab.model.exception.UserNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRep;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRep userRepository;

    public UserServiceImp(UserRep userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String username) {
        if (username==null || username.isEmpty())
            throw new InvalidUsernameException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new InvalidUsernameException();
        User user = new User(username);
        return userRepository.save(user);
    }

    @Override
    public User save(String username, String name, String surname, String password,
                     LocalDate now, List<ShoppingCart> carts) {

        UserFullname userFullname = new UserFullname();
        if(name == null || surname == null || password.isEmpty()){
            throw new InvalidUsernameException();
        }else {
            userFullname.setName(name);
            userFullname.setSurname(surname);
        }

        if(this.userRepository.findByUsername(username).isPresent() || username == null)
            throw new UserNotFoundException(username);

        User user = new User(username, userFullname, password, now, null);
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String usename) {
        return this.findByUsername(usename);
    }


}
