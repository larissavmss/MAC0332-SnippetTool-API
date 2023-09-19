package br.com.usp.mac0332.snippettool.service;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.repository.FolderRepository;
import br.com.usp.mac0332.snippettool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public void addUser(User user) {
        repo.save(user);
    }

    public User findByUsername(String name) {
        return repo.findByUsername(name);
    }
}
