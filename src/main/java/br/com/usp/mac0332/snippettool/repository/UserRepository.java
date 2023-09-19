package br.com.usp.mac0332.snippettool.repository;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User save(User user);

}
