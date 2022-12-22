package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullname;
import mk.finki.ukim.mk.lab.model.exception.InvalidUsernameException;
import mk.finki.ukim.mk.lab.model.exception.UserNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRep;
import mk.finki.ukim.mk.lab.service.Imp.UserServiceImp;
import mk.finki.ukim.mk.lab.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class UserUnit {
    @Mock
    private UserRep userRepository;

    private UserService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        UserFullname userFullname = new UserFullname();
        userFullname.setName("name");
        userFullname.setSurname("surname");
        User user = new User("username", userFullname, "password", LocalDate.now(), null);

        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);

        this.service = Mockito.spy(new UserServiceImp(this.userRepository));
    }

    @Test
    public void testSuccess(){
        User user = this.service.save("usename", "name", "surname", "password", LocalDate.now(), null);
        Mockito.verify(this.service).save("usename", "name", "surname", "password", LocalDate.now(), null);

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("user do not match","username", user.getUsername());
        Assert.assertEquals("password do not match","password", user.getPassword());
        Assert.assertEquals("Fullname do not match","name", user.getFullname().getName());
        Assert.assertEquals("Fullname do not match","surname", user.getFullname().getSurname());
    }

    @Test
    public void testUser(){
        Assert.assertThrows("InvalidUsernameException excepted for name",
                InvalidUsernameException.class,
                () -> this.service.save("usename", null, "surname", "password", LocalDate.now(), null));

        Mockito.verify(this.service).save("usename", null, "surname", "password", LocalDate.now(), null);
    }
    @Test
    public void testSurname(){
        Assert.assertThrows("InvalidUsernameException excepted for name",
                InvalidUsernameException.class,
                () -> this.service.save("usename", "name", null, "password", LocalDate.now(), null));

        Mockito.verify(this.service).save("usename", "name", null, "password", LocalDate.now(), null);
    }
    @Test
    public void testPassword(){
        String password = "";
        Assert.assertThrows("InvalidUsernameException excepted for name",
                InvalidUsernameException.class,
                () -> this.service.save("usename", "name", "surname", password, LocalDate.now(), null));
        Mockito.verify(this.service).save("usename", "name", "surname", password, LocalDate.now(), null);
    }

    @Test
    public void testUserId(){
        String testuser = null;
        Assert.assertThrows("UserNotFoundException excepted",
                UserNotFoundException.class,
                ()->  this.service.save(testuser, "name", "surname", "password", LocalDate.now(), null));
        Mockito.verify(this.service).save(testuser, "name", "surname", "password", LocalDate.now(), null);

    }

}
