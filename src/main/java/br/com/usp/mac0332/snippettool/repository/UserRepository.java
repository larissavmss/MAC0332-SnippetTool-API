package br.com.usp.mac0332.snippettool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usp.mac0332.snippettool.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findAll();

}
