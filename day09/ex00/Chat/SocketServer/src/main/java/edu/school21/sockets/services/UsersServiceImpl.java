package edu.school21.sockets.services;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Component
public class UsersServiceImpl implements UsersService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository repo;

    @Autowired
    public UsersServiceImpl(PasswordEncoder passwordEncoder, UsersRepository repo) {
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    @Override
    public boolean registration(String name, String password) {
        if (repo.findByLogin(name).isPresent()) {
            return false;
        }
        repo.save(new User(0, name, passwordEncoder.encode(password)));
        return true;
    }
}
