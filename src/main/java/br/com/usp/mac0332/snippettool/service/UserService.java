package br.com.usp.mac0332.snippettool.service;

import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }


    public UserDetails loadUserByUsername(Login login) throws UsernameNotFoundException {
        var userFromDb = repo.findByUsername(login.getUsername());
        if (userFromDb == null && login.getEmail() != ""){
            return repo.save(createUserFromLogin(login));
        }
        if (userFromDb != null && passwordEncoder.matches(login.getPassword(), userFromDb.getPassword())){
            return userFromDb;
        } else {
            throw new UsernameNotFoundException("");
        }
    }

    private User createUserFromLogin(Login login){
        var user = new User();
        user.setPassword(passwordEncoder.encode(login.getPassword()));
        user.setUsername(login.getUsername());
        user.setRegistrationDate(Date.valueOf(LocalDate.now()));
        user.setEmail(login.getEmail());
        return user;
    }

    public User findByUsername(String name) {
        return repo.findByUsername(name);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username);
    }
}
